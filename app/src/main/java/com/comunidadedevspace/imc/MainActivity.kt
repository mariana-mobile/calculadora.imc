package com.comunidadedevspace.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
// import android.widget.TextView // Não é mais necessário para tvResultado como resultado final
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar // Importado para usar Snackbar
import com.google.android.material.textfield.TextInputEditText
// import com.google.android.material.textfield.TextInputLayout // Não é mais necessário referenciar aqui se não for manipular diretamente
import kotlin.math.pow

const val KEY_RESULT_IMC = "ResultActivity.KEY_IMC"

class MainActivity : AppCompatActivity() {

    private lateinit var edtPeso: TextInputEditText
    private lateinit var edtAltura: TextInputEditText
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        edtPeso = findViewById(R.id.edt_peso)
        edtAltura = findViewById(R.id.edt_altura)
        btnCalcular = findViewById(R.id.btn_calcular)
        btnLimpar = findViewById(R.id.btn_limpar)
    }

    private fun setupListeners() {
        btnCalcular.setOnClickListener {
            calcularIMC()
        }

        btnLimpar.setOnClickListener {
            limparCampos()
        }
    }

    private fun calcularIMC() {
        val pesoStr = edtPeso.text.toString()
        val alturaStr = edtAltura.text.toString()


        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            Snackbar.make(btnCalcular, R.string.erro_campos_vazios, Snackbar.LENGTH_LONG).show()
            return
        }

        try {
            val peso = pesoStr.toFloat()
            val altura = alturaStr.toFloat()

            if (peso <= 0 || altura <= 0) {
                Snackbar.make(btnCalcular, R.string.erro_valores_invalidos, Snackbar.LENGTH_LONG).show()
                return
            }

            val imc = calcularValorIMC(peso, altura)

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(KEY_RESULT_IMC, imc)
            startActivity(intent)

        } catch (e: NumberFormatException) {
            Snackbar.make(btnCalcular, R.string.erro_valores_invalidos, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun calcularValorIMC(peso: Float, altura: Float): Float {
        return peso / altura.pow(2)
    }

    private fun limparCampos() {
        when {
            edtPeso.hasFocus() -> {
                edtPeso.text?.clear()
            }
            edtAltura.hasFocus() -> {
                edtAltura.text?.clear()
            }
            else -> {
                edtPeso.text?.clear()
                edtAltura.text?.clear()
            }
        }
    }
}