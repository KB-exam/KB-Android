package com.kb.cbt.model.service.impl

import com.kb.cbt.model.service.AccountService
import com.kb.cbt.model.service.Authentication
import com.kb.cbt.model.LoginData
import com.kb.cbt.model.RegisterData
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val auth: Authentication
) : AccountService {
    override suspend fun authenticate(registerData: RegisterData) {
        auth.register(registerData)
    }

    override suspend fun login(loginData: LoginData) {
        auth.login(loginData)
    }

    override suspend fun logout() {
        auth.logout()
    }

}