package com.kb.cbt.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kb.cbt.R
import com.kb.cbt.ui.theme.kbf_display

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = colorResource(R.color.purple_700),
    rippleColor: Color = colorResource(R.color.purple_200),
    action: () -> Unit
) {
    Button(
        onClick = action,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        shape = RoundedCornerShape(24.dp),
        colors =
        ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = rippleColor,
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = kbf_display,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(R.color.white)
        )
    }
}