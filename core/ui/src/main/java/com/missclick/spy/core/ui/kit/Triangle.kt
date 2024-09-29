package com.missclick.spy.core.ui.kit

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import com.missclick.spy.core.ui.theme.AppTheme


@Composable
fun Triangle(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
    enabledColor: Color = AppTheme.colors.secondary,
    enabledBorderColor: Color = AppTheme.colors.secondary,
    disabledColor: Color = AppTheme.colors.onSecondary,
    disabledBorderColor: Color = AppTheme.colors.onSecondary,
    isBorderActive: Boolean = false
) {
    Box(
        modifier = modifier
            .clickable(enabled = isEnabled, onClick = onClick)
            .drawWithCache {
                val roundedPolygon = RoundedPolygon(
                    numVertices = 3,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2,
                    rounding = CornerRounding(
                        size.minDimension / 10f,
                        smoothing = 0.1f
                    )
                )
                val roundedPolygonPath = roundedPolygon.toPath().asComposePath()
                onDrawBehind {
                    drawPath(
                        path = roundedPolygonPath,
                        color = if (isEnabled) enabledColor else disabledColor
                    )
                    if (isBorderActive) {
                        drawPath(
                            path = roundedPolygonPath,
                            color = if (isEnabled) enabledBorderColor else disabledBorderColor,
                            style = Stroke(width = 4f, )
                        )
                    }
                }
            }
            .size(100.dp)

    )
}