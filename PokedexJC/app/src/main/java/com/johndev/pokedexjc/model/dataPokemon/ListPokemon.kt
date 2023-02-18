package com.johndev.pokedexjc.model.dataPokemon

data class ListPokemon(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)