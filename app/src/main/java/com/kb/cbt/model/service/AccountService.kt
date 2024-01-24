package com.kb.cbt.model.service

import com.kb.cbt.model.LoginData
import com.kb.cbt.model.RegisterData

interface AccountService {
    suspend fun authenticate(registerData: RegisterData)
    suspend fun login(loginData: LoginData)
    suspend fun logout()
}