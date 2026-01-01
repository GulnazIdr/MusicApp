package com.example.coroutineapp.presentation.mappers

import android.util.Log
import com.example.coroutineapp.domain.modules.Music
import com.example.coroutineapp.presentation.models.MusicUI

fun Music.toMusicUi(): MusicUI{
    var durationSeconds = 0
    try {
        durationSeconds = (duration.toLong()/1000).toInt()
    }catch (e: Exception){
        Log.e("CONVERTING DURATION STRING TO LONG", "${e.message} ${e::class.simpleName}")
    }
    return MusicUI(
        id = id,
        name = musicName,
        artistName = singerName,
        duration = durationSeconds,
        image = image,
        uri = uri
    )
}