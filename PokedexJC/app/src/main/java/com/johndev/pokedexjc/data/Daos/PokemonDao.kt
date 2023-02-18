package com.johndev.pokedexjc.data.Daos

import androidx.room.*
import com.johndev.pokedexjc.model.entity.AbilityEntity
import com.johndev.pokedexjc.model.entity.ItemEntity
import com.johndev.pokedexjc.model.entity.MoveEntity
import com.johndev.pokedexjc.model.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): PokemonEntity?

    @Query("SELECT * FROM PokemonEntity WHERE name LIKE :name LIMIT 1")
    suspend fun findByName(name: String): PokemonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonEntity: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg pokemonEntity: PokemonEntity)

    @Delete
    suspend fun delete(pokemonEntity: PokemonEntity)

    @Query("DELETE FROM PokemonEntity")
    suspend fun deleteAll()



}