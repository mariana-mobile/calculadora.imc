package com.comunidadedevspace.imc

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val btnVoltar = findViewById<Button>(R.id.btn_voltar)
        btnVoltar.setOnClickListener {
            finish()
        }

        val result = intent.getFloatExtra(KEY_RESULT_IMC, 0f)

        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvClassificacao = findViewById<TextView>(R.id.tv_classificacao)
        val tvMensagemCuidado = findViewById<TextView>(R.id.tv_mensagem_cuidado)

        tvResult.text = String.format("%.2f", result)

        val classificacao: String
        val mensagemCuidado: String
        var classificationColor = Color.WHITE

        when {
            result < 18.5f -> {
                classificacao = "Magreza"
                mensagemCuidado = "Você está abaixo do peso ideal. Considere consultar um nutricionista para orientações sobre uma alimentação adequada para ganho de peso saudável."
                classificationColor = Color.parseColor("#FFC107")
            }
            result < 25f -> {
                classificacao = "Normal"
                mensagemCuidado = "Parabéns! Você está com peso saudável. Continue mantendo hábitos alimentares equilibrados e prática regular de atividade física."
                classificationColor = Color.parseColor("#4CAF50")
            }
            result < 30f -> {
                classificacao = "Sobrepeso"
                mensagemCuidado = "Você está com Sobrepeso. Recomenda-se adotar uma alimentação balanceada e aumentar a prática de atividades físicas para reduzir o peso gradualmente."
                classificationColor = Color.parseColor("#FF9800")
            }
            result < 40f -> {
                classificacao = "Obesidade"
                mensagemCuidado = "Você está com Obesidade. É importante procurar um médico para avaliação e orientações sobre tratamento e cuidados necessários." // Usando uma mensagem genérica para Obesidade
                classificationColor = Color.parseColor("#F44336")
            }
            else -> {
                classificacao = "Obesidade Grave"
                mensagemCuidado = "Você está com Obesidade Grave. É essencial procurar um médico com urgência para avaliação e orientações sobre tratamento adequado." // Mensagem para Obesidade Grave
                classificationColor = Color.parseColor("#D32F2F")
            }
        }

        tvClassificacao.text = classificacao
        tvClassificacao.setTextColor(classificationColor)
        tvMensagemCuidado.text = mensagemCuidado

    }
}