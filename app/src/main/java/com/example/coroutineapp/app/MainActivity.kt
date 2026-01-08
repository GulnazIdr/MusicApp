package com.example.coroutineapp.app

import android.os.Build
import android.os.Bundle
import android.os.FileObserver
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.coroutineapp.presentation.navigation.NavigationGraph
import com.example.coroutineapp.ui.theme.CoroutineAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var fileObserver: FileObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutineAppTheme {
                NavigationGraph()
            }
        }

//        val queryUri =
//            if(Build.VERSION.SDK_INT >= 29)
//                MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
//            else
//                MediaStore.Audio.Media.getContentUri("external")
//
//        val file: File = File(queryUri.toString())
//        fileObserver = FileObserver(file, ){
//          //  Log.d("event", event.)
//        }
    }
}