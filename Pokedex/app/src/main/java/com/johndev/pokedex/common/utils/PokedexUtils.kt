package com.johndev.pokedex.common.utils

import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.lazy.LazyListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch

object PokedexUtils {

    fun LazyListState.disableScrolling(scope: CoroutineScope) {
        scope.launch {
            scroll(scrollPriority = MutatePriority.PreventUserInput) {
                // Await indefinitely, blocking scrolls
                awaitCancellation()
            }
        }
    }

    fun LazyListState.reenableScrolling(scope: CoroutineScope) {
        scope.launch {
            scroll(scrollPriority = MutatePriority.PreventUserInput) {
                // Do nothing, just cancel the previous indefinite "scroll"
            }
        }
    }

    fun String.capitalize(): String {
        return this.replaceFirstChar { it.uppercase() }
    }

    fun getImageUrl(id: Int) = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"

    fun formatNumberId(id: Int): String {
        return "#${String.format("%03d", id)}"
    }

}