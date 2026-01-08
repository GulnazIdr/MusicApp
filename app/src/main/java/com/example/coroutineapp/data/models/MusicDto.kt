package com.example.coroutineapp.data.models

import android.graphics.Bitmap
import android.net.Uri

data class MusicDto(
    val id: Long,
    val musicName: String,
    val singerName: String,
    val duration: Int,
    var cover: Bitmap?,
    val contentUri: Uri
)
