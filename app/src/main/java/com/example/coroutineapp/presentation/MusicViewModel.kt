package com.example.coroutineapp.presentation

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.coroutineapp.domain.AudioManager
import com.example.coroutineapp.domain.DirectoryChangeUseCase
import com.example.coroutineapp.domain.FileRepository
import com.example.coroutineapp.presentation.mappers.toMusicUi
import com.example.coroutineapp.presentation.models.MusicUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.Q)
@HiltViewModel
class MusicViewModel @Inject constructor(
    private val fileRepository: FileRepository,
    private val audioManager: AudioManager
) : ViewModel(), DirectoryChangeUseCase{
    private val _fetchedAudioList = mutableStateOf<List<MusicUI>>(emptyList())
    val fetchedAudioList get() = _fetchedAudioList.value

    init {
        fetchAudioFromDevice(false)
    }

    fun getPlayingState() = audioManager.isPlaying()

    fun playMusic(musicId: Long){
        audioManager.requestFocus()
        audioManager.start(musicId)
    }

    override fun directoryUpdated() {
        fetchAudioFromDevice(true)
    }

    fun getCurrentPosition() = audioManager.getCurrentPosition()

    fun seekTo(pos: Int){
        audioManager.seekTo(pos)
    }

    fun pauseMusic(){
        audioManager.pauseAudio()
    }

    fun getMusicById(id: Long): MusicUI{
        return _fetchedAudioList.value.find{ it.id == id }!!
    }

    private fun fetchAudioFromDevice(isUpdated: Boolean){
        _fetchedAudioList.value = fileRepository.fetchAudioFiles(isUpdated).map {it.toMusicUi()}
    }
}