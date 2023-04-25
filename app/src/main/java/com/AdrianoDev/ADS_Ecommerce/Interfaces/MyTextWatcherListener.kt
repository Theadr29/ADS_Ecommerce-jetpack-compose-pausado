package com.AdrianoDev.ADS_Ecommerce.Interfaces

import android.content.Context
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MyTextWatcherListener(val context: Context ,val editText: EditText, val textView: TextView, val resultTextView: TextView) : TextWatcher {

    private lateinit var t1: TextToSpeech

    init {
        t1 = TextToSpeech(context) { status ->
            if (status != TextToSpeech.ERROR) {
                t1!!.language = Locale("pt", "BR")
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Do nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Do something when the text changes
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            val str = s.toString()
            when (str) {
                "uma caixa", "uma caixa de piso", "uma caixa de piso branco", "um piso" -> {
                    editText.setText("1")
                    t1.speak("Uma caixa adicionada", TextToSpeech.QUEUE_FLUSH, null, null)
                    calculadora(textView, resultTextView,editText)
                }
                else -> {}
            }

        }

    }


    override fun afterTextChanged(s: Editable?) {
        // Do nothing
    }


}
//
private fun calculadora(textView: TextView?, resultTextView: TextView?, editText: EditText?) {
    if (textView == null || resultTextView == null || editText == null) {
        return
    }
    val mtpiso = editText.text.toString().toIntOrNull() ?: 0

    val qtddemetro = mtpiso.toDouble() * 2.68
    textView.text = String.format("%.2f", qtddemetro)

    val multipli = qtddemetro

    val resultado = multipli.toString().toDouble() * 33.00
    resultTextView.text = String.format("%.2f", resultado)
}


//"duas caixas de piso branco", "duas caixas de piso","duas caixas" -> {
//    editText.setText("2")
//    t1!!.speak(
//        "Duas caixas adicionadas",
//        TextToSpeech.QUEUE_FLUSH,
//        null,
//        null
//    )
//}
//"how are you" -> {
//    editText.setText("I'm a robot, I am never tired, sir")
//    t1!!.speak(
//        "I'm a robot, I am never tired, sir",
//        TextToSpeech.QUEUE_FLUSH,
//        null,
//        null
//    )
//}
//"what can I ask you" -> {
//    editText.setText("You can ask me anything to help you")
//    t1!!.speak(
//        "You can ask me anything to help you",
//        TextToSpeech.QUEUE_FLUSH,
//        null,
//        null
//    )
//}
//"tell me a joke" -> {
//    editText.setText("Two sheep said baa in the field, other one said shit I wanna say that")
//    t1!!.speak(
//        "Two sheep said baa in the field, other one said shit I wanna say that",
//        TextToSpeech.QUEUE_FLUSH,
//        null,
//        null
//    )
//}
//"do you like Trump" -> {
//    editText.setText("I will never consider him as a role model")
//    t1!!.speak(
//        "I will never consider him as a role model",
//        TextToSpeech.QUEUE_FLUSH,
//        null,
//        null
//    )
//}
//"what is the situation" -> {
//    editText.setText("There is an ambush, retreat sir")
//    t1!!.speak(
//        "There is an ambush, retreat sir",
//        TextToSpeech.QUEUE_FLUSH,
//        null,
//        null
//    )
//}