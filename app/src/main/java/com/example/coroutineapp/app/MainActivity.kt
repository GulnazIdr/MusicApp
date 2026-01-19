package com.example.coroutineapp.app

import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.coroutineapp.data.AudioObserver
import com.example.coroutineapp.presentation.navigation.NavigationGraph
import com.example.coroutineapp.ui.theme.CoroutineAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var audioObserver: AudioObserver
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentResolver.registerContentObserver(
            MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL),
            true, audioObserver
        )
        enableEdgeToEdge()
        setContent {
            CoroutineAppTheme {
                NavigationGraph(
                    context = this
                )
            }
        }
    }
}