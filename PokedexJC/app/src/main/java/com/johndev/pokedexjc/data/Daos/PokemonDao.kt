package com.johndev.pokedexjc.data.Daos

import androidx.room.*
import com.johndev.pokedexjc.model.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
    fun getAll(): Flow<List<PokemonEntity>>

    @Query("SELECT DISTINCT * FROM PokemonEntity WHERE id = :id LIMIT 1")
    fun findById(id: Int): Flow<PokemonEntity>

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

    @Update
    suspend fun update(pokemonEntity: PokemonEntity)

}