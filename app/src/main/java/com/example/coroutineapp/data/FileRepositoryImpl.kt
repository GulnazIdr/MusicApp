package com.example.coroutineapp.data

import android.content.Context
import com.example.coroutineapp.data.mappers.FileDataMapper
import com.example.coroutineapp.domain.AudioReader
import com.example.coroutineapp.domain.FileRepository
import com.example.coroutineapp.domain.modules.Music
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val audioReader: AudioReader
): FileRepository , FileDataMapper(){
    override fun fetchAudioFiles(): List<Music> = audioReader.getFiles().map { it.toMusic() }

    override fun loadAudioCovers(context: Context, index: Int) {
        audioReader.loadBitmapIfNeeded(context, index)
    }
}