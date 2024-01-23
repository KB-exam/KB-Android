package com.kb.cbt.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    openAndPopUp: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    MainScreenView()
}