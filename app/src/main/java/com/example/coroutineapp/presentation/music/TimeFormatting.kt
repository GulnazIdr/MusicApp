//package com.example.coroutineapp.presentation.music
//
//import java.time.Duration
//
//
//fun formatDuration(seconds: Int): String {
//    val duration = Duration. seconds(seconds.toDouble())
//    val minutes = duration.inWholeMinutes
//    val remainingSeconds = duration.inWholeSeconds % 60
//    return String.format("%02d:%02d", minutes, remainingSeconds)
//}