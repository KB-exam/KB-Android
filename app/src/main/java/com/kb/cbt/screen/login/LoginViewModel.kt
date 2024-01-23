package com.kb.cbt.screen.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val employeeNumber
        get() = uiState.value.employeeNumber

    private val password
        get() = uiState.value.password

    fun onEmployeeNumberChange(newValue: String) {
        uiState.value = uiState.value.copy(employeeNumber = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onClickRegister(openAndPopUp: (String) -> Unit) {
        openAndPopUp("RegisterScreen")
    }

    fun onClickLogin(openAndPopUp: (String) -> Unit) {
        openAndPopUp("MainScreen")
    }
}