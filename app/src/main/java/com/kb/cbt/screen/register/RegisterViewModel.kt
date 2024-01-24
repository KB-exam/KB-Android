package com.kb.cbt.screen.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kb.cbt.model.RegisterData
import com.kb.cbt.repo.UserRepository
import com.kb.cbt.screen.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    var uiState = mutableStateOf(RegisterUiState())
        private set

    private val employeeNumber
        get() = uiState.value.employeeNumber

    private val name
        get() = uiState.value.name

    private val password
        get() = uiState.value.password

    private val passwordRepeat
        get() = uiState.value.passwordRepeat

    fun onEmployeeNumberChange(newValue: Int) {
        uiState.value = uiState.value.copy(employeeNumber = newValue)
    }

    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(name = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onPasswordRepeatChange(newValue: String) {
        uiState.value = uiState.value.copy(passwordRepeat = newValue)
    }

    fun onClickRegister() {
        if(uiState.value.password == uiState.value.passwordRepeat) {
            viewModelScope.launch {
                userRepository.createUser(RegisterData(
                    employeeNumber = uiState.value.employeeNumber,
                    name = uiState.value.name,
                    password = uiState.value.password
                ))
            }
        }
    }

    fun popUpBackStack(openAndPopUp: (String) -> Unit) {
        openAndPopUp("LoginScreen")
    }
}