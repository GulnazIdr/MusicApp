package com.example.coroutineapp.presentation.music.components

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coroutineapp.R
import com.example.coroutineapp.presentation.models.MusicUI
import com.example.coroutineapp.presentation.music.MusicViewScreen
import com.example.coroutineapp.ui.theme.lightGrey

@Composable
fun MusicComponent(
    musicUI: MusicUI,
    onMusic: (musicId: Long) -> Unit,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    modifier: Modifier = Modifier
) {
    val musicName = musicUI.name
    val artistName = musicUI.artistName

    val mediaPlayer = MediaPlayer.create(LocalContext.current, musicUI.uri)

    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                mediaPlayer.start()
                onMusic(musicUI.id)
            })
    ) {
        Column {
            if(!isFirst)
                HorizontalDivider(
                    thickness = 1.dp,
                    color = lightGrey,
                    modifier = Modifier.padding(bottom = 7.dp)
                )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.default_audio),
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                    contentDescription = "audio image"
                )

                Spacer(modifier = Modifier.width(20.dp))

                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text =
                            if(musicName.length > 28)
                                musicName.replaceRange(29, musicName.length, "...")
                            else musicName,
                        fontSize = 16.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if(artistName.length > 30)
                            artistName.replaceRange(37, artistName.length, "...")
                        else artistName,
                        fontSize = 14.sp,
                        color = lightGrey
                    )
                }
            }

            if(isLast)
                HorizontalDivider(
                    thickness = 1.dp,
                    color = lightGrey,
                    modifier = Modifier.padding(top = 5.dp)
                )
        }
    }
}

//@Preview
//@Composable
//private fun MusicComponentPreview() {
//    MusicComponent(
//        musicUI = MusicUI(
//            "hello everybody this is my name and my name doesn mean anything shiitty only good",
//            "hello everybody this is my name and my name doesn mean anything shiitty only good",
//            0,""
//        ),
//        {}
//    )
//}