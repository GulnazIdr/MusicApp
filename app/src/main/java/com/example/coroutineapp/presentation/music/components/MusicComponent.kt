package com.example.coroutineapp.presentation.music.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coroutineapp.R
import com.example.coroutineapp.presentation.MusicViewModel
import com.example.coroutineapp.presentation.models.MusicUI
import com.example.coroutineapp.ui.theme.lightGrey
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MusicComponent(
    musicUI: MusicUI,
    onMusic: (musicId: Long) -> Unit,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    musicViewModel: MusicViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val musicName = musicUI.name
    val artistName = musicUI.artistName

    val imageModifier = Modifier
        .fillMaxHeight()
        .clip(RoundedCornerShape(10.dp))

    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        musicViewModel.playMusic()
                    },
                    onDoubleTap = {
                        onMusic(musicUI.id)
                    }
                )
            }

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
                if(musicUI.image != null)
                    Image(
                        bitmap = musicUI.image.asImageBitmap(),
                        modifier = imageModifier,
                        contentDescription = "audio image"
                    )
                else
                    Image(
                        painter = painterResource(id = R.drawable.default_audio),
                        modifier = imageModifier.background(Color.White),
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