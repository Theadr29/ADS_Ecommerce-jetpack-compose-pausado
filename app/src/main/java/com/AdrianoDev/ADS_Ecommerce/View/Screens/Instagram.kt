package com.AdrianoDev.ADS_Ecommerce.View.Screens
import androidx.compose.foundation.clickable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.View.TopbarWithBack.TopBack
import com.AdrianoDev.ADS_Ecommerce.ViewModel.MyViewModel


@Composable
fun InstagramView(viewModel: MyViewModel) {


    Top() // TOP BAR COM BOTÃO DE VOLTAR

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(55.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.purple_777)),
            contentAlignment = Alignment.Center
        ) {
            val context = LocalContext.current
            Image(
                painter = painterResource(id = R.drawable.insta),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .offset(y = (-25).dp)
                    .clickable(onClick = {
                        viewModel.Instagram(context)
                    })
            )
        }
    }
}


@Composable()
fun Top(){
    TopBack(rememberNavController())
    }

@Preview
@Composable
fun Instapreview() {
    InstagramView(MyViewModel())

}