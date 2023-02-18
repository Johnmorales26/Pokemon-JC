package com.johndev.pokedexjc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.johndev.pokedexjc.data.Daos.AbilityDao
import com.johndev.pokedexjc.data.Daos.ItemDao
import com.johndev.pokedexjc.data.Daos.MoveDao
import com.johndev.pokedexjc.data.Daos.PokemonDao
import com.johndev.pokedexjc.model.entity.AbilityEntity
import com.johndev.pokedexjc.model.entity.ItemEntity
import com.johndev.pokedexjc.model.entity.MoveEntity
import com.johndev.pokedexjc.model.entity.PokemonEntity

@Database(entities = [PokemonEntity::class, MoveEntity::class, ItemEntity::class, AbilityEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun itemDao(): ItemDao
    abstract fun moveDao(): MoveDao
    abstract fun abilityDao(): AbilityDao

}