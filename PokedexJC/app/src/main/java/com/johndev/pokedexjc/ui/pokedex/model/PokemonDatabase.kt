package com.johndev.pokedexjc.ui.pokedex.model

import com.johndev.pokedexjc.data.PokemonApplication
import com.johndev.pokedexjc.data.Daos.PokemonDao
import com.johndev.pokedexjc.model.entity.PokemonEntity

class PokemonDatabase {

    private val dao: PokemonDao by lazy { PokemonApplication.database.pokemonDao() }

    suspend fun insert(pokemonEntity: PokemonEntity) = dao.insert(pokemonEntity)

    suspend fun getAll() = dao.getAll()

    suspend fun findById(id: Int) = dao.findById(id)

}