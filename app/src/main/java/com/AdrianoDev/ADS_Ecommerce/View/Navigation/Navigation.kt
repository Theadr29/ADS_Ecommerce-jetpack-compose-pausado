package com.AdrianoDev.ADS_Ecommerce.View.Navigation

import SplashArt
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.AdrianoDev.ADS_Ecommerce.View.HomeScreen
import com.AdrianoDev.ADS_Ecommerce.View.Screens.Calculadoras.CalcVoiceScreen
import com.AdrianoDev.ADS_Ecommerce.View.Screens.Calculadoras.NumberPicker
import com.AdrianoDev.ADS_Ecommerce.View.Screens.EsquadriasScreen
import com.AdrianoDev.ADS_Ecommerce.View.Screens.InstagramView
import com.AdrianoDev.ADS_Ecommerce.View.Screens.PisosScreen
import com.AdrianoDev.ADS_Ecommerce.View.Screens.Sobre
import com.AdrianoDev.ADS_Ecommerce.ViewModel.MyViewModel
import kotlinx.coroutines.launch

@Composable

fun Nav() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashArt(navController, viewModel ()
            )
        }
        composable("home") {
            HomeScreen(MyViewModel(),navController)
        }

        composable("insta"){
            InstagramView(MyViewModel())
        }

        composable("pisos"){
            PisosScreen(navController)
        }

        composable("esquadrias"){
            EsquadriasScreen(navController)
        }

        composable("sobre"){
            Sobre(MyViewModel())
        }
        composable("calc"){
            CalcVoiceScreen()
        }
        activity("calcvoice"){

        }
    }
}

