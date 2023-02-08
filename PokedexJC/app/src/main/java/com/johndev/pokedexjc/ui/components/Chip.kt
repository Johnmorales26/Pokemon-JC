package com.johndev.pokedexjc.ui.components

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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp

@Composable
fun ChipType(
    label: String,
    modifier: Modifier
) {
    Card(
        modifier = modifier.height(32.dp)
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label.capitalize(),
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                style = MaterialTheme.typography.body2
            )
        }
    }
}