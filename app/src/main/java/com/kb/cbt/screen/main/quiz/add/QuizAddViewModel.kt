package com.kb.cbt.screen.main.quiz.add

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kb.cbt.model.AddQuizData
import com.kb.cbt.model.RegisterData
import com.kb.cbt.repo.UserRepository
import com.kb.cbt.screen.register.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizAddViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    var uiState = mutableStateOf(QuizAddUiState())
        private set

    private val title
        get() = uiState.value.title

    private val content
        get() = uiState.value.content

    private val category
        get() = uiState.value.category

    private val type
        get() = uiState.value.type

    private val answer
        get() = uiState.value.answer

    private val choice1
        get() = uiState.value.choice1

    private val choice2
        get() = uiState.value.choice2

    private val choice3
        get() = uiState.value.choice3

    private val choice4
        get() = uiState.value.choice4

    fun onSelectValue(newValue: String) {
        if(newValue == "4지선다") {
            uiState.value = uiState.value.copy(type = 4)
        } else if(newValue == "OX문제") {
            uiState.value = uiState.value.copy(type = 2)
        }
    }
    fun onTitleChange(newValue: String) {
        uiState.value = uiState.value.copy(title = newValue)
    }
    fun onContentChange(newValue: String) {
        uiState.value = uiState.value.copy(content = newValue)
    }
    fun onCategoryChange(newValue: String) {
        uiState.value = uiState.value.copy(category = newValue)
    }
    fun onTypeChange(newValue: String) {
        if(newValue.isDigitsOnly()) {
            uiState.value = uiState.value.copy(type = newValue.toInt())
        }
    }
    fun onAnswerChange(newValue: String) {
        uiState.value = uiState.value.copy(answer = newValue)
    }
    fun onChoice1Change(newValue: String) {
        uiState.value = uiState.value.copy(choice1 = newValue)
    }
    fun onChoice2Change(newValue: String) {
        uiState.value = uiState.value.copy(choice2 = newValue)
    }
    fun onChoice3Change(newValue: String) {
        uiState.value = uiState.value.copy(choice3 = newValue)
    }
    fun onChoice4Change(newValue: String) {
        uiState.value = uiState.value.copy(choice4 = newValue)
    }

    fun onClickUpload(openAndPopUp: (String) -> Unit) {
        var quizAddStatus: String = ""
        viewModelScope.launch {
                quizAddStatus = userRepository.makeQuestion(
                    AddQuizData(
                        accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXJhbSI6WzExMV0sImlhdCI6MTcwNjE4MDc3MiwiZXhwIjoxNzA2NjEyNzcyLCJpc3MiOiJkb2duZG9uZyJ9.tFgC-XwL7HQjNIg7OPmlCPRHlBQHsLXt80_s2caaDW4",
                        title = title,
                        content = content,
                        type = type,
                        choice1 = choice1,
                        choice2 = choice2,
                        choice3 = choice3,
                        choice4 = choice4,
                        answer = answer,
                        category = category
                    )
                ).message.toString()

            Log.d(ContentValues.TAG, "quizAddStatus: $quizAddStatus")
            if(quizAddStatus == "suc OX" || quizAddStatus == "suc 4") {
                openAndPopUp("MainScreen")
            }
        }
    }
}