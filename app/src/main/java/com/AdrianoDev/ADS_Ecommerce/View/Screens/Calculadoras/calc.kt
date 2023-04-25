package com.AdrianoDev.ADS_Ecommerce.View.Screens.Calculadoras

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.AdrianoDev.ADS_Ecommerce.Interfaces.MyOnTouchListener
import com.AdrianoDev.ADS_Ecommerce.Interfaces.SpeechRecognitionManager
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.View.Screens.Top
import com.AdrianoDev.ADS_Ecommerce.ViewModel.MyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.lang.Math.abs
import java.util.*

private lateinit var context: Context


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SpeechRecognitionButton(speechRecognizer: SpeechRecognizer, onTextChanged: (String) -> Unit) {
    val context = LocalContext.current
    var isListening by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }


    Column {
        Button(
            onClick = {
                if (!isListening) {
                    inputText = ""
                    isListening = true
                    val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                    }
                    speechRecognizer.startListening(recognizerIntent)
                } else {
                    isListening = false
                    speechRecognizer.stopListening()
                }
            },
            enabled = !isListening
        ) {
            Text(text = if (isListening) "Listening..." else "Start Listening")
        }
        Text(text = inputText, modifier = Modifier.padding(top = 16.dp))
        DisposableEffect(Unit) {
            onDispose {
                speechRecognizer.destroy()
            }
        }
    }


}




@Composable

private fun calculadora(textView: MutableState<Double>, resultTextView: MutableState<Double>, editTextValue: TextFieldValue) {

    var textState by remember { mutableStateOf(TextFieldValue("")) }

    val mtpiso = editTextValue.text.toIntOrNull() ?: 0

    val qtddemetro = mtpiso.toDouble() * 2.68
    textView.value = qtddemetro

    val multipli = qtddemetro

    val resultado = multipli.toString().toDouble() * 33.00
    resultTextView.value = resultado
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Quantidade de caixas de piso:",
            fontSize = 20.sp
        )
        TextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )

        )
        Text(
            text = "Resultado 1: ${textView.value.formatDecimal(2)}",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Resultado 2: ${resultTextView.value.formatDecimal(2)}",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }

}

private fun Double.formatDecimal(digits: Int): String {
    return String.format("%.${digits}f", this)
}

//@Composable
//private fun SpeechButton(myOnTouchListener: MyOnTouchListener,  editText: EditText, mSpeechRecognizer: SpeechRecognizer,  mSpeechRecognizerIntent: Intent) {
//     fun onTouch(v: View, event: MotionEvent): Boolean {
//        when (event.action) {
//            MotionEvent.ACTION_UP -> {
//                mSpeechRecognizer.stopListening()
//                editText.hint = "you will see input here"
//            }
//            MotionEvent.ACTION_DOWN -> {
//                editText.setText("")
//                editText.hint = "listening........"
//                mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
//            }
//        }
//        return false
//    }
//}



