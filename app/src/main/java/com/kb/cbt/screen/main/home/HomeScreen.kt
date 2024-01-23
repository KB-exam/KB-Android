package com.kb.cbt.screen.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kb.cbt.composable.BackOnPressed

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    BackOnPressed()

    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = "HomeScreen")
    }
}