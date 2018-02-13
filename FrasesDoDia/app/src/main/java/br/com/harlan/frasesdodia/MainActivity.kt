package br.com.harlan.frasesdodia

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var btnNovaFrase: Button
    lateinit var tvFrase: TextView
    val frases = arrayOf(
            "Nunca saberemos o quão forte somos até que ser forte seja a única escolha.",
            "Se a caminhada está difícil, é porque você está no caminho certo.",
            "No caminho entre as pedras o espinho protege a flor.",
            "Eu ainda não cheguei lá, mas estou mais perto do que ontem.",
            "Se eu não mudar o que faço hoje, todos os amanhãs serão iguais a ontem."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarComponentes()
    }

    private fun  escolherFrase(): String {
        val numeroRandomico = Random().nextInt(frases.size)
        return frases[numeroRandomico]
    }

    private fun inicializarComponentes() {
        btnNovaFrase = findViewById(R.id.btnNovaFrase)
        tvFrase = findViewById(R.id.tvFrase)
        adicionarEventos()
    }

    private fun adicionarEventos() {
        btnNovaFrase.setOnClickListener(View.OnClickListener {
            tvFrase.setText(escolherFrase())
        })
    }
}
