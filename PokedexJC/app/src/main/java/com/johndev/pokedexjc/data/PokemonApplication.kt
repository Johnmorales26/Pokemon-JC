package com.johndev.pokedexjc.data

import android.app.Application
import androidx.room.Room

class PokemonApplication : Application() {

    companion object {
        lateinit var database: PokemonDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(this, PokemonDatabase::class.java, "PokemonDatabase")
            .build()
    }

}