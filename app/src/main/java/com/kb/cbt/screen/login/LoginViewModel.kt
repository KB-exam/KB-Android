package com.kb.cbt.screen.login

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kb.cbt.composable.snackbar.SnackbarManager
import com.kb.cbt.model.LoginData
import com.kb.cbt.model.RegisterData
import com.kb.cbt.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.kb.cbt.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
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
        var loginStatus: String = ""
        viewModelScope.launch {
            try {
                loginStatus = userRepository.login(
                    LoginData(
                        empNumber = uiState.value.employeeNumber.toInt(),
                        password = uiState.value.password
                    )).message
            } catch(e: Exception) {
                Log.d(TAG, "exception: $e")
            }

            Log.d(ContentValues.TAG, "registerStatus: $loginStatus")
            if(loginStatus == "wrong password") {
                SnackbarManager.showMessage(AppText.login_error_password_invalid)
            } else if(loginStatus == "no user") {
                SnackbarManager.showMessage(AppText.login_error_no_user)
            } else if(loginStatus == "login fail") {
                SnackbarManager.showMessage(AppText.login_error)
            }
            else {
                Log.d(TAG, "Login: $loginStatus")
                if(loginStatus != "") {
                    openAndPopUp("MainScreen")
                }
                else {
                    SnackbarManager.showMessage(AppText.login_error)
                }
            }
        }
    }
}