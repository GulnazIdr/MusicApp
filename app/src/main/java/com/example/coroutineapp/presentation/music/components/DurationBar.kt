package com.example.coroutineapp.presentation.music.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.coroutineapp.presentation.common.Bar
import com.example.coroutineapp.ui.theme.lightGrey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun DurationBar(
    currentDuration: Int,
    onValueChange: (isHigher: Boolean) -> Unit,
    height: Dp = 6.dp,
    paddingValues: PaddingValues? = null
) {
    //26 960
    Log.d("current duration", currentDuration.toString())
    val scope = CoroutineScope(Dispatchers.Main + Job())

    val initialScreenWidth = LocalWindowInfo.current.containerSize.width.dp
    var screenWidth = initialScreenWidth
    var horizontalPadding = 0.dp

    var currentValue by remember { mutableIntStateOf(currentDuration) }

    if(paddingValues != null) {
         horizontalPadding = paddingValues.calculateLeftPadding(LayoutDirection.Ltr).value.plus(
                paddingValues.calculateRightPadding(LayoutDirection.Ltr).value).dp
        screenWidth = (initialScreenWidth - horizontalPadding) * currentValue / 100
    }else
        screenWidth = initialScreenWidth * currentValue / 100

    var sliderOffset by remember { mutableFloatStateOf(screenWidth.value - 10) }

    Log.d("screen width", "$screenWidth $currentValue $paddingValues $sliderOffset")
    Box{
        Box(
            modifier = Modifier
                .size(15.dp)
                .offset{IntOffset(sliderOffset.roundToInt(), 0)}
                .zIndex(1f)
                .clip(RoundedCornerShape(100.dp))
                .background(Color.Red)
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
                width = screenWidth,
                backgroundColor = Color.White,
                modifier = Modifier.draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
//                        if(
//                            sliderOffset+delta > 0f &&
//                            sliderOffset+delta < (screenWidth - horizontalPadding).value
//                        ) {

                        Log.d("offset slider", "$delta $sliderOffset ${screenWidth-horizontalPadding}")
                            sliderOffset += delta
                            if (delta < 0) {
                                // scope.launch {
                                // delay(10)
                                currentValue--

                                onValueChange(false)
                                // }
                            } else {
                                scope.launch {
                                    //  delay(10)
                                    currentValue++
                                    //  sliderOffset -= delta
                                    onValueChange(true)
                                }
                            }
                     //   }

                    }
                ),
            )
        }
    }
}

@Preview
@Composable
private fun DurationBarPreview() {
    var current by remember { mutableStateOf(1) }
    DurationBar(++current, {})
}