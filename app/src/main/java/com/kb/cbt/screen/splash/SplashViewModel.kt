package com.kb.cbt.screen.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): ViewModel() {
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp("LoginScreen", "SplashScreen")
    }
}