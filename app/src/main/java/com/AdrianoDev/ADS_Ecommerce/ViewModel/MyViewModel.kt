package com.AdrianoDev.ADS_Ecommerce.ViewModel

import SplashArt
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.XmlResourceParser
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.view.View
import android.widget.Adapter
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.ui.AppBarConfiguration
import com.AdrianoDev.ADS_Ecommerce.App
import com.AdrianoDev.ADS_Ecommerce.LoginActivity
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.View.HomeScreen
import com.AdrianoDev.ADS_Ecommerce.View.Screens.InstagramView
import com.AdrianoDev.ADS_Ecommerce.View.Screens.Sobre
import com.AdrianoDev.ADS_Ecommerce.databinding.ActivityMainBinding
import com.AdrianoDev.ADS_Ecommerce.model.listadeprodutos
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

class MyViewModel : ViewModel() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private lateinit var produtos: MutableList<listadeprodutos>

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
     var account: GoogleSignInAccount? = null



    fun navigateToHome(navController: NavController) {
        viewModelScope.launch {
            delay(3000L)
            navController.navigate("home")
        }
    }


    fun navigateToSobre(navController: NavController) {
        viewModelScope.launch {
            navController.navigate("sobre")
        }
    }

    fun Instagram(context: Context) {
        val uri = Uri.parse("https://www.instagram.com/ads_ecommerce/")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.instagram.android")
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/ads_ecommerce/")
                )
            )
        }
    }

    fun AccountGoogle(context: Context) {
        auth = FirebaseAuth.getInstance()

        val res = context.resources
        val packageName = context.packageName
        val clientIdResId = res.getIdentifier("default_web_client_id", "string", packageName)
        val clientId = res.getString(clientIdResId)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)
        account = GoogleSignIn.getLastSignedInAccount(context)
    }



    fun Exit(context: Context) {

        val builder = AlertDialog.Builder(context)
        builder.setMessage("Ao sair a sua lista de compras será apagada. Tem certeza que deseja sair?")
        builder.setPositiveButton("Sim") { _, _ ->
            GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN)
                .signOut()
                .addOnCompleteListener {
                    // Remove a chave isLoggedIn do SharedPreferences
                    val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        remove("isLoggedIn")
                        apply()
                    }
                    // Deleta todos os dados da tabela listadeprodutos
                    CoroutineScope(Dispatchers.IO).launch {
                        val app = context.applicationContext as App
                        app.db.listDao().deleteAll()
                    }
                    // Inicia LoginActivity e finaliza MainActivity
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
        }
        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}



