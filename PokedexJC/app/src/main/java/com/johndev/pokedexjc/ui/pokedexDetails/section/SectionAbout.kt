package com.johndev.pokedexjc.ui.pokedexDetails.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.model.dataPokemon.PokemonComplete
import com.johndev.pokedexjc.ui.components.CardPokemonSize
import com.johndev.pokedexjc.ui.components.ItemMoves

@Composable
fun SectionAbout(pokemon: PokemonComplete) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CardPokemonSize(pokemon.height, pokemon.weight)
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Moves",
            style = MaterialTheme.typography.h6
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            content = {
                items(pokemon.moves.size) {index ->
                    val item = pokemon.moves[index]
                    ItemMoves(item)
                }
            }
        )
    }
}
