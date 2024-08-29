package com.missclick.spy.core.ui.kit.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.missclick.spy.core.ui.theme.AppTheme
import com.missclick.spy.core.ui.theme.SpyTheme

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = AppTheme.colors.secondary
    )
) {
    Button(
        modifier = modifier
            .padding(horizontal = AppTheme.dimens.horizontalPadding)
            .fillMaxWidth()
            .height(AppTheme.dimens.secondaryButtonHeight),
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        shape = AppTheme.shapes.button,
    ) {
        Text(text = text, style = AppTheme.types.h60)
    }
}

@Composable
@Preview
private fun SecondaryButtonPreview() {
    SpyTheme {
        SecondaryButton(onClick = { /*TODO*/ }, text = "test")
    }
}