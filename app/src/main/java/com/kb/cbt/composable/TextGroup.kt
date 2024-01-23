package com.kb.cbt.composable

import android.graphics.fonts.FontFamily
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.kb.cbt.ui.theme.kbf_display

@Composable
fun H1Title(modifier: Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 24.sp,
        fontFamily = kbf_display,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}