package com.johndev.pokedexjc.data.Daos

import androidx.room.*
import com.johndev.pokedexjc.model.entity.ItemEntity

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemEntity")
    suspend fun getAll(): List<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemEntity: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg itemEntity: ItemEntity)

    @Query("SELECT * FROM ItemEntity WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id: Long): ItemEntity?

    @Query("SELECT * FROM ItemEntity WHERE name LIKE :name LIMIT 1")
    suspend fun findByName(name: String): ItemEntity?

    @Delete
    suspend fun delete(itemEntity: ItemEntity)

    @Query("DELETE FROM ItemEntity")
    suspend fun deleteAll()

}