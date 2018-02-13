package br.com.harlan.avaliabus.business.services

import br.com.harlan.avaliabus.adapter.HistoricRecyclerAdapter
import br.com.harlan.avaliabus.database.UserDatabase
import br.com.harlan.avaliabus.database.services.TaskServiceable
import br.com.harlan.avaliabus.model.EvaluationModel
import br.com.harlan.avaliabus.prototype.FragmentPrototype
import br.com.harlan.avaliabus.view.HomeActivity
import br.com.harlan.avaliabus.view.LoginActivity
import br.com.harlan.avaliabus.view.RegisterEvaluationFragment
import br.com.harlan.avaliabus.view.services.Messages
import br.com.harlan.avaliabus.view.services.Messages.*
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseException
import com.google.firebase.storage.UploadTask

class TaskService : TaskServiceable, Messages {

    //region Variables and Constructor
    var messageService: MessageServiceable
    var navigationService: NavigationServiceable
    private lateinit var mensagem: String
    private lateinit var historicAdapter: HistoricRecyclerAdapter
    private lateinit var evaluationModels: ArrayList<EvaluationModel>
    private lateinit var upgradeComponents: UpgradeComponents

    constructor(messageService: MessageServiceable, navigationService: NavigationServiceable) {
        this.messageService = messageService
        this.navigationService = navigationService
    }
    //endregion Variables and Constructor

    fun newError(mensagem: String) {
        messageService.newToast(mensagem)
    }

    //region AuthenticationFunctions

    override fun registeredUser(task: Task<AuthResult>) {
        if (task.isSuccessful()) {
            mensagem = "Usuário cadastrado com sucesso."
            navigationService.navigateTo(LoginActivity::class.java)
        } else {
            try {
                throw task.exception!!
            } catch (e: FirebaseAuthWeakPasswordException) {
                mensagem = FIREBASE_AUTH_WEAK_PASSWORD_EXCEPTION
                e.printStackTrace()
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                mensagem = FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION
                e.printStackTrace()
            } catch (e: FirebaseAuthUserCollisionException) {
                mensagem = FIREBASE_AUTH_USER_COLLISION_EXCEPTION
                e.printStackTrace()
            } catch (e: Exception) {
                mensagem = AUTH_EXCEPTION
                e.printStackTrace()
            }
        }
        messageService.newToast(mensagem)
    }

    override fun userSavedDatabase(task: Task<Void>) {
        if (task.isSuccessful) {
            UserDatabase(this).userLogOut()
        } else {
            try {
                throw task.exception!!
            } catch (e: FirebaseNetworkException) {
                mensagem = EVALUATION_SENT_NETWORK_EXCEPTION
                e.printStackTrace()
            } catch (e: Exception) {
                mensagem = AUTH_EXCEPTION
                e.printStackTrace()
            }
        }
    }

    override fun sessionClosed() {
        messageService.newToast(SESSION_CLOSED)
        navigationService.navigateTo(LoginActivity::class.java)
    }

    override fun sessionStarted(task: Task<AuthResult>) {
        if (task.isSuccessful()) {
            mensagem = LOGIN_SUCESSFUL
            navigationService.navigateTo(HomeActivity::class.java)
        } else {
            try {
                throw task.exception!!
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                mensagem = FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_LOGIN
            } catch (e: FirebaseAuthInvalidUserException) {
                mensagem = FIREBASE_AUTH_INVALID_USER_EXCEPTION_LOGIN
            } catch (e: Exception) {
                mensagem = LOGIN_EXCEPTION
            }
        }
        messageService.newToast(mensagem)
    }

    override fun passwordReset(task: Task<Void>) {
        if (task.isSuccessful) {
            mensagem = "Senha redefinida com sucesso. Em breve chegará um " + "email com mais instruções."
            navigationService.navigateTo(LoginActivity::class.java)
        } else {
            try {
                throw task.exception!!
            } catch (e: FirebaseAuthInvalidUserException) {
                mensagem = "Email não cadastrado."
            } catch (e: Exception) {
                mensagem = "Ocorreu um erro ao redefinir sua senha: " + e.message
            }
        }
        messageService.newToast(mensagem)
    }

    //endregion AuthenticationFunctions

    //region DatabaseFunctions

    override fun evaluationSent(task: Task<Void>) {
        if (task.isSuccessful()) {
            mensagem = EVALUATION_SENT_SUCESSFUL
            FragmentPrototype.replace(FragmentPrototype.REGISTER_FRAGMENT, RegisterEvaluationFragment())
            navigationService.loadFragment(FragmentPrototype.getFragment(FragmentPrototype.REGISTER_FRAGMENT))
        } else {
            treatExceptionEvaluation(task)
        }
        messageService.newToast(mensagem)
    }

    override fun editedEvaluation(task: Task<Void>) {
        if (task.isSuccessful()) {
            mensagem = EVALUATION_SENT_SUCESSFUL
            navigationService.finishActivity()
        } else {
            treatExceptionEvaluation(task)
        }
        messageService.newToast(mensagem)
    }

    fun treatExceptionEvaluation(task: Task<Void>) {
        try {
            throw task.exception!!
        } catch (e: FirebaseNetworkException) {
            mensagem = EVALUATION_SENT_NETWORK_EXCEPTION
            e.printStackTrace()
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            mensagem = EVALUATON_SENT_EXCEPTION_LOGIN_REQUIRED
            e.printStackTrace()
        } catch (e: Exception) {
            mensagem = EVALUATION_SENT_EXCEPTION
            e.printStackTrace()
        }
    }

    fun waitForResult(historicAdapter: HistoricRecyclerAdapter, evaluationModels: ArrayList<EvaluationModel>, upgradeComponents: UpgradeComponents) {
        this.upgradeComponents = upgradeComponents
        this.evaluationModels = evaluationModels
        this.historicAdapter = historicAdapter
    }

    override fun dataChange() {
        historicAdapter.dataChange()
    }

    override fun recoveredRatings(evaluationModels: ArrayList<EvaluationModel>) {
        for (i in evaluationModels.size - 1 downTo 0) {
            this.evaluationModels.add(evaluationModels.get(i))
        }
        upgradeComponents.requisitionCompleted()
        historicAdapter.notifyDataSetChanged()
    }

    override fun errorRetrieveReviews(toException: DatabaseException) {
        messageService.newToast("Houve um erro ao tentar recuperar suas avaliações: " + toException.message)
    }

    //endregion DatabaseFunctions

    //region StorageFunctions
    val IMAGE_UPLOAD_TYPE: Int = 1
    var quantity: Int = 0

    override fun newUploadFiles(quantity: Int, uploadType: Int) {
        this.quantity = quantity
        if (uploadType == IMAGE_UPLOAD_TYPE)
            messageService.newProgressDialog(IMAGES_UPLOAD_TITLE_PROGRESSS_DIALOG,
                    IMAGES_UPLOADING + "0" + IMAGES_PERCENT_UPLOAD)
    }

    private var taskSnapshotArrayList: ArrayList<UploadTask.TaskSnapshot> = ArrayList()

    override fun sendingFiles(task: UploadTask.TaskSnapshot) {
        if (!taskSnapshotArrayList.isEmpty()) {
            var lock = false
            for (taskSnapshot in taskSnapshotArrayList) {
                if (task.getTotalByteCount() == taskSnapshot.getTotalByteCount()) {
                    val index = taskSnapshotArrayList.indexOf(taskSnapshot)
                    taskSnapshotArrayList.set(index, task)
                    lock = true
                }
            }
            if (!lock) {
                taskSnapshotArrayList.add(task)
            }
        } else {
            taskSnapshotArrayList.add(task)
        }
        showProgress()
    }

    private fun showProgress() {
        var uploadProgress: String = ""
        var i: Int = 1
        for (aux in taskSnapshotArrayList) {
            val progress: Double = (100 * aux.bytesTransferred / aux.totalByteCount).toDouble()
            uploadProgress = uploadProgress + progress + "% Imagem " + i + "\n"
            i++
        }
        messageService.updateMessageProgressDialog(IMAGES_UPLOADING + uploadProgress)
    }

    var quantitySent: Int = 0

    override fun uploadComplete(task: Task<UploadTask.TaskSnapshot>) {
        quantitySent++
        if(quantitySent == quantity){
            taskSnapshotArrayList.clear()
            quantitySent = 0
            messageService.closeProgressDialog()
            messageService.newToast(IMAGES_SENT_SUCESSFUL)
        }
    }

    override fun fileUploadFailed(e: Exception) {

    }

    //endregion StorageFunctions

    interface UpgradeComponents {
        fun requisitionCompleted()
    }
}