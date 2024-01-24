package com.kb.cbt.model.service

import com.kb.cbt.model.Quiz
import com.kb.cbt.model.QuizLenData
import kotlinx.coroutines.flow.Flow

interface StorageService {
    fun getQuiz(quizLenData: QuizLenData): List<Quiz>?
    fun saveQuiz(quiz: Quiz)
    fun updateQuiz(quiz: Quiz)
    fun deleteQuiz(quizId: String)
}