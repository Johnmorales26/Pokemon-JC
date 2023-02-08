package com.johndev.pokedexjc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.johndev.pokedexjc.model.PokemonEntity
import com.johndev.testingretrofit.PokemonDao

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

}