package com.kb.cbt.repo

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
}