package com.example.coroutineapp.presentation.navigation

import kotlinx.serialization.Serializable

interface Destination

@Serializable
data object MainLayerScreen: Destination

@Serializable
data class MusicDetailScreen(val musicId: Long): Destination