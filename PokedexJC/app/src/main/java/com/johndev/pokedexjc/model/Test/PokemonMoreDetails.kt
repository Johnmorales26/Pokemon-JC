package com.johndev.pokedexjc.model.Test

data class PokemonMoreDetails(
    val id: Int,
    val name: String,
    val order: Int,
    val pokemon: Pokemon,
    val types: List<Type>
)