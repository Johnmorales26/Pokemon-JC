package com.johndev.pokedexjc.ui.items.model

import com.johndev.pokedexjc.data.Daos.ItemDao
import com.johndev.pokedexjc.data.PokemonApplication
import com.johndev.pokedexjc.model.entity.ItemEntity
import kotlinx.coroutines.flow.Flow


class ItemRepository {

    private val roomDatabase = ItemDatabase()
    private val dao: ItemDao by lazy { PokemonApplication.database.itemDao() }

    fun getAll(): Flow<List<ItemEntity>> = dao.getAll()

    suspend fun insert(itemEntity: ItemEntity) = roomDatabase.insert(itemEntity)

}