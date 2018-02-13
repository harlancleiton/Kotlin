package com.example.harlan.appkotlin.TiposDeClasses

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.TextView
import com.example.harlan.appkotlin.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TipoDeClassesActivity : AppCompatActivity() {

    lateinit var cachorro: Cachorro
    lateinit var tvCorCachorro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipo_de_classes)
        tvCorCachorro = findViewById(R.id.textView)
        cachorro = Cachorro()
        cachorro.cor="Preto"
        tvCorCachorro.setText("Cor do cachorro: "+cachorro.cor)
        var dataBase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        dataBase.child("Cor cachorro").setValue(cachorro.cor)
    }
}