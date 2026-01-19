package com.example.coroutineapp.domain

import com.example.coroutineapp.domain.modules.Music

interface FileRepository {
    fun fetchAudioFiles(isUpdated: Boolean): List<Music>
}