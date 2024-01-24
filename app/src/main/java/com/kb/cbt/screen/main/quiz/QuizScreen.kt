package com.kb.cbt.screen.main.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kb.cbt.composable.BackOnPressed
import com.kb.cbt.R.drawable as AppIcon

@Composable
fun QuizScreen(
    openAndPopUp: (String) -> Unit,
    viewModel: QuizViewModel = hiltViewModel(),
) {
    BackOnPressed()

    val listState = rememberLazyListState()


    Box(modifier = Modifier.fillMaxSize()) {
       // Text(modifier = Modifier.align(Alignment.Center), text = "${viewModel.quizListStorage}")

        val quiz = viewModel.printQuiz()
        Text(modifier = Modifier.align(Alignment.Center), text = quiz.toString())

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = listState
        ) {

        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp, end = 12.dp),
            onClick = {

        }) {
            Icon(painter = painterResource(id = AppIcon.ic_add), contentDescription = "")
        }
    }


}