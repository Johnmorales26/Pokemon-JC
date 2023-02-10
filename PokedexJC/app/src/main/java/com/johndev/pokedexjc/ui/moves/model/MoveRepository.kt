package com.johndev.pokedexjc.ui.moves.model

import com.johndev.pokedexjc.model.dataMoves.MoveEntity

class MoveRepository {

    private val roomDatabase = RoomMovesDatabase()

    suspend fun addMove(moveEntity: MoveEntity) = roomDatabase.addMove(moveEntity)

    suspend fun getAllMoves() = roomDatabase.getAllMoves()

}