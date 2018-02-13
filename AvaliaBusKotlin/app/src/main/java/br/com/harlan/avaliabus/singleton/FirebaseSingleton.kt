package br.com.harlan.avaliabus.singleton

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object FirebaseSingleton {

    private var databaseReference: DatabaseReference? = null
        /*get() {
            if (databaseReference == null)
                databaseReference = FirebaseDatabase.getInstance().reference
            return databaseReference!!
        }*/
    private var firebaseAuth: FirebaseAuth? = null
        /*get() {
            if (firebaseAuth == null)
                firebaseAuth = FirebaseAuth.getInstance()
            return firebaseAuth!!
        }*/
    private var firebaseStorage: FirebaseStorage? = null
        /*get() {
            if(firebaseStorage == null)
                firebaseStorage = FirebaseStorage.getInstance()
            return firebaseStorage!!
        }*/
    private var storageReference: StorageReference? = null
        /*get() {
            if(storageReference == null)
                storageReference = firebaseStorage!!.getReference()
            return storageReference!!
        }*/

    fun getDatabaseReference(): DatabaseReference? {
        if(databaseReference == null)
            databaseReference = FirebaseDatabase.getInstance().getReference()
        return databaseReference!!
    }

    fun getFirebaseAuth(): FirebaseAuth {
        if (firebaseAuth == null)
            firebaseAuth = FirebaseAuth.getInstance()
        return firebaseAuth!!
    }

    fun getStorageReference(): StorageReference {
        if (storageReference == null)
            storageReference = getFirebaseStorage().reference
        return storageReference as StorageReference
    }

    fun getFirebaseStorage(): FirebaseStorage {
        if (firebaseStorage == null)
            firebaseStorage = FirebaseStorage.getInstance()
        return firebaseStorage as FirebaseStorage
    }
}