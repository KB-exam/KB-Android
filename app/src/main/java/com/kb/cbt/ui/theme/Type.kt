package com.kb.cbt.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kb.cbt.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val kbf_display = FontFamily(
    Font(R.font.kbf_bold_display, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.kbf_medium_display, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.kbf_light_display, FontWeight.Light, FontStyle.Normal),
)

val kbf_text = FontFamily(
    Font(R.font.kbf_bold_text, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.kbf_medium_text, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.kbf_light_text, FontWeight.Light, FontStyle.Normal),
)