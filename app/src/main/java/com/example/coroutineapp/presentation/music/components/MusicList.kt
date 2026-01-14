package com.example.coroutineapp.presentation.music.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coroutineapp.presentation.models.MusicUI

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MusicList(
    musicUIList: List<MusicUI>,
    onMusic: (musicId: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed( musicUIList) {index,  musicUI ->
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
