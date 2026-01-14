package com.example.coroutineapp.presentation.music.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DownloadMusicScreen(
    innerPadding: PaddingValues
) {
    val url = "https://rus.hitmotop.com/"
    AndroidView(factory = {
        WebView(it).apply{
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}

@Preview
@Composable
private fun DownloadMusicScreenPreview() {
    DownloadMusicScreen(
        innerPadding = PaddingValues()
    )
}