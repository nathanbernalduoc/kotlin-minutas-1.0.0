package com.example.test2.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationWrapper() {
    val navController = rememberNavControlle();
    NavHost(navController = navController, startDestination = "Login") {

    }
}