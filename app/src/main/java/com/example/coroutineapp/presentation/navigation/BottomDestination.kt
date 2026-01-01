package com.example.coroutineapp.presentation.navigation

import kotlinx.serialization.Serializable

interface BottomDestination

@Serializable
data object MainScreen: BottomDestination

@Serializable
data object PlaylistScreen: BottomDestination

@Serializable
data object DownloadMusicScreen: BottomDestination

@Serializable
data object ProfileScreen: BottomDestination