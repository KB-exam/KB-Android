package com.kb.cbt.screen.main.quiz.add

data class QuizAddUiState(
    val accessToken: String = "",
    val title: String = "",
    val content: String = "",
    val category: String = "",
    val type: Int = 0,
    val answer: String = "",
    val choice1: String = "",
    val choice2: String = "",
    val choice3: String = "",
    val choice4: String = "",
)