package com.shaun.newsbreeze.navigation


sealed class Routes(val route: String) {
    object HomeScreen : Routes("HomeScreen")
    object NewsViewScreen : Routes("NewsViewScreen")
    object SavedScreen : Routes("SavedScreen")
    object SplashScreen : Routes("SplashScreen")

}