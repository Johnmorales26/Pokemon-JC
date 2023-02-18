package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.model.entity.PokemonEntity
import com.johndev.pokedexjc.model.entity.pokemonColor
import com.johndev.pokedexjc.ui.pokedexDetails.section.SectionAbout
import com.johndev.pokedexjc.ui.pokedexDetails.section.SectionBaseStats

@Composable
fun TabsPokemonDetails(pokemon: PokemonEntity) {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("About", "Base Stats")//, "Evolution", "Moves")
    val pokemonColor = pokemonColor(
        (if (pokemon.types == null) {
            "Fire"
        } else {
            pokemon.typeOfPokemon.toString()
        })
    )
    // Reuse the default offset animation modifier, but use our own indicator
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        FancyIndicator(pokemonColor, Modifier.tabIndicatorOffset(tabPositions[state]))
    }
    Column {
        TabRow(
            selectedTabIndex = state,
            indicator = indicator
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = state == index,
                    onClick = { state = index }
                )
            }
        }
        when(state) {
            0 -> SectionAbout(pokemon)
            1 -> SectionBaseStats(pokemon, pokemonColor)
            //2 -> SectionEvolutions()
            //3 -> SectionMoves()
        }
    }
}

@Composable
fun FancyIndicator(color: Color, modifier: Modifier = Modifier) {
    // Draws a rounded rectangular with border around the Tab, with a 5.dp padding from the edges
    // Color is passed in as a parameter [color]
    Box(
        modifier
            .padding(5.dp)
            .fillMaxSize()
            .border(BorderStroke(2.dp, color), RoundedCornerShape(5.dp))
    )
}