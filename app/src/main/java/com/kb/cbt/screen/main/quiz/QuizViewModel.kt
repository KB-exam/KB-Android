package com.kb.cbt.screen.main.quiz

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kb.cbt.model.Quiz
import com.kb.cbt.model.QuizLenData
import com.kb.cbt.model.service.StorageService
import com.kb.cbt.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {


    var quizListStorage: List<Quiz> = listOf()


    fun printQuiz(callback: (List<Quiz>) -> Unit) {
        viewModelScope.launch {
            quizListStorage = userRepository.getExam(QuizLenData(
                accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXJhbSI6WzEwMF0sImlhdCI6MTcwNjEwOTY4NCwiZXhwIjoxNzA2NTQxNjg0LCJpc3MiOiJkb2duZG9uZyJ9.lXK05PjSzgsYfNtS_p8q5-6jQprUmHDr_cxxdiAQU-k",
                quantity = 5,
            ))
            callback(quizListStorage)
        }
    }
}