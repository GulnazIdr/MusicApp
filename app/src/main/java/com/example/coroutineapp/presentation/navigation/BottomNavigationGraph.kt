package com.example.coroutineapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coroutineapp.presentation.music.screens.DownloadMusicScreen
import com.example.coroutineapp.presentation.music.screens.MusicListScreen
import com.example.coroutineapp.presentation.profile.ProfileScreen

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    onMusic: (musicId: Long) -> Unit,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = PlaylistScreen
    ){
        composable<PlaylistScreen> {
            MusicListScreen(
                innerPadding = innerPadding,
                onMusic = {onMusic(it)}
            )
        }

        composable<DownloadMusicScreen> {
            DownloadMusicScreen(
                innerPadding = innerPadding
            )
        }

        composable<ProfileScreen> {
            ProfileScreen(
                innerPadding = innerPadding
            )
        }
    }
}