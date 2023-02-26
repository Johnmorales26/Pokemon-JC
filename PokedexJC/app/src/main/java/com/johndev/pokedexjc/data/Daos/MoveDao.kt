package com.johndev.pokedexjc.data.Daos

import androidx.room.*
import com.johndev.pokedexjc.model.entity.MoveEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoveDao {

    @Query("SELECT * FROM MoveEntity")
    fun getAll(): Flow<List<MoveEntity>>

    @Query("SELECT * FROM MoveEntity WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): MoveEntity?

    @Query("SELECT * FROM MoveEntity WHERE name = :name LIMIT 1")
    suspend fun findByName(name: String): MoveEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(moveEntity: MoveEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg moveEntity: MoveEntity)

    @Delete
    suspend fun delete(moveEntity: MoveEntity)

    @Query("DELETE FROM MoveEntity")
    suspend fun deleteAll()

}