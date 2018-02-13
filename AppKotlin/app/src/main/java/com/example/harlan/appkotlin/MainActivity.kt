package com.example.harlan.appkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.harlan.appkotlin.TiposDeClasses.TipoDeClassesActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent: Intent = Intent(this, TipoDeClassesActivity::class.java)
        startActivity(intent)
    }
}