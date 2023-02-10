package com.johndev.pokedexjc.ui.pokedex.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.pokedexjc.data.PokemonUtils.getImagePokemon
import com.johndev.pokedexjc.model.PokemonEntity
import com.johndev.pokedexjc.model.Test.PokemonMoreDetails
import com.johndev.testingretrofit.Client.service
import com.johndev.pokedexjc.ui.pokedex.model.PokemonRepository
import des.c5inco.pokedexer.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokedexViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val _pokemons = MutableLiveData<MutableList<Pokemon>>()

    private val resultAllPokemon = MutableLiveData<MutableList<PokemonEntity>>()
    val allPokemon : LiveData<MutableList<PokemonEntity>> = resultAllPokemon

    private val resultMoreDetails = MutableLiveData<PokemonMoreDetails>()
    val moreDetails : LiveData<PokemonMoreDetails> = resultMoreDetails

    private val result = MutableLiveData<Long>()

    private val _pokemonsDetails = MutableLiveData<PokemonEntity>()

    fun getPokemonsList() {
        viewModelScope.launch {
            //_pokemons.value = SamplePokemonData
        }
    }

    fun getAllPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAllPokemon()
            withContext(Dispatchers.Main) {
                resultAllPokemon.value = result
            }
        }
    }

    suspend fun addPokemon(pokemonEntity: PokemonEntity) {
        viewModelScope.launch {
            try {
                val resultSave = repository.addPokemon(pokemonEntity)
                result.value = resultSave
            } catch (e: Exception) {
                //snackbarMsg.value = R.string.save_error_note
            }
        }
    }

    fun getPokemon(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            service.getPokemonWithDetails(id).enqueue(object : Callback<PokemonEntity> {
                override fun onResponse(call: Call<PokemonEntity>, response: Response<PokemonEntity>) {
                    // Procesar respuesta exitosa
                    response.body()?.let {
                        _pokemonsDetails.value = it
                        viewModelScope.launch(Dispatchers.IO) {
                            getMoreDetails(it)
                        }
                    }
                }
                override fun onFailure(call: Call<PokemonEntity>, t: Throwable) {
                    // Procesar error en la petición
                }
            })
        }
    }

    fun getMoreDetails(pokemon: PokemonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            service.getTypePokemon(pokemon.id).enqueue(object : Callback<PokemonMoreDetails> {
                override fun onResponse(call: Call<PokemonMoreDetails>, response: Response<PokemonMoreDetails>) {
                    // Procesar respuesta exitosa
                    response.body()?.let {
                        resultMoreDetails.value = it
                        viewModelScope.launch {
                            pokemon.imageUrl = getImagePokemon(it.id)
                            pokemon.type = it.types[0].type.name.capitalize()
                            addPokemon(pokemon)
                        }
                    }
                }
                override fun onFailure(call: Call<PokemonMoreDetails>, t: Throwable) {
                    // Procesar error en la petición
                }
            })
        }
    }

}