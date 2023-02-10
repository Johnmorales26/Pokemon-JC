package com.johndev.pokedexjc.ui.moves.model

import com.johndev.pokedexjc.data.PokemonApplication
import com.johndev.pokedexjc.model.dataMoves.MoveEntity
import com.johndev.testingretrofit.PokemonDao

class RoomMovesDatabase {

    private val dao: PokemonDao by lazy { PokemonApplication.database.pokemonDao() }

    suspend fun addMove(moveEntity: MoveEntity) = dao.addMove(moveEntity)

    suspend fun getAllMoves() = dao.getAllMoves()

}