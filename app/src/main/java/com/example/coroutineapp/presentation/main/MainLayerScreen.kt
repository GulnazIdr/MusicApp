package com.example.coroutineapp.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.coroutineapp.presentation.common.BottomBar
import com.example.coroutineapp.presentation.models.MusicUI
import com.example.coroutineapp.presentation.navigation.BottomNavState
import com.example.coroutineapp.presentation.navigation.BottomNavigationGraph

@Composable
fun MainLayerScreen(
    onMusic: (musicId: Long) -> Unit,
) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        bottomBar = {
            BottomBar(
                navState = BottomNavState(navController)
            )
        }
    ){ padding ->
        BottomNavigationGraph(
            innerPadding = PaddingValues(
                start = 20.dp, end = 20.dp, top = padding.calculateTopPadding()
            ),
            navController = navController,
            onMusic = {onMusic(it)}
        )
    }
}