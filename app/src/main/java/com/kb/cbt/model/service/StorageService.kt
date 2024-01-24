package com.kb.cbt.model.service

import com.kb.cbt.model.Quiz

interface StorageService {
    val quizList: List<Quiz>

    fun getQuiz(quizId: String): Quiz?
    fun saveQuiz(quiz: Quiz)
    fun updateQuiz(quiz: Quiz)
    fun deleteQuiz(quizId: String)
}