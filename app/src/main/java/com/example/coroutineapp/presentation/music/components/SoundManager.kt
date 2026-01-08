package com.example.coroutineapp.presentation.music.components

import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coroutineapp.R

@Composable
fun SoundManager(
    modifier: Modifier = Modifier
) {
    val audioManager = LocalContext.current.getSystemService(AUDIO_SERVICE) as AudioManager
    var currentVolume by remember { mutableIntStateOf(
        audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)) }
    var maxVolume by remember { mutableIntStateOf(
        audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.low_sound),
            contentDescription = "low sound icon",
            modifier = Modifier.size(35.dp),
            tint = Color.White
        )///////////////////////////
        //0.2//////////////////////

        DurationBar(
            height = 8.dp,
            initialValue = currentVolume * 100 / maxVolume.toFloat(),
            onValueChange = {value->
                Log.d("time time", "$value $currentVolume")
                currentVolume = (value*maxVolume).toInt()
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0)
            },
            paddingValues = PaddingValues(horizontal = 10.dp)
        )
    }
}
//
//@Preview
//@Composable
//private fun SoundManagerPreview() {
//    SoundManager()
//}