package com.example.coroutineapp.presentation.music.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coroutineapp.presentation.music.formatDuration

@Composable
fun MusicDurationBar(
    currentTime: Int,
    musicDuration: Int,
    onValueChanged: (value: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var maxDuration by remember { mutableIntStateOf(musicDuration) }

    Column{
        DurationBar(
            initialValue = currentTime * 100 / maxDuration.toFloat(),
            onValueChange = {value->
                onValueChanged((value*maxDuration).toInt())
            },
            paddingValues = PaddingValues(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentTime.formatDuration(),
                color = Color.White,
                fontSize = 17.sp
            )

            Text(
                text = musicDuration.formatDuration(),
                color = Color.White,
                fontSize = 17.sp
            )
        }
    }
}

//@Preview
//@Composable
//private fun MusicDurationBarPreview() {
//    MusicDurationBar(
//        1,1
//    )
//}