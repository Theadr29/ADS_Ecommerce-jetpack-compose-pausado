package com.AdrianoDev.ADS_Ecommerce.View.Menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.AdrianoDev.ADS_Ecommerce.View.Theme.InstaIcon
import com.AdrianoDev.ADS_Ecommerce.ViewModel.MyViewModel
import kotlinx.coroutines.launch



data class BottomSheetItem(
    val title: String,
    val icon: ImageVector,
    val onClick: (viewModel: MyViewModel, navController: NavController) -> Unit
)

val bottomSheetItems = listOf(
    BottomSheetItem(
        title = "Instagram",
        icon = InstaIcon,
        onClick = { viewModel, navController ->
            navController.navigate("insta")
        }
    ),

    BottomSheetItem(
        title = "E-Mail",
        icon = Icons.Default.MailOutline,
        onClick = { viewModel, navController ->

        } ),

    BottomSheetItem(
        title = "Scan",
        icon = Icons.Default.Search,
        onClick = {viewModel, navController ->

        } ),
    BottomSheetItem(
        title = "Edit",
        icon = Icons.Default.Edit,
        onClick = {viewModel, navController ->

        } ),
    BottomSheetItem(
        title = "Favorite",
        icon = Icons.Default.Favorite,
        onClick = {viewModel, navController ->

        } ),
    BottomSheetItem(
        title = "Settings",
        icon = Icons.Default.Settings,
        onClick = {viewModel, navController ->

        } )
)
//val coroutineScope = rememberCoroutineScope()

//BottomSheetScaffold(
//sheetContent = {
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//    ) {
//        bottomSheetItems.forEach { item ->
//            BottomSheetItem(
//                title = item.title,
//                icon = item.icon,
//                onClick = { navController, viewModel ->
//                    coroutineScope.launch {
//
//                        item.onClick.invoke(navController, viewModel)
//                    }
//                }
//            )
//        }
//    }
//},
//sheetPeekHeight = 0.dp,
//scaffoldState = rememberBottomSheetScaffoldState()
//) {