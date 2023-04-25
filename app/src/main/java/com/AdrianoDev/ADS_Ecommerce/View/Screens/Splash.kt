import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.View.Screens.dh
import com.AdrianoDev.ADS_Ecommerce.ViewModel.MyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashArt(navController: NavController, viewModel: MyViewModel) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_777))
            .padding(16.dp) // adicionando padding de 16dp
    ) {
        val deviceWidth = maxWidth
        val deviceHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.adslogo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(deviceWidth - 100.dp)
                    .height(deviceHeight / 1)
                    .offset(y = (-25).dp)
            )

            LaunchedEffect(true) {
                viewModel.navigateToHome(navController)

            }
        }
    }
}
@Preview
@Composable
fun SplashPreview() {
    val navController = rememberNavController()
    SplashArt(navController, MyViewModel())
}



