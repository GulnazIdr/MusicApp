package com.example.coroutineapp.presentation.comparison

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OptionsGrid(
    list: List<String>,
    onOption: (choice: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(110.dp),
        verticalArrangement = Arrangement.spacedBy (10.dp),
        horizontalArrangement = Arrangement.spacedBy (10.dp),
    ) {
        items(list) { option ->
            OptionItem(
                option = option,
                onOption = {onOption(it)}
            )
        }
    }
}

@Preview
@Composable
private fun OptoinsGridPrev() {
    OptionsGrid(
        list = listOf("jogging", "cooking", "programming", "studying"), {}
    )
}