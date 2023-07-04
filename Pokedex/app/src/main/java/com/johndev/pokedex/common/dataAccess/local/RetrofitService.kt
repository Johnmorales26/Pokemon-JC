package com.johndev.pokedex.common.dataAccess.local

import com.johndev.pokedex.common.utils.Constants.URL_BASE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: PokemonService = retrofit.create(PokemonService::class.java)

}