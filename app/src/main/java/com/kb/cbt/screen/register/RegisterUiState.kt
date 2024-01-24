package com.kb.cbt.screen.register

data class RegisterUiState (
    val employeeNumber: Int = 0,
    val name: String = "",
    val password: String = "",
    val passwordRepeat: String = ""
)