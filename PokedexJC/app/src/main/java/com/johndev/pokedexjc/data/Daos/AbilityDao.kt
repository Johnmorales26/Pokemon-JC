package com.johndev.pokedexjc.data.Daos

import androidx.room.*
import com.johndev.pokedexjc.model.entity.AbilityEntity
import com.johndev.pokedexjc.model.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AbilityDao {

    @Query("SELECT * FROM AbilityEntity")
    fun getAll(): Flow<List<AbilityEntity>>

    @Query("SELECT * FROM AbilityEntity WHERE id = :id LIMIT 1  ")
    suspend fun findById(id: Int): AbilityEntity?

    @Query("SELECT * FROM AbilityEntity WHERE name LIKE :name LIMIT 1  ")
    suspend fun findByName(name: String): AbilityEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(abilityEntity: AbilityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg abilityEntity: AbilityEntity)

    @Delete
    suspend fun delete(abilityEntity: AbilityEntity)

    @Query("DELETE FROM AbilityEntity")
    suspend fun deleteAll()

}