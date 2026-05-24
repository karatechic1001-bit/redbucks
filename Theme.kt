package com.football.tactics.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = FieldGreen,
    secondary = Gold,
    tertiary = GoldDark,
    background = FieldGreenDark,
    surface = DarkGray,
    onPrimary = White,
    onSecondary = DarkGray,
    onBackground = White,
    onSurface = White
)

private val LightColorScheme = lightColorScheme(
    primary = FieldGreen,
    secondary = Gold,
    tertiary = GoldDark,
    background = OffWhite,
    surface = White,
    onPrimary = White,
    onSecondary = DarkGray,
    onBackground = DarkGray,
    onSurface = DarkGray
)

@Composable
fun FootballTacticsQuizTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
