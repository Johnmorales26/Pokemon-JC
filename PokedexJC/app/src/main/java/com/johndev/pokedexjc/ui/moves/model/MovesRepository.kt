package com.johndev.pokedexjc.ui.moves.model

import com.johndev.pokedexjc.data.Daos.MoveDao
import com.johndev.pokedexjc.data.PokemonApplication
import com.johndev.pokedexjc.model.entity.MoveEntity
import kotlinx.coroutines.flow.Flow

class MovesRepository {

    private val dao: MoveDao by lazy { PokemonApplication.database.moveDao() }

    suspend fun insert(moveEntity: MoveEntity) = dao.insert(moveEntity)

    fun getAll(): Flow<List<MoveEntity>> = dao.getAll()

    suspend fun findById(id: Int) = dao.findById(id)

}