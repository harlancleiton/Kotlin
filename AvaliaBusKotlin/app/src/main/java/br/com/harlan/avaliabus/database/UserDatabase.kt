package br.com.harlan.avaliabus.database

import br.com.harlan.avaliabus.database.services.TaskServiceable
import br.com.harlan.avaliabus.model.UserModel
import br.com.harlan.avaliabus.singleton.FirebaseSingleton
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class UserDatabase : BaseDatabase, Directories {

    constructor(taskService: TaskServiceable) : super(taskService)

    fun registerUser(userModel: UserModel) {
        val firebaseAuth: FirebaseAuth = FirebaseSingleton.getFirebaseAuth()
        firebaseAuth.createUserWithEmailAndPassword(userModel.email, userModel.password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    taskService.registeredUser(task)
                    if (task.isSuccessful) {
                        userModel.objectId = FirebaseSingleton.getFirebaseAuth().currentUser!!.uid
                        saveUserDatabase(userModel)
                    }
                }
    }

    private fun saveUserDatabase(userModel: UserModel) {
        val databaseReference = FirebaseSingleton.getDatabaseReference()!!
        userModel.objectId = FirebaseSingleton.getFirebaseAuth().currentUser!!.uid
        databaseReference.child(userModel.classChild).child(userModel.objectId).setValue(userModel)
                .addOnCompleteListener { task: Task<Void> ->
                    taskService.userSavedDatabase(task)
                }
    }

    fun validateLogin(userModel: UserModel) {
        val firebaseAuth: FirebaseAuth = FirebaseSingleton.getFirebaseAuth()
        firebaseAuth.signInWithEmailAndPassword(userModel.email, userModel.password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    taskService.sessionStarted(task)
                }
    }

    fun userLogOut() {
        val firebaseAuth: FirebaseAuth = FirebaseSingleton.getFirebaseAuth()
        firebaseAuth.signOut()
        taskService.sessionClosed()
    }

    fun redefinePassword(email: String) {
        val firebaseAuth: FirebaseAuth = FirebaseSingleton.getFirebaseAuth()
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task: Task<Void> ->
                    taskService.passwordReset(task)
                }
    }
}