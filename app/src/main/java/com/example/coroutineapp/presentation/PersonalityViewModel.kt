package com.example.coroutineapp.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonalityViewModel @Inject constructor(

): ViewModel() {

    fun getQuestionList(): List<Pair<String, List<String>>>{
        return listOf(
            Pair("Are you an extrovert or an introvert?", listOf("extrovert", "introvert")),
            Pair("Are you mostly sad or happy?", listOf("sad", "happy")),
            Pair("What are your hobbies?", listOf("jogging", "cycling", "art", "baking",
                "dancing", "programming", "studying", "reading"))
        )
    }
}