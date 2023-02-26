package com.johndev.pokedexjc.ui.pokedex.model

import com.johndev.pokedexjc.data.Daos.PokemonDao
import com.johndev.pokedexjc.data.PokemonApplication
import com.johndev.pokedexjc.model.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

class PokemonRepository {

    private val dao: PokemonDao by lazy { PokemonApplication.database.pokemonDao() }

    fun getAll(): Flow<List<PokemonEntity>> = dao.getAll()

    suspend fun insert(pokemonEntity: PokemonEntity) = dao.insert(pokemonEntity)

    suspend fun update(pokemonEntity: PokemonEntity) = dao.update(pokemonEntity)

    fun findById(id: Int) = dao.findById(id)

}