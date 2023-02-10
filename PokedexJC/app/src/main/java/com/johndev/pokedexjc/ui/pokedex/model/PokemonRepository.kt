package com.johndev.pokedexjc.ui.pokedex.model

import com.johndev.pokedexjc.model.PokemonEntity
import com.johndev.pokedexjc.ui.pokedex.model.RoomPokemonDatabase

class PokemonRepository {

    private val roomDatabase = RoomPokemonDatabase()

    suspend fun addPokemon(pokemonEntity: PokemonEntity) = roomDatabase.addPokemon(pokemonEntity)

    suspend fun getAllPokemon() = roomDatabase.getAllPokemon()

}