package com.example.coroutineapp.presentation.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.coroutineapp.ui.theme.lightGrey

@Composable
fun Bar(
    height: Dp,
    width: Dp,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable () -> Unit = {}
) {
    Log.d("bar width", width.toString())
    Box(
        modifier = modifier
            .width(width)
            .background(backgroundColor)
            .clip(RoundedCornerShape(4.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            content()
        }
    }
}
