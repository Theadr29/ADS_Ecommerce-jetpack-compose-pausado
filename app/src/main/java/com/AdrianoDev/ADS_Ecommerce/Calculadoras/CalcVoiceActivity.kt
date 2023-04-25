@file:Suppress("DEPRECATED_IDENTITY_EQUALS")
package com.AdrianoDev.ADS_Ecommerce.Calculadoras

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.databinding.ActivityCalcvoiceBinding
import java.util.*

class CalcVoiceActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 100

    private lateinit var t1: TextToSpeech
    private lateinit var binding: ActivityCalcvoiceBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityCalcvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

            checkPermission()

        val editText = binding.editText

        val mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)

        val mSpeechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        }

        mSpeechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle?) {}

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(v: Float) {}

            override fun onBufferReceived(bytes: ByteArray?) {}

            override fun onEndOfSpeech() {}

            override fun onError(i: Int) {}

            override fun onResults(bundle: Bundle?) {
                val matches = bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    editText.setText(matches[0])
                }
            }

            override fun onPartialResults(bundle: Bundle?) {}

            override fun onEvent(i: Int, bundle: Bundle?) {}
        })

        editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Handler().postDelayed({
                        val str = editText.text.toString()
                        when (str) {
                            "uma caixa","uma caixa de piso","uma caixa de piso branco","um piso"-> {
                                editText.setText("1")
                                t1.speak("Uma caixa adicionada", TextToSpeech.QUEUE_FLUSH, null, null)
                            }
                            "duas caixas de piso branco", "duas caixas de piso","duas caixas" -> {
                                editText.setText("2")
                                t1!!.speak(
                                    "Duas caixas adicionadas",
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            }
                            "how are you" -> {
                                editText.setText("I'm a robot, I am never tired, sir")
                                t1!!.speak(
                                    "I'm a robot, I am never tired, sir",
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            }
                            "what can I ask you" -> {
                                editText.setText("You can ask me anything to help you")
                                t1!!.speak(
                                    "You can ask me anything to help you",
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            }
                            "tell me a joke" -> {
                                editText.setText("Two sheep said baa in the field, other one said shit I wanna say that")
                                t1!!.speak(
                                    "Two sheep said baa in the field, other one said shit I wanna say that",
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            }
                            "do you like Trump" -> {
                                editText.setText("I will never consider him as a role model")
                                t1!!.speak(
                                    "I will never consider him as a role model",
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            }
                            "what is the situation" -> {
                                editText.setText("There is an ambush, retreat sir")
                                t1!!.speak(
                                    "There is an ambush, retreat sir",
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            }
                            else -> {}
                        }
                    }, 1000)
                }

            override fun afterTextChanged(s: Editable) {}
        })
        t1 = TextToSpeech(
            getApplicationContext()
        ) { status ->
            if (status != TextToSpeech.ERROR) {
                t1!!.language = Locale("pt", "BR")
            }
        }


        binding.btn.setOnTouchListener(View.OnTouchListener { v, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    mSpeechRecognizer.stopListening()
                    editText.hint = "you will see input here"
                }
                MotionEvent.ACTION_DOWN -> {
                    editText.setText("")
                    editText.hint = "listening........"
                    mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
                }
            }
            false
        })
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