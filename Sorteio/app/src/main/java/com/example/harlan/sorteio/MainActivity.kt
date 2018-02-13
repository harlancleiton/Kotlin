package com.example.harlan.sorteio

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent: Intent = Intent(this, ArrayActivity::class.java)
        startActivity(intent)
    }

    fun btnJogarClicado(view: View){
        var txtNumero = findViewById<TextView>(R.id.txtNumero)
        txtNumero.setText("Teste")
        
    }
}