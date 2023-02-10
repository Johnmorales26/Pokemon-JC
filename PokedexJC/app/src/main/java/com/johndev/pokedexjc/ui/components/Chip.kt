package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChipType(
    label: String,
    modifier: Modifier,
    color: Color?
) {
    Card(
        modifier = modifier.height(32.dp)
    ) {
        Column(
            modifier = Modifier.background(
                color ?: Color.Transparent),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label.replaceFirstChar(Char::titlecase),
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                style = MaterialTheme.typography.caption
            )
        }
    }
}