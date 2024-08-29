package com.missclick.spy.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class SpyShapes(
    val button: Shape,
    val frame: Shape,
    val rectangle: Shape,
)

val spyShapes = SpyShapes(
    button = RoundedCornerShape(16.dp),
    frame = RoundedCornerShape(4.dp),
    rectangle = RoundedCornerShape(0.dp),
)

val LocalShapes = staticCompositionLocalOf<SpyShapes> {
    error("No shapes provided")
}