package com.kb.cbt.repo

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
import com.kb.cbt.model.service.ApiService
import retrofit2.Response
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

    suspend fun makeQuestion(quizAddQuizData: AddQuizData): AddQuizStatus {
        return apiService.makeQuestion(quizAddQuizData)
    }

    suspend fun allQuestion(accessToken: String): Response<AddQuizStatus> {
        val response = apiService.allQuestion(AllQuestion(accessToken))

        return when (response.code()) {
            200 -> response // Success
            400 -> response
            402 -> throw NoUserException(response.errorBody()?.string()) // Handle 402
            403 -> throw InvalidTokenException(response.errorBody()?.string()) // Handle 403
            else -> throw Exception("Unexpected error") // Handle other codes if needed
        }
    }
}

// Custom exception for 402 status code
class NoUserException(message: String?) : Exception(message)

// Custom exception for 403 status code
class InvalidTokenException(message: String?) : Exception(message)