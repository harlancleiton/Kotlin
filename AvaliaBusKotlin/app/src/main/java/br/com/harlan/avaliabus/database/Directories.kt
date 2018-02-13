package br.com.harlan.avaliabus.database

import br.com.harlan.avaliabus.singleton.FirebaseSingleton
import com.google.firebase.database.DatabaseReference

interface Directories {
    val USER_CHILD: String
        get() = "usuarios"
    val CURRENT_USER_ID: String
        get() = FirebaseSingleton.getFirebaseAuth().getCurrentUser()!!.getUid()
    val EVALUATION_CHILD: String
        get() = "avaliacoes"
    val EVALUATION_CHILD_COMPLETE: DatabaseReference
        get() = FirebaseSingleton.getDatabaseReference()!!.child(EVALUATION_CHILD).child(CURRENT_USER_ID)
    val IMAGES_STORAGE_CHILD: String
        get() = "imagens" + "/" + EVALUATION_CHILD + "/" + CURRENT_USER_ID + "/"
    val IMAGE_EVALUATION_UPLOAD_TYPE: Int
        get() = 1
}