package com.AdrianoDev.ADS_Ecommerce.Interfaces

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import java.util.*

class SpeechRecognitionManager(private val context: Context,private val editText: EditText) {
    private val mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

    val mSpeechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    }

    init {
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
    }

    fun startListening() {
        mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
    }

    fun stopListening() {
        mSpeechRecognizer.stopListening()
    }
}

class MyOnTouchListener(private val editText: EditText, private val speechRecognitionManager: SpeechRecognitionManager) : View.OnTouchListener {
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                speechRecognitionManager.stopListening()
                editText.hint = "0"
            }
            MotionEvent.ACTION_DOWN -> {
                editText.setText("")
                editText.hint = "Gravando"
                speechRecognitionManager.startListening()
            }
        }
        return false
    }
}

