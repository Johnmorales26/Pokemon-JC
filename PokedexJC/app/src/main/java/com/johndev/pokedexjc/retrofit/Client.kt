package com.johndev.testingretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Client {

    var retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: PokemonService = retrofit.create(PokemonService::class.java)

}