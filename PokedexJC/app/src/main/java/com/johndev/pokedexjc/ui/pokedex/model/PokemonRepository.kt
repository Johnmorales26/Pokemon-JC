package com.johndev.pokedexjc.ui.pokedex.model

import com.johndev.pokedexjc.model.entity.PokemonEntity

class PokemonRepository {

    private val roomDatabase = PokemonDatabase()

    suspend fun insert(pokemonEntity: PokemonEntity) = roomDatabase.insert(pokemonEntity)

    suspend fun getAll() = roomDatabase.getAll()

    suspend fun findById(id: Int) = roomDatabase.findById(id)

}