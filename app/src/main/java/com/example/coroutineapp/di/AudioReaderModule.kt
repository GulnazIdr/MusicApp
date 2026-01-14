package com.example.coroutineapp.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.coroutineapp.data.AudioObserver
import com.example.coroutineapp.data.FileRepositoryImpl
import com.example.coroutineapp.domain.AudioManager
import com.example.coroutineapp.domain.AudioReader
import com.example.coroutineapp.domain.FileRepository
import com.example.coroutineapp.domain.AudioListener
import com.example.coroutineapp.domain.DirectoryChangeUseCase
import com.example.coroutineapp.presentation.MusicViewModel
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
    fun provideAudioListener(@ApplicationContext context: Context, ):
            AudioListener = AudioListener(context)

    @Singleton
    @Provides
    fun provideAudioManager(
        @ApplicationContext context: Context,
        audioListener: AudioListener
    ): AudioManager = AudioManager(context, audioListener)

    @RequiresApi(Build.VERSION_CODES.Q)
    @Singleton
    @Provides
    fun provideAudioObserver(directoryChangeUseCase: DirectoryChangeUseCase) =
        AudioObserver(directoryChangeUseCase)

    @RequiresApi(Build.VERSION_CODES.Q)
    @Singleton
    @Provides
    fun provideDirectoryChangeUseCase(
        fileRepository: FileRepository,
        audioManager: AudioManager
    ): DirectoryChangeUseCase{
        return MusicViewModel(fileRepository, audioManager)
    }
}