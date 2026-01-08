package com.example.coroutineapp.di

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.provider.MediaStore
import com.example.coroutineapp.data.AudioReader
import com.example.coroutineapp.data.FileRepositoryImpl
import com.example.coroutineapp.domain.FileRepository
import com.example.coroutineapp.presentation.music.AudioFocusListener
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

    @Singleton
    @Provides
    fun provideAudioFocusListener(
        @ApplicationContext context: Context
    ): AudioFocusListener =
        AudioFocusListener(context)


}