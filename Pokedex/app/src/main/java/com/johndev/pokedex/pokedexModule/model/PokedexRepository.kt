package com.johndev.pokedex.pokedexModule.model

import com.johndev.pokedex.common.dataAccess.local.RetrofitService.service
import com.johndev.pokedex.common.entities.PokemonEntity
import com.johndev.pokedex.common.entities.PokemonPage

class PokedexRepository {

    suspend fun getPokemonForPage(limit: Int, offset: Int): PokemonPage? {
        return service.getPokemonForPage(limit = limit, offset = offset).body()
    }

    suspend fun getPokemonByName(name: String): PokemonEntity? {
        return service.getPokemonByName(name).body()
    }

}