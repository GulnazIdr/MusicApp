package com.example.coroutineapp.presentation.music.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coroutineapp.presentation.MusicViewModel
import com.example.coroutineapp.presentation.models.MusicUI

@Composable
fun MusicList(
    musicUIList: List<MusicUI>,
    onMusic: (musicId: Long) -> Unit,
    musicViewModel: MusicViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed( musicUIList) {index,  musicUI ->
                LaunchedEffect(Unit) {
                    musicViewModel.loadAudioCovers(context, index)
                }

                MusicComponent(
                    isFirst = index == 0,
                    isLast = index == musicUIList.size-1,
                    musicUI =  musicUI,
                    onMusic = {onMusic(it)}
                )
            }
        }
    }
}
