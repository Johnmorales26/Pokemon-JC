package com.johndev.testingretrofit

import com.johndev.pokedexjc.model.PokemonEntity
import com.johndev.pokedexjc.model.Test.PokemonMoreDetails
import com.johndev.pokedexjc.model.dataMoves.MoveRetrofit
import com.johndev.pokedexjc.model.dataPokemon.PokemonComplete
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{id}/")
    fun getPokemonWithDetails(@Path("id") id: Int): Call<PokemonEntity>

    @GET("pokemon/{id}/")
    fun getPokemonComplete(@Path("id") id: Int): Call<PokemonComplete>

    @GET("pokemon-form/{id}/")
    fun getTypePokemon(@Path("id") id: Int): Call<PokemonMoreDetails>

    @GET("move/{id}/")
    fun getMoves(@Path("id") id: Int): Call<MoveRetrofit>

}