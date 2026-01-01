package com.example.coroutineapp.presentation.music.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.time.Duration.Companion.seconds

@Composable
fun MusicDurationBar(
    currentTime: Int = 0,
    musicDuration: Int,
    modifier: Modifier = Modifier
) {
    Column{
        DurationBar(
            currentDuration = currentTime,
            onValueChange = {}
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentTime.toString(),
                color = Color.White,
                fontSize = 17.sp
            )

            Text(
                text = (musicDuration/60.0).seconds .toString(),
                color = Color.White,
                fontSize = 17.sp
            )
        }
    }
}

@Preview
@Composable
private fun MusicDurationBarPreview() {
    MusicDurationBar(
        0, 178
    )
}