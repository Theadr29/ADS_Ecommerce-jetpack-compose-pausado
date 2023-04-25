package com.AdrianoDev.ADS_Ecommerce.View
import android.annotation.SuppressLint
import android.util.DisplayMetrics
import android.view.View
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.View.Menu.BottomSheetItem
import com.AdrianoDev.ADS_Ecommerce.View.Menu.bottomSheetItems
import com.AdrianoDev.ADS_Ecommerce.View.Theme.Purple700
import com.AdrianoDev.ADS_Ecommerce.ViewModel.MyViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.border
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*
import androidx.navigation.NavOptions
import kotlin.ranges.coerceAtMost


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(viewModel: MyViewModel,navController: NavController) {

    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetItemList = mutableListOf<BottomSheetItem>()

        val screenWith = LocalConfiguration.current.screenWidthDp.dp

        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val peekHeight = screenHeight / 19f
        val peekWidth = screenWith / 16
        //Lets define bottomSheetScaffoldState which will hold the state of Scaffold
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        )
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
//        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_right)
//        .setPopExitAnim(R.anim.slide_out_left)
        .build()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp,

        ),



            sheetBackgroundColor = Color(android.graphics.Color.parseColor("#4D3B86")),// Defina a cor de fundo como #3C2B67
            sheetContentColor = MaterialTheme.colors.onSurface,
            sheetPeekHeight = peekHeight,
            sheetContent = {


                Column(
                    content = {

                        Spacer(modifier = Modifier.padding(bottom = 5.dp))

                        Image(
                            painter = painterResource(id = R.drawable.arrastapracima),
                            contentDescription = "Logo do App",
                            modifier = Modifier
                                .width(100.dp)
                                .align(Alignment.CenterHorizontally)

                        )

                        LazyVerticalGrid(
                            //cells = GridCells.Fixed(3)
                            columns = GridCells.Fixed(3), //https://developer.android.com/jetpack/compose/lists
                        ) {
                            items(bottomSheetItems.size, itemContent = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 24.dp)
                                        .clickable {},
                                ) {
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    Icon(
                                        bottomSheetItems[it].icon,
                                        bottomSheetItems[it].title,
                                        tint = Color.White,
                                        modifier = Modifier.clickable { bottomSheetItems[it].onClick.invoke(viewModel, navController) }
                                        )

                                    Spacer(modifier = Modifier.padding(8.dp))
                                    Text(text = bottomSheetItems[it].title, color = Color.White)
                                }

                            })
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)

                        //.background(Color(0xFF6650a4))

                )
            },
            topBar = {
                TopAppBar(
                    //TITULO O LOGO OU SEJA UMA IMAGEM!!
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.adslogo),
                            contentDescription = "Logo do App",
                            modifier = Modifier
                                .width(screenWith)
                        )

                    },
                    backgroundColor = Purple700, // define a cor de fundo como azul

                    modifier = Modifier
                        .width(screenWith)
                        .height(50.dp)
                )
            }
        ) {

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.purple_777))
                    .padding(16.dp)
            ) {
                val deviceWidth = maxWidth
                val deviceHeight = maxHeight

                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (
                        melhorPreco, arrastePara, homeScroll, entreEmContato
                    ) = createRefs()

                    Image(
                        painter = painterResource(id = R.drawable.omelhorpreco),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth, //para usar fitCenter
                        modifier = Modifier.constrainAs((melhorPreco)) {
                            top.linkTo(parent.top, 17.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(homeScroll.top, -40.dp)

                            width = Dimension.value(deviceWidth - 14.dp)
                            height =
                                Dimension.value((deviceHeight / 3.2f).coerceAtMost(deviceWidth))

                        }
                    )
//                Image(
//                    painter = painterResource(id = R.drawable.arrasteodedoparaolado),
//                    contentDescription = null,
//                    contentScale = ContentScale.Fit,
//                    modifier = Modifier.constrainAs((arrastePara)) {
//                        top.linkTo(melhorPreco.bottom,10.dp)
//                        start.linkTo(parent.start, 165.dp)
//                        end.linkTo(parent.end)
//                        bottom.linkTo(homeScroll.top, -15.dp)
//                        width = Dimension.value(deviceWidth / 1.7f)
//                        height = Dimension.value((deviceHeight / 30).coerceAtMost(deviceWidth))
//
//                    })
                    //ScorllView
                    LazyRow(
                        contentPadding = PaddingValues(0.dp),
                        horizontalArrangement = Arrangement.spacedBy(0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .constrainAs((homeScroll)) {
                                top.linkTo(melhorPreco.bottom, 40.dp)
                                start.linkTo(parent.start, 13.dp)
                                end.linkTo(parent.end)
                                bottom.linkTo(entreEmContato.top, 10.dp)

                                width = Dimension.value(deviceWidth + 20.dp)
                                height =
                                    Dimension.value((deviceHeight / 2.7f).coerceAtMost(deviceWidth)) // coerceAtMost garante que a imagem seja redimensionada de forma proporcional
                            }
                            .padding(horizontal = 7.dp, vertical = 10.dp)

                    ) {
                        item {

                            Image(
                                painter = painterResource(id = R.drawable.pisos),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,

                                modifier = Modifier
                                    .width(deviceWidth - 20.dp)
                                    .height(deviceHeight)
                                    .padding(start = 17.dp)
                                    .clickable {
                                        navController.navigate("pisos",navOptions) },

                            )
                            Image(
                                painter = painterResource(id = R.drawable.esquadrias),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(deviceWidth - 20.dp)
                                    .height(deviceHeight)
                                    .padding(start = 17.dp)
                                    .clickable {
                                        navController.navigate("esquadrias") },
                            )
                            Image(
                                painter = painterResource(id = R.drawable.minerios),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(deviceWidth - 20.dp)
                                    .height(deviceHeight)
                                    .padding(start = 17.dp)
                                    .clickable {
                                        navController.navigate("calc")

                                    },
                            )
                            Image(
                                painter = painterResource(id = R.drawable.lajes),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(deviceWidth - 20.dp)
                                    .height(deviceHeight)
                                    .padding(start = 17.dp)

                            )
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.entreemcontato),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth, //para usar fitCenter
                        modifier = Modifier.constrainAs((entreEmContato)) {
                            top.linkTo(homeScroll.bottom, -10.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom, 50.dp)
                            width = Dimension.value(deviceWidth - 14.dp)
                            height =
                                Dimension.value((deviceHeight / 3.2f).coerceAtMost(deviceWidth))

                        }
                    )
                }
            }
        }

    }

//private fun Shape.border(
//    borderStroke: BorderStroke,
//    roundedCornerShape: RoundedCornerShape
//): Shape {
//    return object : BorderShape(this, borderStroke, roundedCornerShape) {}
//}
//
//abstract class BorderShape(
//    private val shape: Shape,
//    private val borderStroke: BorderStroke,
//    private val roundedCornerShape: RoundedCornerShape,
//    val cornerRadius:Dp = 16.dp
//
//    ) : Shape {
//    override fun createOutline(
//        size: Size,
//        layoutDirection: LayoutDirection,
//        density: Density,
//    ): Outline {
//        val innerOutline = shape.createOutline(size, layoutDirection, density)
//        val halfStrokeWidthPx = with(density) { borderStroke.width.toPx() / 2 }
//        val paddedRect = innerOutline.bounds.inflate(halfStrokeWidthPx)
//        val paddedRoundRect = RoundRect(
//            left = paddedRect.left,
//            top = paddedRect.top,
//            right = paddedRect.right,
//            bottom = paddedRect.bottom,
//            topLeftCornerRadius =30.dp,
//            topRightCornerRadius =30
//        )
//        return Outline.Rounded(paddedRoundRect)
//    }
//}
//


@Preview
@Composable
fun HomePreview(){
    val navController = rememberNavController()
  HomeScreen(MyViewModel(), navController)
}

