package com.johndev.pokedex.common.dataAccess.local

import com.johndev.pokedex.common.utils.Constants.URL_POKEMON
import com.johndev.pokedex.common.entities.PokemonEntity
import com.johndev.pokedex.common.entities.PokemonPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonService {

    @GET(URL_POKEMON)
    suspend fun getPokemonForPage(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonPage>

    @GET("$URL_POKEMON{name}/")
    suspend fun getPokemonByName(@Path("name") name: String): Response<PokemonEntity>

}