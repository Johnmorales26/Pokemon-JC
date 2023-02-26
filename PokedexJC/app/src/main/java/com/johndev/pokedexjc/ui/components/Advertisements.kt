package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.R

@Composable
fun AnnouncementFavoritesEmpty() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokebola),
            contentDescription = null
        )
        Text(
            text = "You haven't marked any Pokémon as a favorite...\nMark a Pokémon as a favorite by tapping on the details section.",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AnnouncementCapturedEmpty() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokebola),
            contentDescription = null
        )
        Text(
            text = "You haven't marked any Pokemon as caught...\nMark a Pokemon as caught by tapping the button in the details section.",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}