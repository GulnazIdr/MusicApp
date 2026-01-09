package com.example.coroutineapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.coroutineapp.presentation.main.MainLayerScreen
import com.example.coroutineapp.presentation.music.screens.MusicViewScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    fun navigateAndPop(destination: Destination, pop: Destination){
        navController.navigate(destination)
    }

    NavHost(
        navController = navController,
        startDestination = MainLayerScreen
    ){
        composable<MainLayerScreen> {
            MainLayerScreen(
                onMusic = {navigateAndPop(MusicDetailScreen(it), MainLayerScreen)}
            )
        }

        composable<MusicDetailScreen> {
            val id = it.toRoute<MusicDetailScreen>().musicId
            MusicViewScreen(
                musicId = id
            )
        }
    }
}