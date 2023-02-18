package com.johndev.pokedexjc.ui.items.model

import com.johndev.pokedexjc.data.Daos.ItemDao
import com.johndev.pokedexjc.data.PokemonApplication
import com.johndev.pokedexjc.model.entity.ItemEntity


class ItemDatabase {

    private val dao: ItemDao by lazy { PokemonApplication.database.itemDao() }

    suspend fun insert(itemEntity: ItemEntity) = dao.insert(itemEntity)

    suspend fun getAll() = dao.getAll()

}