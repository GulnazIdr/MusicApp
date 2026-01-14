package com.example.coroutineapp.data

import android.content.Context
import android.util.Log
import com.example.coroutineapp.data.mappers.FileDataMapper
import com.example.coroutineapp.domain.AudioReader
import com.example.coroutineapp.domain.FileRepository
import com.example.coroutineapp.domain.modules.Music
import com.example.coroutineapp.presentation.MusicViewModel
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val audioReader: AudioReader
): FileRepository , FileDataMapper(){
    override fun fetchAudioFiles(isUpdated: Boolean): List<Music> {
        return audioReader.getFiles(isUpdated).map { it.toMusic() }
    }
}