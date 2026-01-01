package com.example.coroutineapp.presentation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.coroutineapp.domain.FileRepository
import com.example.coroutineapp.presentation.mappers.toMusicUi
import com.example.coroutineapp.presentation.models.MusicListUi
import com.example.coroutineapp.presentation.models.MusicUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val fileRepository: FileRepository
) : ViewModel(){
    private val _fetchedAudioList = mutableStateOf<List<MusicUI>>(emptyList())
    val fetchedAudioList: State<List<MusicUI>> = _fetchedAudioList

    val musicListUiClass: MusicListUi = MusicListUi(fetchedAudioList.value)

    init {
        fetchAudioFromDevice()
        Log.d("fetching finished1", "")
    }

    private fun fetchAudioFromDevice(){
        _fetchedAudioList.value = fileRepository.fetchAudioFiles().map {it.toMusicUi()}
    }

    fun loadAudioCovers(context: Context, index: Int){
        fileRepository.loadAudioCovers(context, index)
    }

    fun getMusicById(id: Long): MusicUI{
        return fetchedAudioList.value.find{ it.id == id }!!
    }
}