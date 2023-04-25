package com.AdrianoDev.ADS_Ecommerce.Calculadoras

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.AdrianoDev.ADS_Ecommerce.App
import com.AdrianoDev.ADS_Ecommerce.model.listadeprodutos
import com.AdrianoDev.ADS_Ecommerce.CupomDeCompra
import com.AdrianoDev.ADS_Ecommerce.Interfaces.MyOnTouchListener
import com.AdrianoDev.ADS_Ecommerce.Interfaces.MyTextWatcherListener
import com.AdrianoDev.ADS_Ecommerce.Interfaces.SpeechRecognitionManager
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.databinding.ActivityItemBinding
import java.util.*

//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class ItemPiso() : AppCompatActivity() {

    private lateinit var context: Context
    private val PERMISSION_REQUEST_CODE = 100
    private lateinit var composeView: ComposeView
    private lateinit var preferences: SharedPreferences
    private lateinit var binding: ActivityItemBinding
    private lateinit var t1: TextToSpeech
    private lateinit var primaryButton: Button
    private lateinit var secondaryButton: Button
    private lateinit var numberTextView: TextView



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        checkPermission()

        context = this


        val editText = binding.editTextNumber
        val textView2 = binding.textView2
        val resultadofinal = binding.resultadofinal
        binding.textView2.maxLines = 1
      
        val speechRecognitionManager = SpeechRecognitionManager(this,editText)
        val myOnTouchListener = MyOnTouchListener(editText, speechRecognitionManager)
        binding.calcular.setOnTouchListener(myOnTouchListener)

        val listener = MyTextWatcherListener(context, editText,textView2,resultadofinal)
        editText.addTextChangedListener(listener)


        calculadora()

        val dados = intent.extras

        var piso = dados?.getInt("piso")
        binding.imagemProduto.setImageResource(piso!!)

        var descricao = dados?.getInt("descricao")
        binding.descricao.setImageResource(descricao!!)

        binding.voltar2.setOnClickListener {
            finish()
        }


    }

    private fun calculadora() {

        val mtpiso = binding.editTextNumber.text.toString().toIntOrNull() ?: 0

        val qtddemetro = mtpiso.toDouble() * 2.68
        binding.textView2.text = String.format("%.2f", qtddemetro)

        val multipli = qtddemetro

        val resultado = multipli.toString().toDouble() * 33.00
        binding.resultadofinal.text = String.format("%.2f", resultado)

        val dados = intent.extras

        var piso = dados?.getInt("piso")

        var descricao = dados?.getInt("descricao")

        var nome = dados?.getInt("nome")
//        var name = dados?.getString("user")
//
//        var fotouser = dados?.getString("imagem")

        val updateId = intent.extras?.getInt("updateId")
        val calcularButton = binding.calcular
        val comprarButton = binding.comprar



        binding.comprar.setOnClickListener {

            if (resultado == 0.0) {
                // Show an error message to the user or prevent submission in some other way
                Toast.makeText(
                    this,
                    "Por favor, selecione uma quantidade válida.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            Thread {


                val app = application as App
                val dao = app.db.listDao()


                dao.insert(

                    listadeprodutos(
                        nome = nome!!,
                        foto = piso!!,
                        quantidadeCx = "$mtpiso",
                        quantidadeMt = "$qtddemetro",
                        valorUni = "33,00",
                        valordopiso = "$resultado",
                        id = updateId!!,
                        type = "type",
                        name = "name",
                        photoUrl = "fotouser"
                    )
                )
                runOnUiThread {
                    val Intent = Intent(this, CupomDeCompra::class.java)
                    Intent.putExtra("caixas", mtpiso)
                    Intent.putExtra("qtdmetro", qtddemetro)
                    Intent.putExtra("valorpiso", resultado)
                    Intent.putExtra("piso", piso)
                    Intent.putExtra("descricao", descricao)
                    Intent.putExtra("nome", nome)
                    Intent.putExtra("type", "type")
                    Intent.putExtra("user", "name")
                    Intent.putExtra("imagem", "fotouser")


                    startActivity(Intent)

                }

            }.start()


        }


    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) === PackageManager.PERMISSION_GRANTED)
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }
}
//
















