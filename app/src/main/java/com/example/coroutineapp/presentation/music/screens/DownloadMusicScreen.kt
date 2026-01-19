package com.example.coroutineapp.presentation.music.screens

import android.app.DownloadManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coroutineapp.presentation.MusicViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DownloadMusicScreen(
    innerPadding: PaddingValues,
    applicationContext: Context,
    musicViewModel: MusicViewModel = hiltViewModel()
) {
    val url = "https://rus.hitmotop.com/"

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
            setDownloadListener { url, _, contentDisposition, mimeType, _ ->
                setupDownloadListener(
                    "https://eu.hitmo-top.com/get/music/20251124/Kjenni_MS_Dymka_-_Vorona_80311772.mp3",
                    contentDisposition,
                    mimeType,
                    applicationContext
                )
                musicViewModel.fetchAudioFromDevice(true)
            }
        }
    }, update = {
        it.loadUrl(url)
    })
}

private fun setupDownloadListener(
    url: String,
    contentDisposition: String,
    mimeType: String,
    context: Context
) {
    val filename = getFileName(contentDisposition, url)

    val request = DownloadManager.Request(url.toUri())
        .setMimeType(mimeType)
        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setTitle(filename)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)

    val downloadManager = context.getSystemService(DownloadManager::class.java)
    downloadManager.enqueue(request)
    Toast.makeText(context, "Download started", Toast.LENGTH_SHORT).show()
}

private fun getFileName(contentDisposition: String?, url: String): String {
    return if (contentDisposition != null && contentDisposition.contains("filename=")) {
        contentDisposition.substringAfter("filename=").replace("\"", "")
    } else {
        url.substringAfterLast("/")
    }
}

//@Preview
//@Composable
//private fun DownloadMusicScreenPreview() {
//    DownloadMusicScreen(
//        innerPadding = PaddingValues()
//    )
//}