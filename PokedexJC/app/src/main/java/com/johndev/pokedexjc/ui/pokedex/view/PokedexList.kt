package com.johndev.pokedexjc.ui.pokedex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.model.entity.PokemonEntity
import com.johndev.pokedexjc.ui.components.PokeDexCard

@Composable
fun PokedexList(pokemonList: List<PokemonEntity>, onIdSelected: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        content = {
            items(pokemonList.size) {index ->
                val item = pokemonList[index]
                PokeDexCard(
                    modifier = Modifier,
                    pokemon = item
                ) {
                    onIdSelected(it.id)
                }
            }
        }
    )
}