package com.example.coroutineapp.di

import android.content.Context
import com.example.coroutineapp.data.AudioReader
import com.example.coroutineapp.data.FileRepositoryImpl
import com.example.coroutineapp.domain.FileRepository
import com.example.coroutineapp.presentation.models.MusicListUi
import com.example.coroutineapp.presentation.models.MusicUI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AudioReaderModule {

    @Singleton
    @Provides
    fun provideAudioReader(@ApplicationContext context: Context): AudioReader {
        return AudioReader(context)
    }

    @Singleton
    @Provides
    fun provideFileRepository(
        audioReader: AudioReader
    ): FileRepository{
        return FileRepositoryImpl(audioReader)
    }

}