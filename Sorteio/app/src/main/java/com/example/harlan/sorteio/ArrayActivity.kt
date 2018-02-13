package com.example.harlan.sorteio

import android.app.Dialog
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ArrayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_array)
        var nomes = arrayOf("Harlan","Vanessa","Leía", "Julie")
        var dialog: ProgressDialog = ProgressDialog(this)
        dialog.setTitle("Nomes")
        dialog.show()
        var texto: String = "Nomes no array:\n"
        for(i in 0..3)
            texto+=nomes[i]+"\n"
        dialog.setMessage(texto)
    }
}