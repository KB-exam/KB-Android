package com.kb.cbt.model.service

import com.kb.cbt.model.AddQuizData
import com.kb.cbt.model.AddQuizStatus
import com.kb.cbt.model.AllQuestion
import com.kb.cbt.model.LoginData
import com.kb.cbt.model.LoginStatus
import com.kb.cbt.model.Quiz
import com.kb.cbt.model.QuizLenData
import com.kb.cbt.model.RegisterData
import com.kb.cbt.model.SignUpStatus
import com.kb.cbt.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("signup")
    suspend fun createUser(@Body registerData: RegisterData): SignUpStatus

    @POST("login")
    suspend fun login(@Body loginData: LoginData): LoginStatus

    @POST("getExam")
    suspend fun getExam(@Body quizLenData: QuizLenData): List<Quiz>

    @POST("makeQuestion")
    suspend fun makeQuestion(@Body quizAddQuizData: AddQuizData): AddQuizStatus

    @POST("allQuestion")
    suspend fun allQuestion(@Body allQuestion: AllQuestion): Response<AddQuizStatus>

}