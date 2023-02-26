package com.johndev.pokedexjc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.johndev.pokedexjc.data.Daos.*
import com.johndev.pokedexjc.model.entity.*

@Database(
    entities = [
        PokemonEntity::class,
        MoveEntity::class,
        ItemEntity::class,
        AbilityEntity::class],
    version = 1
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun itemDao(): ItemDao
    abstract fun moveDao(): MoveDao
    abstract fun abilityDao(): AbilityDao

}