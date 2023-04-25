package com.AdrianoDev.ADS_Ecommerce.View.TopbarWithBack

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.View.HomeScreen
import com.AdrianoDev.ADS_Ecommerce.View.Screens.InstagramView
import com.AdrianoDev.ADS_Ecommerce.View.Screens.Sobre
import com.AdrianoDev.ADS_Ecommerce.View.Theme.Purple700
import com.AdrianoDev.ADS_Ecommerce.ViewModel.MyViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopBack(navController: NavController) {
    val screenWith = LocalConfiguration.current.screenWidthDp.dp

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                //TITULO O LOGO OU SEJA UMA IMAGEM!!
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.adslogo),
                        contentDescription = "Logo do App",
                        modifier = Modifier
                            .width(screenWith)
                            .offset(x =-29.dp)
                    )

                },
                backgroundColor = Purple700, // define a cor de fundo como azul

                modifier = Modifier
                    .width(screenWith)
                    .height(50.dp),

                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painterResource(id = R.drawable.voltar),
                            contentDescription = "MenuIcon",
                            tint = Color.Unspecified
                        )
                    }
                }
            )
        }
    ) {

    }
}


@Preview
@Composable
fun TopPreview(){
    TopBack(rememberNavController())
       

}

