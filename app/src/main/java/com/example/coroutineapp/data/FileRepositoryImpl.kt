package com.example.coroutineapp.data

import com.example.coroutineapp.data.mappers.FileDataMapper
import com.example.coroutineapp.domain.AudioReader
import com.example.coroutineapp.domain.FileRepository
import com.example.coroutineapp.domain.modules.Music
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val audioReader: AudioReader
): FileRepository , FileDataMapper(){
    override fun fetchAudioFiles(isUpdated: Boolean): List<Music> {
        return audioReader.getFiles(isUpdated).map { it.toMusic() }
    }
}