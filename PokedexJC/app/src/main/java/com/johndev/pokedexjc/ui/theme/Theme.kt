package com.johndev.pokedexjc.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val DarkColorPalette = darkColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryDarkColor,
    secondary = SecondaryColor,
    secondaryVariant = SecondaryDarkColor,
    background = BackgroundDarkColor,
    surface = SurfaceDarkColor,
    onPrimary = PrimaryTextColor,
    onSecondary = SecondaryTextColor,
    onBackground = OnBackgroundDarkColor,
    onSurface = OnSurfaceDarkColor,
)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryDarkColor,
    secondary = SecondaryColor,
    secondaryVariant = SecondaryDarkColor,
    background = BackgroundLightColor,
    surface = SurfaceLightColor,
    onPrimary = PrimaryTextColor,
    onSecondary = SecondaryTextColor,
    onBackground = OnBackgroundLightColor,
    onSurface = OnSurfaceLightColor,
)

@Composable
fun PokedexJCTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}