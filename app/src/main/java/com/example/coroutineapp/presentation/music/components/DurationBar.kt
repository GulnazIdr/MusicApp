package com.example.coroutineapp.presentation.music.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.coroutineapp.presentation.common.Bar
import com.example.coroutineapp.ui.theme.lightGrey

@Composable
fun DurationBar(
    initialValue: Float,
    onValueChange: (delta: Float) -> Unit,
    height: Dp = 6.dp,
    paddingValues: PaddingValues? = null
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val initialScreenWidth = maxWidth
        var valueWidth by remember { mutableStateOf(initialScreenWidth)}
        var horizontalPadding = 0.dp

        var currentPercent by remember { mutableFloatStateOf(initialValue) }
        Log.d("value2", initialValue.toString())
        currentPercent = initialValue

        var currentVolume by remember { mutableFloatStateOf(currentPercent/100) }

        val range = 0..1

        if(paddingValues != null) {
            horizontalPadding = paddingValues.calculateLeftPadding(LayoutDirection.Ltr).value.plus(
                paddingValues.calculateRightPadding(LayoutDirection.Ltr).value).dp
            valueWidth = (initialScreenWidth - horizontalPadding) * currentPercent / 100
        }else
            valueWidth = initialScreenWidth * currentPercent / 100

        var sliderOffset by remember { mutableStateOf(valueWidth) }
        sliderOffset = valueWidth

        //Spacer
        Box {
            Box(
                modifier = Modifier
                    .padding(paddingValues ?: PaddingValues())
                    .size(15.dp)

                    .offset(x = sliderOffset, y = 0.dp)
                    .zIndex(1f)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            if (delta < 0 && currentPercent > 0 && currentVolume > range.start) {
                                currentVolume -= 0.1f
                                valueWidth -= 1.dp
                            } else if (delta > 0 && currentPercent < 100 && currentVolume < range.endInclusive) {
                                currentVolume += 0.1f
                                valueWidth += 1.dp
                            }

                            onValueChange(currentVolume)
                        }
                    ),
            )

            Bar(
                height = height,
                backgroundColor = lightGrey,
                modifier = Modifier
                    .zIndex(0f)
                    .padding(paddingValues ?: PaddingValues())
                    .align(Alignment.Center),
                width = initialScreenWidth
            ) {
                Bar(
                    height = height,
                    width = valueWidth,
                    backgroundColor = Color.White,
                )
            }
        }
    }
}

//@Preview
//@Composable
//private fun DurationBarPreview() {
//    var current by remember { mutableFloatStateOf(1f) }
//    DurationBar(++current, {})
//}