package br.com.harlan.alcoolougasolina.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.com.harlan.alcoolougasolina.R
import br.com.harlan.alcoolougasolina.business.AlcoolOuGasolinaBusiness
import com.google.firebase.database.DatabaseReference

class MainActivity : AppCompatActivity() {
    lateinit var btnCalcular: Button
    lateinit var edtPrecoGasolina: EditText
    lateinit var edtPrecoAlcool: EditText
    lateinit var textResultado: TextView
    var precoGasolina: Double = 0.0
    var precoAlcool: Double = 0.0
    var resultado: String = ""
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarComponentes();
        adicionarEventos();
    }

    private fun adicionarEventos() {
        btnCalcular.setOnClickListener(View.OnClickListener {
            calcularMelhorCombustivel();
        })
    }

    private fun calcularMelhorCombustivel() {
        if(edtPrecoGasolina.text.isEmpty()==null && edtPrecoGasolina.text.equals("") && edtPrecoAlcool.text.isEmpty()==null && edtPrecoAlcool.text.equals("")) {
            precoGasolina = edtPrecoGasolina.text.toString().toDouble()
            precoAlcool = edtPrecoAlcool.text.toString().toDouble()
            resultado = AlcoolOuGasolinaBusiness().calcularRazao(precoGasolina, precoAlcool)
            textResultado.text = resultado + " está melhor!"
        }
        else
            Toast.makeText(this, "Preencha todos os campos antes de calcular.", Toast.LENGTH_LONG).show()
    }

    private fun inicializarComponentes() {
        btnCalcular = findViewById(R.id.btnCalcular)
        edtPrecoGasolina = findViewById(R.id.edtPrecoGasolina)
        edtPrecoAlcool = findViewById(R.id.edtPrecoAlcool)
        textResultado = findViewById(R.id.textResultado)
    }
}