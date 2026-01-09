package com.example.coroutineapp.presentation

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.coroutineapp.domain.AudioManager
import com.example.coroutineapp.domain.FileRepository
import com.example.coroutineapp.presentation.mappers.toMusicUi
import com.example.coroutineapp.presentation.models.MusicListUi
import com.example.coroutineapp.presentation.models.MusicUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val fileRepository: FileRepository,
    private val audioManager: AudioManager
) : ViewModel(){
    private val _fetchedAudioList = mutableStateOf<List<MusicUI>>(emptyList())
    val fetchedAudioList: State<List<MusicUI>> = _fetchedAudioList

    val musicListUiClass: MusicListUi = MusicListUi(fetchedAudioList.value)

    fun getPlayingState() = audioManager.isPlaying

    init {
        fetchAudioFromDevice()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun playMusic(){
        audioManager.requestFocus()
        audioManager.start()
    }

    fun provide() = audioManager.provideMedia()

    fun getCurrentPosition() = audioManager.currentPosition

    fun seekTo(pos: Int){
        audioManager.seekTo(pos)
    }

    fun pauseMusic(){
        Log.d("click happened3", "")
        audioManager.pause()
    }

    fun loadAudioCovers(context: Context, index: Int){
        fileRepository.loadAudioCovers(context, index)
    }

    fun getMusicById(id: Long): MusicUI{
        return fetchedAudioList.value.find{ it.id == id }!!
    }

    private fun fetchAudioFromDevice(){
        _fetchedAudioList.value = fileRepository.fetchAudioFiles().map {it.toMusicUi()}
    }
}