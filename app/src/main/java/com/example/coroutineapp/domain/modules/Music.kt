package com.example.coroutineapp.domain.modules

import android.graphics.Bitmap
import android.net.Uri

data class Music(
    val id: Long,
    val musicName: String,
    val singerName: String,
    val duration: String,
    val image: Bitmap?,
    val uri: Uri
)
