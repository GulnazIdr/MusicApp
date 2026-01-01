package com.example.coroutineapp.presentation.music.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coroutineapp.R

@Composable
fun MusicManager(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ){
        Icon(
            painter = painterResource(R.drawable.music_back),
            contentDescription = "audio image",
            tint = Color.White,
            modifier = Modifier.size(60.dp)
        )

        VerticalDivider(
            thickness = 14.dp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 20.dp, end = 5.dp)
                .height(60.dp)
        )
        VerticalDivider(
            thickness = 14.dp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 5.dp, end = 20.dp)
                .height(60.dp)
        )

        Icon(
            painter = painterResource(R.drawable.music_forward),
            contentDescription = "audio image",
            tint = Color.White,
            modifier = Modifier.size(60.dp)
        )
    }
}

@Preview
@Composable
private fun MusicManagerPreview() {
    MusicManager(

    )
}