package br.com.harlan.alcoolougasolina.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseSingleton{ //Também é o nome do arquivo
    var databaseReference: DatabaseReference = getVarDatabaseReference()
    private fun getVarDatabaseReference(): DatabaseReference{
        if(databaseReference==null)
            databaseReference = FirebaseDatabase.getInstance().getReference()
        return databaseReference
    }
}