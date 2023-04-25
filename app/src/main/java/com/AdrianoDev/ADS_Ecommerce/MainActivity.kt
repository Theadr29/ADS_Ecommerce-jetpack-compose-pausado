package com.AdrianoDev.ADS_Ecommerce
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.speech.RecognizerIntent
import android.view.*
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.view.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.AdrianoDev.ADS_Ecommerce.Esquadrias
import com.AdrianoDev.ADS_Ecommerce.MateriaisLajes
import com.AdrianoDev.ADS_Ecommerce.Minerais
import com.AdrianoDev.ADS_Ecommerce.View.HomeScreen
import com.AdrianoDev.ADS_Ecommerce.View.Navigation.Nav
import com.AdrianoDev.ADS_Ecommerce.View.Screens.initSize
import com.AdrianoDev.ADS_Ecommerce.View.Theme.ADS_EcommerceTheme
import com.AdrianoDev.ADS_Ecommerce.ViewModel.MyViewModel
import com.AdrianoDev.ADS_Ecommerce.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.AdrianoDev.ADS_Ecommerce.model.listadeprodutos

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.text.get

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private lateinit var produtos: MutableList<listadeprodutos>
    private lateinit var navView: NavigationView
    private lateinit var navigationView: NavigationView
    private lateinit var headerView: View
    private lateinit var nome: TextView
    private lateinit var imagem: ImageView
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
//
//    override fun onResume() {
//        super.onResume()
//            checkLoginState()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemBars()
//
//        if (!isUserLoggedIn()) {
//            // Se não estiver logado, inicia LoginActivity e finaliza a MainActivity
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//            return
//        }
//        checkLoginState()



        setContent {
                Nav()



        }

    }

//        private fun checkLoginState() {
//        val sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
//        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
//
//        if (!isLoggedIn) {
//            // Redirecionar o usuário para a tela de login, se necessário
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }
//
//
//    private fun isUserLoggedIn(): Boolean {
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        return currentUser != null
//    }



    private fun hideSystemBars() {

            val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
            windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }


        val android.content.Context.navigationBarHeight: Int
        get() {
            val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

            return if (Build.VERSION.SDK_INT >= 30) {
                windowManager
                    .currentWindowMetrics
                    .windowInsets
                    .getInsets(WindowInsets.Type.navigationBars())
                    .bottom

            } else {

                val currentDisplay = windowManager.defaultDisplay

                val appUsableSize = Point()
                val realScreenSize = Point()

                currentDisplay?.apply {
                    getSize(appUsableSize)
                    getRealSize(realScreenSize)
                }

                // navigation bar on the side
                if (appUsableSize.x < realScreenSize.x) {
                    return realScreenSize.x - appUsableSize.x
                }

                // navigation bar at the bottom
                return if (appUsableSize.y < realScreenSize.y) {
                    realScreenSize.y - appUsableSize.y
                } else 0
            }
        }

}






//        binding = ActivityMainBinding.inflate(layoutInflater)
//
//        setContentView(binding.root)
//

//


//
//

//
//
//        nome = headerView.findViewById<TextView?>(R.id.nomeusuario)
//        imagem = headerView.findViewById(R.id.fotodeperfil)
//        if (account != null) {
//            Glide.with(applicationContext).load(account.photoUrl).into(imagem)
//            nome.text = account.displayName
//
//        } else {
//
//        }
//
//
//        binding.appBarMain.menssagem.setOnClickListener {
//            Thread {
//                val app = application as App
//                val dao = app.db.listDao()
//                val listaDeProdutos = dao.getALL()
//                val gson = Gson()
//                runOnUiThread {
//                    val listaDeProdutosJson = gson.toJson(listaDeProdutos)
//                    startActivity(Intent(this, CupomDeCompra::class.java).apply {
//                        putExtra("listaDeProdutos", listaDeProdutosJson)
//                        putExtra("type", "type")
//                        putExtra("user", "$name")
//                        putExtra("imagem","$fotouser")
//
//                    })
//                }
//            }.start()
//
//
//        }
//

//

//
//       val button = headerView.findViewById<Button>(R.id.sair)
//
//        button.setOnClickListener {
//            val builder = AlertDialog.Builder(this)
//            builder.setMessage("Ao sair a sua lista de compras será apagada. Tem certeza que deseja sair?")
//            builder.setPositiveButton("Sim") { _, _ ->
//                GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
//                    .signOut()
//                    .addOnCompleteListener(this) {
//                        // Remove a chave isLoggedIn do SharedPreferences
//                        val sharedPref = this.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
//                        with(sharedPref.edit()) {
//                            remove("isLoggedIn")
//                            apply()
//                        }
//                        // Deleta todos os dados da tabela listadeprodutos
//                        CoroutineScope(Dispatchers.IO).launch {
//                            val app = application as App
//                            app.db.listDao().deleteAll()
//                        }
//                        // Inicia LoginActivity e finaliza MainActivity
//                        val intent = Intent(this, LoginActivity::class.java)
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                        startActivity(intent)
//                        finish()
//                    }
//            }
//            builder.setNegativeButton("Cancelar") { dialog, _ ->
//                dialog.dismiss()
//            }
//            val dialog = builder.create()
//            dialog.show()
//        }
//
//
//
//

//
//
//    private fun isUserLoggedIn(): Boolean {
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        return currentUser != null
//    }
//
//

//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
//
//    override fun onBackPressed() {
//        // não faz nada
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
//        with (sharedPref.edit()) {
//            putBoolean("isAppClosed", true)
//            apply()
//        }
//
//    }
//
//}