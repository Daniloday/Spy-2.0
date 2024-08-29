package com.missclick.spy.core.ui.theme

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.missclick.spy.core.ui.R

@Immutable
data class SpyTypography(
    val h18: TextStyle,
    val h20: TextStyle,
    val h22: TextStyle,
    val h24: TextStyle,
    val h28: TextStyle,
    val h30: TextStyle,
    val h42: TextStyle,
    val h48: TextStyle,
    val h54: TextStyle,
    val h60: TextStyle,
    val h168: TextStyle,
)

val spyTypography = SpyTypography(
    h18 = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
    h20 = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
    h22 = TextStyle(
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
    h24 = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
    h28 = TextStyle(
        fontSize = 28.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
    h30 = TextStyle(
        fontSize = 30.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
    h42 = TextStyle(
        fontSize = 42.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
    h48 = TextStyle(
        fontSize = 48.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
    h54 = TextStyle(
        fontSize = 54.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
    h60 = TextStyle(
        fontSize = 60.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
    h168 = TextStyle(
        fontSize = 168.sp,
        fontFamily = FontFamily(Font(R.font.kelly_slab)),
    ),
)

val LocalTypography = staticCompositionLocalOf<SpyTypography> {
    error("No typography provided")
}