package com.johndev.pokedexjc.ui.pokedexDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.pokedexjc.model.entity.PokemonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {

    /*private val _pokemonsDetails = MutableLiveData<PokemonEntity>()
    val pokemonDetails : LiveData<PokemonEntity> = _pokemonsDetails

    fun getPokemon(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Client.service.getPokemonEntity(id).enqueue(object : Callback<PokemonEntity> {
                override fun onResponse(call: Call<PokemonEntity>, response: Response<PokemonEntity>) {
                    // Procesar respuesta exitosa
                    response.body()?.let {
                        _pokemonsDetails.value = it
                        viewModelScope.launch(Dispatchers.IO) {

                        }
                    }
                }
                override fun onFailure(call: Call<PokemonEntity>, t: Throwable) {
                    // Procesar error en la petición
                }
            })
        }
    }*/

}