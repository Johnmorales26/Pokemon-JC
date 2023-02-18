package com.johndev.pokedexjc.ui.moves.model

import com.johndev.pokedexjc.model.entity.MoveEntity

class MovesRepository {

    private val roomDatabase = MovesDatabase()

    suspend fun insert(moveEntity: MoveEntity) = roomDatabase.insert(moveEntity)

    suspend fun getAll() = roomDatabase.getAll()

    suspend fun findById(id: Int) = roomDatabase.findById(id)

}