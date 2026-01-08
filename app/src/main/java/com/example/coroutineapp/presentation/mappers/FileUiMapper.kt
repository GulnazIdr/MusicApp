package com.example.coroutineapp.presentation.mappers

import android.util.Log
import com.example.coroutineapp.domain.modules.Music
import com.example.coroutineapp.presentation.models.MusicUI

fun Music.toMusicUi(): MusicUI{
    return MusicUI(
        id = id,
        name = musicName,
        artistName = singerName,
        duration = duration,
        image = image,
        uri = uri
    )
}