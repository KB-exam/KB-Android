package com.kb.cbt.model

data class AddQuizData(
    val accessToken: String,
    val title: String = "",
    val content: String? = "",
    val type: Int?,
    val choice1: String?,
    val choice2: String?,
    val choice3: String?,
    val choice4: String?,
    val answer: String?,
    val category: String?,
)