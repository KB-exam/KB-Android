package com.kb.cbt.screen.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kb.cbt.R
import com.kb.cbt.composable.BackOnPressed

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    BackOnPressed()

    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = "HomeScreen")

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp, end = 12.dp),
            onClick = {

            }) {
            Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "")
        }
    }
}