package com.johndev.pokedexjc.ui.pokedex.model

import com.johndev.pokedexjc.data.PokemonApplication
import com.johndev.pokedexjc.model.PokemonEntity
import com.johndev.testingretrofit.PokemonDao

class RoomPokemonDatabase {

    private val dao: PokemonDao by lazy { PokemonApplication.database.pokemonDao() }

    suspend fun addPokemon(pokemonEntity: PokemonEntity) = dao.addPokemon(pokemonEntity)

    suspend fun getAllPokemon() = dao.getAllPokemon()

}