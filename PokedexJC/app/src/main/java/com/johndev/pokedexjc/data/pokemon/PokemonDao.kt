package com.johndev.testingretrofit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.johndev.pokedexjc.model.PokemonEntity
import com.johndev.pokedexjc.model.dataMoves.MoveEntity
import des.c5inco.pokedexer.model.Move

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAllPokemon(): MutableList<PokemonEntity>

    @Insert
    suspend fun addPokemon(pokemonEntity: PokemonEntity): Long

    @Query("SELECT * FROM MoveEntity")
    suspend fun getAllMoves(): List<MoveEntity>

    @Insert
    suspend fun addMove(moveEntity: MoveEntity): Long

    @Query("SELECT * FROM MoveEntity WHERE id = :id")
    suspend fun getMoveById(id: Long): MoveEntity?



}