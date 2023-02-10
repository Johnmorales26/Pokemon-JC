package com.johndev.pokedexjc.ui.pokedexDetails.section

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.model.dataPokemon.Stat
import com.johndev.pokedexjc.ui.components.ItemBaseStats

@Composable
fun SectionBaseStats(stats: List<Stat>, colorType: Color) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(stats.size) {
            ItemBaseStats(stats[it], colorType)
        }
    }
}