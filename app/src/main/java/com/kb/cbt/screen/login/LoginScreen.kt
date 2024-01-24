package com.kb.cbt.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kb.cbt.R
import com.kb.cbt.composable.BasicButton
import com.kb.cbt.composable.H1Title
import com.kb.cbt.composable.NormalTextField
import com.kb.cbt.composable.PasswordField
import com.kb.cbt.screen.splash.SplashViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    openAndPopUp: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        H1Title(modifier = Modifier, text = "Login")


//        TextField(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp),
//            value = uiState.employeeNumber,
//            onValueChange = viewModel::onEmployeeNumberChange,
//            label = { Text("직원번호") },
//        )

//        TextField(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 8.dp),
//            value = uiState.password,
//            onValueChange = viewModel::onPasswordChange,
//            label = { Text("비밀번호") },
//        )

        NormalTextField(
            value = uiState.employeeNumber,
            placeholder = "직원번호",
            onNewValue = viewModel::onEmployeeNumberChange,
            modifier = Modifier.padding(top = 16.dp)
        )

        PasswordField(
            value = uiState.password,
            placeholder = "비밀번호",
            onNewValue = viewModel::onPasswordChange,
            modifier = Modifier.padding(top = 12.dp)
        )

        BasicButton(
            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
            color = colorResource(R.color.primary_1),
            rippleColor = colorResource(R.color.primary_2),
            text = "로그인",
        ) {
            viewModel.onClickLogin(openAndPopUp)
        }
        BasicButton(
            modifier = Modifier.padding(top = 8.dp, start = 12.dp, end = 12.dp),
            color = colorResource(R.color.primary_gray1),
            rippleColor = colorResource(R.color.primary_gray2),
            text = "회원가입",
        ) {
            viewModel.onClickRegister(openAndPopUp)
        }
    }
}