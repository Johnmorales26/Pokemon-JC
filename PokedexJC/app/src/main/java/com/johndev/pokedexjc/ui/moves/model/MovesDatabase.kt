package com.johndev.pokedexjc.ui.moves.model

import com.johndev.pokedexjc.data.Daos.MoveDao
import com.johndev.pokedexjc.data.PokemonApplication
import com.johndev.pokedexjc.data.Daos.PokemonDao
import com.johndev.pokedexjc.model.entity.MoveEntity

class MovesDatabase {

    private val dao: MoveDao by lazy { PokemonApplication.database.moveDao() }

    suspend fun insert(moveEntity: MoveEntity) = dao.insert(moveEntity)

    suspend fun getAll() = dao.getAll()

    suspend fun findById(id: Int) = dao.findById(id)

}