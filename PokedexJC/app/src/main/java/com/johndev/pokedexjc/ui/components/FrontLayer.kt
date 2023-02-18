package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.model.entity.PokemonEntity
import com.johndev.pokedexjc.ui.theme.PokedexJCTheme

@Composable
fun PokemonFrontLayer(pokemon: PokemonEntity) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 32.dp)
    ) {
        TabsPokemonDetails(pokemon)
    }
}

@Preview
@Composable
private fun PokemonDetailsPreview() {

    PokedexJCTheme() {
        Surface(Modifier.fillMaxSize()) {

        }
    }
}