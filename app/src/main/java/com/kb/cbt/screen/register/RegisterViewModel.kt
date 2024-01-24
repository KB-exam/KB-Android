package com.kb.cbt.screen.register

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kb.cbt.model.RegisterData
import com.kb.cbt.repo.UserRepository
import com.kb.cbt.screen.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

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

    fun onEmployeeNumberChange(newValue: String) {
        if(newValue.isDigitsOnly()) {
            uiState.value = uiState.value.copy(employeeNumber = newValue)
        }
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

    fun onClickRegister(openAndPopUp: (String) -> Unit) {
        if(uiState.value.password == uiState.value.passwordRepeat) {
            var registerStatus: String = ""
            viewModelScope.launch {
                try {
                    registerStatus = userRepository.createUser(RegisterData(
                        empNumber = uiState.value.employeeNumber.toInt(),
                        nickname = uiState.value.name,
                        password = uiState.value.password
                    )).message
                } catch(e: Exception) {

                }

                Log.d(TAG, "registerStatus: $registerStatus")
                if(registerStatus == "User created successfully.") {
                    openAndPopUp("LoginScreen")
                }
            }


        } else {

        }
    }

    fun popUpBackStack(openAndPopUp: (String) -> Unit) {
        openAndPopUp("LoginScreen")
    }
}