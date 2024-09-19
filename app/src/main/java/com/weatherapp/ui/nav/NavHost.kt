package com.weatherapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.weatherapp.ui.HomePage
import com.weatherapp.ui.ListPage
import com.weatherapp.ui.MapPage

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.HomePageKt.route) {
    // composable (route = NOME DESTA DESTINAÇÃO) { UI DA DESTINAÇÃO }
        composable(route = BottomNavItem.HomePageKt.route) {
            HomePage()
        }
        composable(route = BottomNavItem.ListPageKt.route) {
            ListPage()
        }
        composable(route = BottomNavItem.MapPageKt.route) {
            MapPage()
        }
    }
}