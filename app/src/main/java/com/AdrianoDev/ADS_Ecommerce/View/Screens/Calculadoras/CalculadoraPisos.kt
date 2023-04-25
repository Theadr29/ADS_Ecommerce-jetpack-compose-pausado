package com.AdrianoDev.ADS_Ecommerce.View.Screens.Calculadoras

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.NumberPicker
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DisplayMode.Companion.Picker
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.View.HomeScreen
import com.AdrianoDev.ADS_Ecommerce.View.Screens.Top
import com.AdrianoDev.ADS_Ecommerce.ViewModel.MyViewModel
import com.AdrianoDev.ADS_Ecommerce.databinding.ActivityItemBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun calculadoraPiso() {
//
//
//}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CutPasteId")
@Composable
fun CalcVoiceScreen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }
    val speechRecognizerIntent = remember {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        }
    }
    val textToSpeech = remember { TextToSpeech(context, null) }
    var editTextValue = remember { mutableStateOf("") }
    val editTextHint = remember { mutableStateOf("you will see input here") }
    val isListening = remember { mutableStateOf(false) }

    val onRecognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(bundle: Bundle?) {}

        override fun onBeginningOfSpeech() {}

        override fun onRmsChanged(v: Float) {}

        override fun onBufferReceived(bytes: ByteArray?) {}

        override fun onEndOfSpeech() {}

        override fun onError(i: Int) {}

        override fun onResults(bundle: Bundle?) {
            val matches = bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (!matches.isNullOrEmpty()) {
                editTextValue.value = matches[0]
            }
        }

        override fun onPartialResults(bundle: Bundle?) {}

        override fun onEvent(i: Int, bundle: Bundle?) {}
    }

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            coroutineScope.launch {
                val str = editTextValue.value.toString()
                when (str) {
                    "quatro" -> {
                        editTextValue.value = "Hello, how can i help you"
                        textToSpeech.speak(
                            "Hello, how can i help you",
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            null
                        )
                    }

                    else -> {
                        editTextValue.value = "I'm sorry, I didn't understand what you said"
                        textToSpeech.speak(
                            "I'm sorry, I didn't understand what you said",
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            null
                        )
                    }
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Press the button and speak",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = editTextValue.value,
            onValueChange = { newValue ->
                editTextValue.value = newValue
                textWatcher.onTextChanged(newValue, 0, 0, 0)
            },
            label = { Text(text = editTextHint.value) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .onFocusChanged {
                    if (it.isFocused) {
                        editTextHint.value = ""
                    } else if (editTextValue.value.isEmpty()) {
                        editTextHint.value = "you will see input here"
                    }
                }
        )

        Button(
            onClick = {
                if (isListening.value) {
                    speechRecognizer.stopListening()
                    isListening.value = false
                } else {
                    speechRecognizer.startListening(speechRecognizerIntent)
                    isListening.value = true
                }
            }
        ) {
            Text(text = if (isListening.value) "Stop" else "Start")
        }
    }

    DisposableEffect(key1 = speechRecognizer) {
        speechRecognizer.setRecognitionListener(onRecognitionListener)
        onDispose {
            speechRecognizer.destroy()
        }
    }


    DisposableEffect(key1 = textToSpeech) {
        textToSpeech.language = Locale.getDefault()
        onDispose {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
    }
}
@Preview
@Composable
fun CalculadoraPreview() {
    CalcVoiceScreen()
}
//"what is your name" -> {
//    editTextValue.value = "My name is jarvis sir"
//    textToSpeech.speak(
//        "My name is jarvis sir",
//        TextToSpeech.QUEUE_FLUSH,
//        null,
//        null
//    )
//}
//"how are you" -> {
//    editTextValue.value = "I'm a robot, I am never tired, sir"
//    textToSpeech.speak(
//        "I'm a robot, I am never tired, sir",
//        TextToSpeech.QUEUE_FLUSH,
//        null,
//        null
//    )
//}
//"what can I ask you" -> {
//    editTextValue.value = "You can ask me anything to help you"
//    textToSpeech.speak(
//        "You can ask me anything to help you",
//        TextToSpeech.QUEUE_FLUSH,
//        null,
//        null
//    )
//}
//"who created you" -> {
//    editTextValue.value = "I was created by OpenAI, sir"
//    textToSpeech.speak(
//        "I was created by OpenAI, sir",
//        TextToSpeech.QUEUE_FLUSH,
//        null,
//        null
//    )
//}

//AndroidView(
//            factory = { context ->
//                ActivityItemBinding.inflate(LayoutInflater.from(context)).root
//            },
//            modifier = Modifier.fillMaxSize(),
//            update = { view ->
//                // adiciona a lógica de programação à view aqui
//                view.findViewById<NumberPicker>(R.id.editTextNumber).minValue = 0
//                view.findViewById<NumberPicker>(R.id.editTextNumber).maxValue = 30

