package com.johndev.testingretrofit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.johndev.pokedexjc.model.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAllPokemon(): MutableList<PokemonEntity>

    @Insert
    suspend fun addPokemon(pokemonEntity: PokemonEntity): Long

}