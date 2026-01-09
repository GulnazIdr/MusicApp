package com.example.coroutineapp.presentation.music.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coroutineapp.R
import com.example.coroutineapp.presentation.MusicViewModel
import com.example.coroutineapp.presentation.music.components.MusicDurationBar
import com.example.coroutineapp.presentation.music.components.MusicManager
import com.example.coroutineapp.presentation.music.components.SoundManager
import java.util.Timer
import java.util.TimerTask

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MusicViewScreen(
    musicId: Long,
    musicViewModel: MusicViewModel = hiltViewModel()
) {
    val musicUI = musicViewModel.getMusicById(musicId)
    val isMusicPlaying = musicViewModel.getPlayingState()

    var currentTime by remember { mutableIntStateOf(0) }

    Timer().schedule(object : TimerTask(){
        override fun run() {
            if(musicViewModel.getPlayingState())
                currentTime = musicViewModel.getCurrentPosition()
        }
    }, 0, 1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.weight(1.5f, true),
            verticalArrangement = Arrangement.Center
        ) {
            if (musicUI.image != null)
                Image(
                    bitmap = musicUI.image.asImageBitmap(),
                    contentDescription = "${musicUI.name}'s image",
                    Modifier.scale(2f)
                )
            else
                Icon(
                    painter = painterResource(R.drawable.default_audio),
                    contentDescription = "default audio image",
                    Modifier.scale(2f),
                    tint = Color.White
                )
        }

        Column(
            modifier = Modifier.weight(1f).padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = musicUI.name,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = musicUI.artistName,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            MusicManager(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onStartMusic = { musicViewModel.playMusic() },
                onStopMusic = {musicViewModel.provide().pause()},
                isPlaying = isMusicPlaying
            )

            Spacer(modifier = Modifier.height(20.dp))

            MusicDurationBar(
                musicDuration = musicUI.duration,
                currentTime = currentTime,
                onValueChanged = { value ->
                    musicViewModel.seekTo(value)
                    currentTime =  value
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            SoundManager(
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}