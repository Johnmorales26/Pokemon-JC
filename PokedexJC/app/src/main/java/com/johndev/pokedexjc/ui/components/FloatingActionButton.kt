package com.johndev.pokedexjc.ui.components

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun FabCommon(
    painterRes: Int,
    onClicFab: () -> Unit
) {
    FloatingActionButton(onClick = { onClicFab() }) {
        Icon(painter = painterResource(id = painterRes), contentDescription = null)
    }
}

@Composable
fun ExtFabCommon(
    textRes: Int,
    iconRes: Int,
    onClicFab: () -> Unit
) {
    ExtendedFloatingActionButton(
        text = {
               Text(text = stringResource(id = textRes))
        },
        onClick = { onClicFab() },
        icon = {
            Icon(painter = painterResource(id = iconRes), contentDescription = null)
        }
    )
}