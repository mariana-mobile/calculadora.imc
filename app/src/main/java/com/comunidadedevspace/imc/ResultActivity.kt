package com.comunidadedevspace.imc

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val KEY_RESULT_IMC = "ResultActivity.KEY_IMC"

class ResultActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = intent.getFloatExtra(KEY_RESULT_IMC, 0f)

        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvClassificacao = findViewById<TextView>(R.id.tv_classificacao)

        tvResult.text = result.toString()

        val classificacao: String = when {
            result <= 18.5f -> "MAGREZA"
            result <= 24.9f -> "NORMAL"
            result <= 29.9f -> "SOBREPESO"
            result <= 39.9f -> "OBESIDADE"
            else -> "OBESIDADE GRAVE"
        }
        val classificacaoCores = mapOf(
            "MAGREZA" to "#FF0000",
            "NORMAL" to "#008000",
            "SOBREPESO" to "#FFFF00",
            "OBESIDADE" to "#FFA500",
            "OBESIDADE GRAVE" to "#FF0000"
        )

        tvClassificacao.text = classificacao
        tvClassificacao.setTextColor(Color.parseColor(classificacaoCores[classificacao] ?: "#FFFFFF"))
    }
}
