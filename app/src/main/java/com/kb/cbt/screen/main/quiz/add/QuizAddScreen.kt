package com.kb.cbt.screen.main.quiz.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kb.cbt.composable.BasicButton
import com.kb.cbt.composable.H1Title
import com.kb.cbt.composable.H2Title
import com.kb.cbt.composable.H3Content
import com.kb.cbt.composable.NormalTextField
import com.kb.cbt.screen.register.RegisterViewModel

@Composable
fun QuizAddScreen(
    openAndPopUp: (String) -> Unit,
    viewModel: QuizAddViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    val selectedValue = remember { mutableStateOf("4지선다") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            H1Title(text = "퀴즈 만들기")
            NormalTextField(
                value = uiState.title,
                placeholder = "제목",
                onNewValue = viewModel::onTitleChange
            )
            NormalTextField(
                value = uiState.content,
                placeholder = "내용",
                onNewValue = viewModel::onContentChange
            )
            val items = listOf(
                "4지선다",
                "OX문제"
            )
            Column(
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
            if(selectedValue.value == "4지선다") {
                H2Title(text = "4지선다")
                NormalTextField(
                    value = uiState.choice1,
                    placeholder = "1번 문항",
                    onNewValue = viewModel::onChoice1Change
                )
                NormalTextField(
                    value = uiState.choice2,
                    placeholder = "2번 문항",
                    onNewValue = viewModel::onChoice2Change
                )
                NormalTextField(
                    value = uiState.choice3,
                    placeholder = "3번 문항",
                    onNewValue = viewModel::onChoice3Change
                )
                NormalTextField(
                    value = uiState.choice4,
                    placeholder = "4번 문항",
                    onNewValue = viewModel::onChoice4Change
                )
                NormalTextField(
                    value = uiState.answer,
                    placeholder = "정답",
                    onNewValue = viewModel::onAnswerChange
                )
            } else {
                H2Title(text = "OX문제")
                NormalTextField(
                    value = uiState.answer,
                    placeholder = "정답(O 또는 X로만 입력)",
                    onNewValue = viewModel::onAnswerChange
                )
            }
        }
        BasicButton(
            text = "만들기",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 12.dp)
        ) {
            viewModel.onSelectValue(selectedValue.value)
            viewModel.onClickUpload(openAndPopUp)
        }
    }
}