package com.example.coroutineapp.data.mappers

import com.example.coroutineapp.data.models.MusicDto
import com.example.coroutineapp.domain.modules.Music

abstract class FileDataMapper {
    protected fun MusicDto.toMusic(): Music{
        return Music(
            id = id,
            musicName = musicName,
            singerName = singerName,
            duration = duration,
            image = cover,
            uri = contentUri
        )
    }
}