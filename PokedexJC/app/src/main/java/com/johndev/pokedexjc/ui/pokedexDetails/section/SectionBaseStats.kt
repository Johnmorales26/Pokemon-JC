package com.johndev.pokedexjc.ui.pokedexDetails.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.model.entity.PokemonEntity
import com.johndev.pokedexjc.ui.components.ItemBaseStats

@Composable
fun SectionBaseStats(pokemon: PokemonEntity, colorType: Color) {
    val typeStats = listOf("hp", "attack", "defense", "special attack", "speed")
    val stats: Map<String, Int> = mapOf(
        "hp" to pokemon.hp!!, "attack" to pokemon.attack!!,
        "defense" to pokemon.defense!!, "special attack" to pokemon.special_attack!!,
        "special defense" to pokemon.special_defense!!, "speed" to pokemon.speed!!
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        (0..4).forEach {
            ItemBaseStats(stats[typeStats[it]], typeStats[it], colorType)
        }
    }
}