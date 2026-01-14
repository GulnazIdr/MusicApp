package com.example.coroutineapp.presentation.comparison

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coroutineapp.ui.theme.lightBlue

@Composable
fun OptionItem(
    option: String,
    onOption: (choice: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isClicked by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (isClicked) Color.White else lightBlue)
            .clickable(onClick = {
                isClicked = !isClicked
                onOption(option)
            })
            .padding(20.dp)
    ) {
        Text(
            text = option,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun OptionItemPrev() {
    OptionItem(
        "dancing",{}
    )
}