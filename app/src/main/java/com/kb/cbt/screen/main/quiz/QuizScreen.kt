package com.kb.cbt.screen.main.quiz

import android.content.ContentValues.TAG
import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kb.cbt.composable.BackOnPressed
import com.kb.cbt.composable.BasicButton
import com.kb.cbt.composable.H1Title
import com.kb.cbt.composable.H2Title
import com.kb.cbt.composable.H3Content
import com.kb.cbt.model.Quiz
import com.kb.cbt.R.drawable as AppIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    openAndPopUp: (String) -> Unit,
    viewModel: QuizViewModel = hiltViewModel(),
) {
    BackOnPressed()

    val listState = rememberLazyListState()
    val quizList = remember { mutableStateOf<List<Quiz>>(mutableListOf()) }
    val quizState = remember { mutableStateOf(false) }
    val alertState = remember { mutableStateOf(false) }
    val finishAlertState = remember { mutableStateOf(false) }

    val selectedValue = remember { mutableStateOf("") }
    val sfState = remember { mutableStateOf(Pair<Int, Int>(0, 0)) }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    LaunchedEffect(Unit) {
        viewModel.currentQuiz = 0
        sfState.value = Pair(0, 0)
        viewModel.printQuiz {
            if(it.isEmpty()) {

            } else {
                quizList.value = it
                Log.d(TAG, "quizList: ${quizList.value}")
                quizState.value = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        //(modifier = Modifier.align(Alignment.Center), text = "${viewModel.quizListStorage}")
        //Text(modifier = Modifier.align(Alignment.Center), text = quizList.value.toString())

        Column(modifier = Modifier.fillMaxWidth()) {
            if (quizState.value) {
                H1Title(text = quizList.value[viewModel.currentQuiz].title)
                H2Title(
                    modifier = Modifier.padding(top = 8.dp),
                    text = quizList.value[viewModel.currentQuiz].content ?: ""
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


        if (quizState.value) {
            if (quizList.value[viewModel.currentQuiz].type == 4) {
                val items = listOf(
                    "1)" + quizList.value[viewModel.currentQuiz].choice1 ?: "1.",
                    "2)" + quizList.value[viewModel.currentQuiz].choice2 ?: "2.",
                    "3)" + quizList.value[viewModel.currentQuiz].choice3 ?: "3.",
                    "4)" + quizList.value[viewModel.currentQuiz].choice4 ?: "4.",
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 116.dp)
                ) {
                    //Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")
                    items.forEach { item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .selectable(
                                    selected = isSelectedItem(item),
                                    onClick = { onChangeState(item) },
                                    role = Role.RadioButton
                                )
                                .padding(8.dp)
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
            } else {
                val items = listOf(
                    "O",
                    "X",
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 116.dp)
                ) {
                    //Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")
                    items.forEach { item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .selectable(
                                    selected = isSelectedItem(item),
                                    onClick = { onChangeState(item) },
                                    role = Role.RadioButton
                                )
                                .padding(8.dp)
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
        }
        BasicButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 72.dp),
            text = "답안 제출"
        ) {
            alertState.value = true
        }


//        LazyColumn(
//            modifier = Modifier.fillMaxWidth(),
//            state = listState
//        ) {
//
//        }
    }
    if(finishAlertState.value) {
        val onDismissRequest = {
            openAndPopUp("MainScreen")
            finishAlertState.value = false
        }
        AlertDialog(
            onDismissRequest = onDismissRequest,
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    H1Title(text = "완료")
                    H2Title(modifier = Modifier.padding(top = 12.dp), text = "정답: ${sfState.value.first}, 오답: ${sfState.value.second}" )
                }
            }
        }
    }
    
    if (alertState.value) {
        val onDismissRequest = {
            alertState.value = false
        }

        AlertDialog(
            onDismissRequest = onDismissRequest,
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    H3Content(
                        text = if(viewModel.compareQuiz(
                            selectedValue.value.split(")")[0],
                            quizList.value[viewModel.currentQuiz].answer ?: "nan"
                        ) == 1) "정답입니다."
                        else "오답입니다."
                    )
                    if(viewModel.compareQuiz(
                            selectedValue.value.split(")")[0],
                            quizList.value[viewModel.currentQuiz].answer ?: "nan"
                        ) == 1) {
                        BasicButton(text = "다음문제로") {
                            viewModel.nextQuiz() { s, f ->
                                sfState.value = Pair(s, f)
                                finishAlertState.value = true
                            }
                            viewModel.success()
                            onDismissRequest()
                        }
                    } else {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
//                            BasicButton(text = "다시하기") {
//                                onDismissRequest()
//                            }
                            BasicButton(text = "다음문제로") {
                                viewModel.nextQuiz() { s, f ->
                                    sfState.value = Pair(s, f)
                                    finishAlertState.value = true
                                }
                                viewModel.fail()
                                onDismissRequest()
                            }
                        }
                    }

                }
            }
        }
    }

}