package com.johndev.pokedexjc.ui.pokedexDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.pokedexjc.model.PokemonEntity
import com.johndev.pokedexjc.model.dataDetails.PokemonComplete
import com.johndev.testingretrofit.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {

    private val _pokemonsDetails = MutableLiveData<PokemonComplete>()
    val pokemonDetails : LiveData<PokemonComplete> = _pokemonsDetails

    fun getPokemon(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Client.service.getPokemonComplete(id).enqueue(object : Callback<PokemonComplete> {
                override fun onResponse(call: Call<PokemonComplete>, response: Response<PokemonComplete>) {
                    // Procesar respuesta exitosa
                    response.body()?.let {
                        _pokemonsDetails.value = it
                        viewModelScope.launch(Dispatchers.IO) {

                        }
                    }
                }
                override fun onFailure(call: Call<PokemonComplete>, t: Throwable) {
                    // Procesar error en la petición
                }
            })
        }
    }

}