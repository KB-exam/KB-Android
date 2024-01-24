package com.kb.cbt.model.service

import com.kb.cbt.model.RegisterData
import com.kb.cbt.model.SignUpStatus
import com.kb.cbt.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("signup")
    suspend fun createUser(@Body registerData: RegisterData): SignUpStatus
}