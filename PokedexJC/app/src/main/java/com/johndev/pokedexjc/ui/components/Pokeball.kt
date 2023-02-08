package com.johndev.pokedexjc.ui.components

import com.johndev.pokedexjc.R
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource

@Composable
fun RotatingPokeBall(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing)
        )
    )

    PokeBall(
        modifier = modifier
            .graphicsLayer {
                rotationZ = angle
            },
        tint = Color(0xffF5F5F5),
        alpha = 0.25f
    )
}

@Composable
fun PokeBall(
    modifier: Modifier = Modifier,
    tint: Color = Color.Black,
    alpha: Float = 1f,
) {
    PokeBallBase(modifier = modifier, tint = tint, alpha = alpha)
}

@Composable
private fun PokeBallBase(
    modifier: Modifier = Modifier,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    imageResId: Int = R.drawable.pokeball,
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = imageResId),
        contentDescription = "PokeBall",
        alpha = alpha,
        colorFilter = ColorFilter.tint(tint)
    )
}