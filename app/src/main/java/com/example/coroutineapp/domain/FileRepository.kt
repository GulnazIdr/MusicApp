package com.example.coroutineapp.domain

import android.content.Context
import com.example.coroutineapp.domain.modules.Music

interface FileRepository {
    fun fetchAudioFiles(): List<Music>
    fun loadAudioCovers(context: Context, index: Int)
}