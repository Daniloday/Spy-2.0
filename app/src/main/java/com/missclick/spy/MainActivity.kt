package com.missclick.spy

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import com.missclick.spy.core.navigation.NavGraph
import com.missclick.spy.core.ui.theme.AppTheme
import com.missclick.spy.core.ui.theme.SpyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(Resources.getSystem().configuration.locales[0].language)

        setContent {
            println(stringResource(id = R.string.app_name))
            SpyTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = AppTheme.colors.background
                        )
                ) {
                    NavGraph()
                }
            }
        }
    }
}

