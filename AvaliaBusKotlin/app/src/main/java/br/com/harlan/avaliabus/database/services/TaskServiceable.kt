package br.com.harlan.avaliabus.database.services

import br.com.harlan.avaliabus.model.EvaluationModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DatabaseException
import com.google.firebase.storage.UploadTask
import java.lang.Exception

interface TaskServiceable {
    //region AuthenticationFunctions
    fun registeredUser(task: Task<AuthResult>)
    fun userSavedDatabase(task: Task<Void>)
    fun sessionClosed()
    fun sessionStarted(task: Task<AuthResult>)
    fun passwordReset(task: Task<Void>)
    //endregion AuthenticationFunctions
    //region FileFunctions
    //fun fileSent()
    fun newUploadFiles(quantity: Int, uploadType: Int)
    fun sendingFiles(task: UploadTask.TaskSnapshot)
    fun uploadComplete(task: Task<UploadTask.TaskSnapshot>)
    fun fileUploadFailed(e: Exception)
    //endregion FileFunctions
    //region DatabaseFunctions
    fun evaluationSent(task: Task<Void>)
    fun editedEvaluation(task: Task<Void>)
    fun recoveredRatings(evaluationModels: ArrayList<EvaluationModel>)
    fun errorRetrieveReviews(toException: DatabaseException)
    fun dataChange()
    //endendregion DatabaseFunctions
}