package com.example.coroutineapp.presentation.navigation

import androidx.navigation.NavController

class BottomNavState(private val navController: NavController) {
    fun navigateTo(destination: BottomDestination){
        navController.navigate(destination){
            launchSingleTop = true
            restoreState = true
        }
    }
}