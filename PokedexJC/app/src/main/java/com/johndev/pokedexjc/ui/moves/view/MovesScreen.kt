package com.johndev.pokedexjc.ui.moves.viewModel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.johndev.pokedexjc.model.entity.MoveEntity
import com.johndev.pokedexjc.ui.components.CardMoves

@Composable
fun MovesList(movesViewModel: MovesViewModel) {
    val move: List<MoveEntity> by movesViewModel.movesRoom.observeAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        move.let {
            items(it.size) { index ->
                val item = it[index]
                CardMoves(item)
            }
        }
    }
}