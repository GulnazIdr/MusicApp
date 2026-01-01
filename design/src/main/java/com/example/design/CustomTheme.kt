package com.example.design

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
data class CustomColors(
    val accentColor: Color = accent,
    val inActive: Color = accentInActive,
    val errorColor: Color = error,
    val successColor: Color = success,
    val blackColor: Color = black,
    val whiteColor: Color = white,
    val inputBgColor: Color = inputBg,
    val inputStringColor: Color = inputString,
    val inputIconColor: Color = inputIcon,
    val placeholderColor: Color = placeholder,
    val descriptionColor: Color = description,
    val cardStringColor: Color = cardString,
    val default: Color = Color.Unspecified,
    val transparent: Color = Color.Transparent,
    val inputStrokeColor: Color = inputStroke,
    val captionColor: Color = caption
)

@Immutable
data class CustomTypography(
    val title1Heavy: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 28.sp,
        letterSpacing = 0.33.em,
        color = black
    ),
    val headLineMedium: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp,
        color = black,
        letterSpacing = (-0.0032).em
    ),
    val headLineRegular: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp,
        color = caption,
        letterSpacing = (-0.0032).em
    ),
    val captionSemibold: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 20.sp,
        color = white,
        letterSpacing = 0.em
    ),
    val captionRegular: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp,
        color = black,
        letterSpacing = 0.em
    ),
    val caption2Regular: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 16.sp,
        color = black,
        letterSpacing = 0.em
    ),
    val title2Regular: TextStyle = TextStyle.Default,
    val title2Semibold: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 28.sp,
        color = black,
        letterSpacing = 0.38.em
    ),
    val title3Semibold: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 24.sp,
        color = white,
        letterSpacing = 0.em
    ),
    val title3Medium: TextStyle = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 20.sp,
        color = white,
        letterSpacing = 0.em
    ),
    val textRegular: TextStyle = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp,
        color = white,
        letterSpacing = 0.em
    ),
    val textMedium: TextStyle = TextStyle.Default
)

val LocalCustomColor = staticCompositionLocalOf { CustomColors() }
val LocalTypography = staticCompositionLocalOf { CustomTypography() }

@Composable
fun CustomTheme(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCustomColor provides CustomColors(),
        LocalTypography provides CustomTypography()
    ) { content() }
}

object CustomTheme{
    val customColors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomColor.current

    val customTypography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}