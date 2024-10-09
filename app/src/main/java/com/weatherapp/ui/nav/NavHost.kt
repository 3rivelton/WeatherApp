package com.weatherapp.ui.nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.weatherapp.MainViewModel
import com.weatherapp.db.fb.FBDatabase
import com.weatherapp.ui.HomePage
import com.weatherapp.ui.ListPage
import com.weatherapp.ui.MapPage

@Composable
fun MainNavHost(
    navController: NavHostController,
    viewModel : MainViewModel,
    fbDatabase : FBDatabase,
    context: Context
) {
    NavHost(navController, startDestination = BottomNavItem.HomePageKt.route) {
    // composable (route = NOME DESTA DESTINAÇÃO) { UI DA DESTINAÇÃO }
        composable(route = BottomNavItem.HomePageKt.route) {
            HomePage(viewModel, fbDatabase, context)
        }
        composable(route = BottomNavItem.ListPageKt.route) {
            ListPage(viewModel, fbDatabase, context)
        }
        composable(route = BottomNavItem.MapPageKt.route) {
            MapPage(viewModel, fbDatabase, context)
        }
    }
}