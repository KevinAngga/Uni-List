package com.angga.univlist.ui.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.angga.univlist.R

val Rubik = FontFamily(
    Font(
        resId = R.font.rubik_light,
        weight = FontWeight.Light
    ),

    Font(
        resId = R.font.rubik_regular,
        weight = FontWeight.Normal
    ),

    Font(
        resId = R.font.rubik_medium,
        weight = FontWeight.Medium
    ),

    Font(
        resId = R.font.rubik_semibold,
        weight = FontWeight.SemiBold
    ),

    Font(
        resId = R.font.rubik_bold,
        weight = FontWeight.Bold
    ),
)

val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 16.sp,
    ),

    bodyLarge = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 18.sp,
    ),

    labelLarge = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp,
        lineHeight = 28.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
)