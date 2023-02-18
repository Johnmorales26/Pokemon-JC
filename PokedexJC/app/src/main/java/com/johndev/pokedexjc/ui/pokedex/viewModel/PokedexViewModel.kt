package com.johndev.pokedexjc.ui.pokedex.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.pokedexjc.data.PokemonUtils.getImagePokemon
import com.johndev.pokedexjc.model.dataPokemon.PokemonRetrofit
import com.johndev.pokedexjc.model.entity.PokemonEntity
import com.johndev.pokedexjc.retrofit.Client.service
import com.johndev.pokedexjc.ui.pokedex.model.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokedexViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val _resultAllPokemon = MutableLiveData<List<PokemonEntity>>()
    val allPokemon : LiveData<List<PokemonEntity>> = _resultAllPokemon

    private val _pokemonRoom = MutableLiveData<PokemonEntity?>()
    val pokemonRoom : LiveData<PokemonEntity?> = _pokemonRoom

    fun findById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.findById(id)
            withContext(Dispatchers.Main) {
                _pokemonRoom.value = result
            }
        }
    }

    fun getPokemonsByRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAll()
            withContext(Dispatchers.Main) {
                _resultAllPokemon.value = result
            }
        }
    }

    fun getPokemon(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            service.getPokemonComplete(id).enqueue(object : Callback<PokemonRetrofit> {
                override fun onResponse(call: Call<PokemonRetrofit>, response: Response<PokemonRetrofit>) {
                    // Procesar respuesta exitosa
                    response.body()?.let {
                        try {
                            var typePokemon = ""
                            var movesPokemon = ""
                            it.types.forEach { type ->
                                typePokemon += "${type.type.name},"
                            }
                            it.moves.forEach { move ->
                                movesPokemon += "${move.move.name},"
                            }
                            val pokemonRoom = PokemonEntity(
                                id = it.id,
                                name = it.name,
                                typeOfPokemon = it.types[0].type.name,
                                height = it.height,
                                weight = it.weight,
                                moves = movesPokemon,
                                types = typePokemon,
                                imageUrl = getImagePokemon(it.id),
                                imagesCarousel = "",
                                order = it.order,
                                base_experience = it.base_experience,
                                hp = it.stats[0].base_stat,
                                attack = it.stats[1].base_stat,
                                defense = it.stats[2].base_stat,
                                special_attack = it.stats[3].base_stat,
                                special_defense = it.stats[4].base_stat,
                                speed = it.stats[5].base_stat
                            )
                            viewModelScope.launch(Dispatchers.IO) {
                                repository.insert(pokemonRoom)
                            }
                        } catch (e: Exception) {
                            Log.e("Error_Insert_Pokemon", "onResponse: $e", )
                        }
                    }
                }
                override fun onFailure(call: Call<PokemonRetrofit>, t: Throwable) {
                    // Procesar error en la petición
                }
            })
        }
    }

    /*fun getAllPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            service.getAllPokemon().enqueue(object : Callback<ListPokemon> {
                override fun onResponse(call: Call<ListPokemon>, response: Response<ListPokemon>) {
                    // Procesar respuesta exitosa
                    response.body()?.let { it ->
                        it.results.forEach {
                            viewModelScope.launch(Dispatchers.IO) {
                                var id = it.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                                id = id.replace("/", "")
                                Log.i("ID_POKEMON", "onResponse: ${id.toInt()}")
                                getPokemon(id.toInt())
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<ListPokemon>, t: Throwable) {
                    // Procesar error en la petición
                }
            })
        }
    }*/

}