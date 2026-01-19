package com.example.coroutineapp.presentation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineapp.domain.AudioManager
import com.example.coroutineapp.domain.DirectoryChangeUseCase
import com.example.coroutineapp.domain.FileRepository
import com.example.coroutineapp.presentation.mappers.toMusicUi
import com.example.coroutineapp.presentation.models.MusicUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.Q)
@HiltViewModel
class MusicViewModel @Inject constructor(
    private val fileRepository: FileRepository,
    private val audioManager: AudioManager
) : ViewModel(), DirectoryChangeUseCase{

//    private val _fetchedAudioList = mutableStateOf<List<MusicUI>>(emptyList())
//    val fetchedAudioList get() = _fetchedAudioList.value
    private val _fetchedAudioList = MutableStateFlow<List<MusicUI>>(emptyList())
    val fetchedAudioList = _fetchedAudioList.asStateFlow()

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

     fun fetchAudioFromDevice(isUpdated: Boolean){
         viewModelScope.launch {
             _fetchedAudioList.emit(
                 fileRepository.fetchAudioFiles(isUpdated).map { it.toMusicUi() })
         }//.update{

       // }
         Log.d("called", "${_fetchedAudioList.value.size}")
    }
}