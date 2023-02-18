package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults.IndicatorBackgroundOpacity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.data.PokemonUtils.titleCase

@Composable
fun ItemBaseStats(valueStats: Int?, nameStats: String, colorType: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.weight(3f),
            text = titleCase(nameStats),
            color = colorResource(id = R.color.primaryDarkColor),
            style = MaterialTheme.typography.body2
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp),
            text = valueStats.toString(),
            style = MaterialTheme.typography.body1
        )
        if (valueStats != null) {
            LinearProgressIndicator(
                modifier = Modifier.weight(6f),
                progress = valueStats / 100.0f,
                color = colorType,
                backgroundColor = colorType.copy(alpha = IndicatorBackgroundOpacity)
            )
        }
    }
}

@Composable
fun ItemMoves(move: String) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(24.dp).weight(2f),
                painter = painterResource(id = R.drawable.img_punch),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 8.dp).weight(8f),
                text = move.replaceFirstChar(Char::titlecase),
                style = MaterialTheme.typography.body1
            )
        }
    }
}