package com.johndev.pokedexjc.retrofit

import com.johndev.pokedexjc.model.dataAbilities.AbilityRetrofit
import com.johndev.pokedexjc.model.dataItem.ItemRetrofit
import com.johndev.pokedexjc.model.dataMoves.MoveRetrofit
import com.johndev.pokedexjc.model.dataPokemon.ListPokemon
import com.johndev.pokedexjc.model.dataPokemon.PokemonRetrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    //@GET("pokemon/?limit=10271&offset=0")
    @GET("pokemon/?limit=300&offset=0")
    fun getAllPokemon(): Call<ListPokemon>

    @GET("pokemon/{id}/")
    fun getPokemonComplete(@Path("id") id: Int): Call<PokemonRetrofit>

    @GET("move/{id}/")
    fun getMoves(@Path("id") id: Int): Call<MoveRetrofit>

    @GET("item/{id}/")
    fun getItem(@Path("id") id: Int): Call<ItemRetrofit>

    @GET("ability/{id}/")
    fun getAbility(@Path("id") id: Int): Call<AbilityRetrofit>

}