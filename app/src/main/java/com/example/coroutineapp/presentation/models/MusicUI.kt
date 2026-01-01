package com.example.coroutineapp.presentation.models

import android.graphics.Bitmap
import android.net.Uri

data class MusicUI(
    val id: Long,
    val name: String,
    val artistName: String,
    val duration: Int,
    val image: Bitmap?,
    val uri: Uri
)