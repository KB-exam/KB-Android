package com.kb.cbt.screen.main.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kb.cbt.model.Quiz
import com.kb.cbt.model.QuizLenData
import com.kb.cbt.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {


    var quizListStorage: List<Quiz> = listOf()
    var currentQuiz: Int = 0
    var maxQuiz: Int = 11
    var quizAmount: Int = 0
    var success: Int = 0
    var fail: Int = 0


    fun printQuiz(callback: (List<Quiz>) -> Unit) {
        viewModelScope.launch {
            try {
                val accessToken =
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXJhbSI6WzM5MDIyNzRdLCJpYXQiOjE3MDYxOTUzNDksImV4cCI6MTcwNjYyNzM0OSwiaXNzIjoiZG9nbmRvbmcifQ.GJmtrYEIbkCYr_yVmfDBiE-mOD0zgYjg5vwaIB8-exA"
//                val allQuestion = userRepository.allQuestion(accessToken).body()?.message ?: 0
//
//                //quizAmount = allQuestion.body().message!!
//
//                Log.d("TAG", "allQuestion: $allQuestion")

                quizListStorage = userRepository.getExam(QuizLenData(
                    accessToken = accessToken,
                    quantity = 11,
                ))
                callback(quizListStorage)

            } catch(e: Exception) {

            }
        }
    }

    fun compareQuiz(input: String, answer: String):Int {
        return if(input == answer) {
            1
        } else {
            0
        }
    }

    fun nextQuiz(finish: (Int, Int) -> Unit) {
        if(maxQuiz > currentQuiz + 1) {
            currentQuiz++
        } else {
            finish(success, fail)
        }
    }

    fun success() {
        success++
    }

    fun fail() {
        fail++
    }
}