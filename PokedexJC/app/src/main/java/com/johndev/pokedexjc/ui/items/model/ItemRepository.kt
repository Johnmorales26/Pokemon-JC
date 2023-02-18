package com.johndev.pokedexjc.ui.items.model

import com.johndev.pokedexjc.model.entity.ItemEntity


class ItemRepository {

    private val roomDatabase = ItemDatabase()

    suspend fun insert(itemEntity: ItemEntity) = roomDatabase.insert(itemEntity)

    suspend fun getAll() = roomDatabase.getAll()

}