package com.AdrianoDev.ADS_Ecommerce.View.Screens

import com.AdrianoDev.ADS_Ecommerce.Esquadrias

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.View.Screens.Calculadoras.NumberPicker

@Composable
fun EsquadriasScreen(navController: NavController) {

    val screenWith = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Top()


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(bottom = 50.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.purple_777)),
            state = rememberLazyListState()
        ) {
            item {

                Image(
                    painter = painterResource(id = R.drawable.imagemjanela),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds, //para usar fitCenter

                    modifier = Modifier
                        .size(screenWith / 2.4f)
                        .offset(y = (14).dp, x = (12).dp)

                        .clickable(onClick = {

                        })
                )
                Image(
                    painter = painterResource(id = R.drawable.descrijanela),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,

                    modifier = Modifier
                        .size(screenWith / 3.9f)
                        .offset(y = (-8).dp, x = (12).dp)
                        .aspectRatio(4 / 2f)


                )

                Image(
                    painter = painterResource(id = R.drawable.imagemjanela),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds, //para usar fitCenter
                    modifier = Modifier
                        .size(screenWith / 2.4f)
                        .offset(y = (-228).dp, x = (195).dp)

                        .clickable(onClick = {

                        })
                )

                Image(
                    painter = painterResource(id = R.drawable.descricaojane),
                    contentDescription = null,
                    modifier = Modifier
                        .size(screenWith / 3.9f)
                        .offset(y = (-250).dp, x = (195).dp)
                        .aspectRatio(4 / 2f),

                )


                Image(
                    painter = painterResource(id = R.drawable.portafullalumin),
                    contentScale = ContentScale.FillHeight, //para usar fitCenter

                    contentDescription = null,
                    modifier = Modifier
                        .size(screenWith / 2.3f)
                        .offset(y = (-252).dp, x = (12).dp)
                        .background(colorResource(id = R.color.white))
                        .clickable(onClick = {

                        })
                )

                Image(
                    painter = painterResource(id = R.drawable.descporalum),
                    contentDescription = null,
                    modifier = Modifier
                        .size(screenWith / 3.8f)
                        .offset(y = (-274).dp, x = (12).dp)
                        .aspectRatio(4 / 2f)

                )

                Image(
                    painter = painterResource(id = R.drawable.portafullalumin),
                    contentDescription = null,
                    modifier = Modifier
                        .size(screenWith / 2.3f)
                        .offset(y = (-503).dp, x = (188).dp)
                        .background(colorResource(id = R.color.white))

                        .clickable(onClick = {

                        })
                )

                Image(
                    painter = painterResource(id = R.drawable.descportaalumi),
                    contentDescription = null,
                    modifier = Modifier
                        .size(screenWith / 3.8f)
                        .offset(y = (-525).dp, x = (188).dp)
                        .aspectRatio(4 / 2f)

                )

                Image(
                    painter = painterResource(id = R.drawable.portaalumivi),
                    contentDescription = null,

                    modifier = Modifier
                        .size(screenWith / 2.3f)
                        .offset(y = (-539).dp, x = (12).dp)
                        .background(colorResource(id = R.color.white))

                        .clickable(onClick = {

                        })
                )
                Image(
                    painter = painterResource(id = R.drawable.descportaalumivi),
                    contentDescription = null,
                    modifier = Modifier
                        .size(screenWith / 3.4f)
                        .offset(y = (-567).dp, x = (12).dp)
                        .aspectRatio(4 / 2f)

                )

                Image(
                    painter = painterResource(id = R.drawable.portamadeira),
                    contentDescription = null,

                    modifier = Modifier
                        .size(screenWith / 2.3f)
                        .offset(y = (-801).dp, x = (191).dp)
                        .background(colorResource(id = R.color.white))

                        .clickable(onClick = {

                        })
                )
                Image(
                    painter = painterResource(id = R.drawable.descportamade),
                    contentDescription = null,
                    modifier = Modifier
                        .size(screenWith / 3.4f)
                        .offset(y = (-830).dp, x = (191).dp)
                        .aspectRatio(4 / 2f)

                )

                Image(
                    painter = painterResource(id = R.drawable.portamadeira),
                    contentDescription = null,

                    modifier = Modifier
                        .size(screenWith / 2.3f)
                        .offset(y = (-846).dp, x = (12).dp)
                        .background(colorResource(id = R.color.white))

                        .clickable(onClick = {

                        })
                )
                Image(
                    painter = painterResource(id = R.drawable.descportamadei),
                    contentDescription = null,
                    modifier = Modifier
                        .size(screenWith / 3.4f)
                        .offset(y = (-872).dp, x = (12).dp)
                        .aspectRatio(4 / 2f)

                )


            }

        }


    }
}

@Preview
@Composable
fun EsquadriasPreview() {
    val navController = rememberNavController()

    EsquadriasScreen(navController)
}