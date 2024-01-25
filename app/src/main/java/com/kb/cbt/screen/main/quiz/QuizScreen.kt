package com.kb.cbt.screen.main.quiz

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kb.cbt.composable.BackOnPressed
import com.kb.cbt.composable.H1Title
import com.kb.cbt.composable.H2Title
import com.kb.cbt.composable.H3Content
import com.kb.cbt.model.Quiz
import com.kb.cbt.R.drawable as AppIcon

@Composable
fun QuizScreen(
    openAndPopUp: (String) -> Unit,
    viewModel: QuizViewModel = hiltViewModel(),
) {
    BackOnPressed()

    val listState = rememberLazyListState()
    val quizList = remember { mutableStateOf<List<Quiz>>(mutableListOf()) }
    val quizState = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.printQuiz {
            quizList.value = it
            Log.d(TAG, "quizList: ${quizList.value}")
            quizState.value = true
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)) {
        //(modifier = Modifier.align(Alignment.Center), text = "${viewModel.quizListStorage}")
        //Text(modifier = Modifier.align(Alignment.Center), text = quizList.value.toString())

        Column(modifier = Modifier.fillMaxWidth()) {
            if(quizState.value) {
                H1Title(text = quizList.value[0].title)
                H2Title(
                    modifier = Modifier.padding(top = 8.dp),
                    text = quizList.value[0].content
                )
            }
        }

//        Column(modifier = Modifier
//            .align(Alignment.BottomStart)
//            .padding(bottom = 80.dp)) {
//            if(quizState.value) {
//                H3Content(text = ("1. " + quizList.value[0].choice1))
//                H3Content(text = ("2. " + quizList.value[0].choice2))
//                H3Content(text = ("3. " + quizList.value[0].choice3))
//                H3Content(text = ("4. " + quizList.value[0].choice4))
//            }
//        }

        val selectedValue = remember { mutableStateOf("") }

        val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
        val onChangeState: (String) -> Unit = { selectedValue.value = it }

        if(quizState.value) {
            val items = listOf(
                ("1. " + quizList.value[0].choice1),
                ("2. " + quizList.value[0].choice2),
                ("3. " + quizList.value[0].choice3),
                ("4. " + quizList.value[0].choice4),
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 80.dp)
            ) {
                Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")
                items.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.selectable(
                            selected = isSelectedItem(item),
                            onClick = { onChangeState(item) },
                            role = Role.RadioButton
                        ).padding(8.dp)
                    ) {
                        RadioButton(
                            selected = isSelectedItem(item),
                            onClick = null
                        )
                        H3Content(
                            text = item,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

//        LazyColumn(
//            modifier = Modifier.fillMaxWidth(),
//            state = listState
//        ) {
//
//        }


    }


}