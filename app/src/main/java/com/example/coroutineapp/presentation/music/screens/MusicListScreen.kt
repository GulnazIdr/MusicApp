package com.example.coroutineapp.presentation.music.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coroutineapp.R
import com.example.coroutineapp.presentation.MusicViewModel
import com.example.coroutineapp.presentation.common.CommonTopAppBar
import com.example.coroutineapp.presentation.music.components.MusicList

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MusicListScreen(
    innerPadding: PaddingValues,
    onMusic: (musicId: Long) -> Unit,
    musicViewModel: MusicViewModel = hiltViewModel()
) {
    val musicUIList = musicViewModel.fetchedAudioList

    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        CommonTopAppBar(
            title = stringResource(R.string.music)
        )

        MusicList(
            musicUIList = musicUIList,
            onMusic = {onMusic(it)}
        )
    }
}

//@Preview
//@Composable
//private fun MusicListScreenPreview() {
//    MusicListScreen(innerPadding = PaddingValues(), {})
//}