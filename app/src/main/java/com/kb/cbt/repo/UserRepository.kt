package com.kb.cbt.repo

import com.kb.cbt.model.LoginData
import com.kb.cbt.model.LoginStatus
import com.kb.cbt.model.Quiz
import com.kb.cbt.model.QuizLenData
import com.kb.cbt.model.RegisterData
import com.kb.cbt.model.SignUpStatus
import com.kb.cbt.model.User
import com.kb.cbt.model.service.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun createUser(registerData: RegisterData): SignUpStatus {
        return apiService.createUser(registerData)
    }

    suspend fun login(loginData: LoginData): LoginStatus {
        return apiService.login(loginData)
    }

    suspend fun getExam(quizLenData: QuizLenData): List<Quiz> {
        return apiService.getExam(quizLenData)
    }
}