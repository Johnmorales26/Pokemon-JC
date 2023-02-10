package com.johndev.pokedexjc.model.dataMoves

data class MoveRetrofit(
    val id: Int,
    val accuracy: Int,
    val contest_type: ContestType,
    val damage_class: ContestType,
    val name: String,
    val power: Int,
    val type: Type
)