package com.missclick.spy.core.ui.kit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import com.missclick.spy.core.ui.theme.AppTheme


@Composable
fun Triangle(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    enabledColor: Color = AppTheme.colors.secondary,
    disabledColor: Color = AppTheme.colors.onSecondary,
) {
    Box(
        modifier = modifier
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
                }
            }
            .size(100.dp)
            .clickable(enabled = isEnabled, onClick = onClick)
    )
}