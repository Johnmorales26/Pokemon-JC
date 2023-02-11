package com.johndev.pokedexjc.ui.pokedex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.model.PokemonEntity
import com.johndev.pokedexjc.ui.components.PokeDexCard
import com.johndev.pokedexjc.ui.pokedex.viewModel.PokedexViewModel
import des.c5inco.pokedexer.model.Pokemon

@Composable
fun PokedexList(pokedexViewModel: PokedexViewModel, onIdSelected: (Int) -> Unit) {

    val pokemonList: MutableList<PokemonEntity> by pokedexViewModel.allPokemon.observeAsState(initial = mutableListOf())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        content = {
            items(pokemonList.size) {index ->
                val item = pokemonList[index]

                val pokemon = getPokemon(item)
                PokeDexCard(
                    modifier = Modifier,
                    pokemon = pokemon
                ) {
                    onIdSelected(pokemon.id)
                }
            }
        }
    )
}

fun getPokemon(pokemonEdit: PokemonEntity): Pokemon {
    return Pokemon(
        id = pokemonEdit.id,
        name = pokemonEdit.name.orEmpty().replaceFirstChar(Char::titlecase),
        description = "",
        typeOfPokemon = pokemonEdit.type.orEmpty().replaceFirstChar(Char::titlecase),
        category = "",
        image = pokemonEdit.imageUrl.orEmpty(),
        height = pokemonEdit.height!!.toDouble(),
        weight = pokemonEdit.weight!!.toDouble(),
        genderRate = 0,
        hp = 0,
        attack = 0,
        defense = 0,
        specialAttack = 0,
        specialDefense = 0,
        speed = 0,
        evolutionChain = listOf()
    )
}