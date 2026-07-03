package com.rjp.smartstruct.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = SmartBlue,
    secondary = SmartCyan,
    background = SmartBg,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White
)

@Composable
fun SmartTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
