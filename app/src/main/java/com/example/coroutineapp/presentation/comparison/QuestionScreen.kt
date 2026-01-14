package com.example.coroutineapp.presentation.comparison

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coroutineapp.presentation.PersonalityViewModel
import com.example.coroutineapp.presentation.common.NavigationButton

@Composable
fun QuestionScreen(
    personalityViewModel: PersonalityViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val questions = personalityViewModel.getQuestionList()
    val answerList = mutableListOf<String>()

    Column {
        questions.forEach {
            Text(
                text = it.first,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            OptionsGrid(
                list = it.second,
                onOption = {answerList.add(it)}
            )
            
            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(modifier = Modifier.height(30.dp))

        NavigationButton(
            text = "Find similarity",
            onAction = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun QuestionScreenPreview() {
    QuestionScreen()
}