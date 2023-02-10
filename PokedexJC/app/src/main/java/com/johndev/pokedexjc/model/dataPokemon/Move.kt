package com.johndev.pokedexjc.model.dataPokemon

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)