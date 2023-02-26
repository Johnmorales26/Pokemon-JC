package com.johndev.pokedexjc.ui.pokedex.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.pokedexjc.model.dataPokemon.PokemonRetrofit
import com.johndev.pokedexjc.model.entity.PokemonEntity
import com.johndev.pokedexjc.model.entity.mapPokemonRetrofitToEntity
import com.johndev.pokedexjc.retrofit.Client.service
import com.johndev.pokedexjc.ui.pokedex.model.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PokedexViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private var pokemonList: Flow<List<PokemonEntity>> = repository.getAll()

    fun getPokemonById(id: Int): Flow<PokemonEntity> {
        return repository.findById(id)
    }

    fun sortListByCriteria(list: List<PokemonEntity>, sortBy: String, order: String): List<PokemonEntity> {
        val sortedList = when(sortBy) {
            "ID (# / Number)" -> list.sortedBy { it.id }
            "Alphabetical (A - Z)" -> list.sortedBy { it.name }
            "PS" -> list.sortedBy { it.hp }
            "Attack" -> list.sortedBy { it.attack }
            "Defense" -> list.sortedBy { it.defense }
            "Special Attack" -> list.sortedBy { it.special_attack }
            "Special Defense" -> list.sortedBy { it.special_defense }
            else -> list.sortedBy { it.speed }
        }

        return when (order) {
            "Upward" -> sortedList
            else -> sortedList.reversed()
        }
    }

    fun getPokemonListOrdenate(sortBy: String = "", order: String = "Upward"): Flow<List<PokemonEntity>> {
        return pokemonList.map { sortListByCriteria(it, sortBy, order) }
    }

    val pokemonFavoriteList: Flow<List<PokemonEntity>> = pokemonList.map { pokemonList ->
        pokemonList.filter { it.isFavorite }
    }

    val pokemonCapturedList: Flow<List<PokemonEntity>> = pokemonList.map { pokemonList ->
        pokemonList.filter { it.isCaptured }
    }

    val pokemonListSize: Flow<Int> = pokemonList.map { pokemonList ->
        pokemonList.size
    }

    fun getPokemon(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonFromService(id)
        }
    }

    private fun getPokemonFromService(id: Int) {
        service.getPokemonComplete(id).enqueue(object : Callback<PokemonRetrofit> {
            override fun onResponse(
                call: Call<PokemonRetrofit>,
                response: Response<PokemonRetrofit>
            ) {
                // Procesar respuesta exitosa
                response.body()?.let { pokemonRetrofit ->
                    val pokemonRoom = mapPokemonRetrofitToEntity(pokemonRetrofit)
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.insert(pokemonRoom)
                    }
                }
            }

            override fun onFailure(call: Call<PokemonRetrofit>, t: Throwable) {
                if (t is IOException) {
                    // Es posible que la conexión a internet haya fallado
                    Log.e("PokemonViewModel", "Error de conexión a internet: ${t.message}")
                    // _errorMessage.postValue("No se pudo conectar con el servidor, por favor revisa tu conexión a internet.")
                } else {
                    // Si la excepción no es una IOException, se trata de un error diferente
                    Log.e("PokemonViewModel", "Error en la petición HTTP: ${t.message}")
                    // _errorMessage.postValue("Ocurrió un error en la petición, por favor intenta de nuevo más tarde.")
                }
            }
        })
    }

    fun updateFavorite(pokemonEntity: PokemonEntity) {
        viewModelScope.launch {
            pokemonEntity.isFavorite = !pokemonEntity.isFavorite
            repository.update(pokemonEntity)
        }
    }

    fun updateCaptured(pokemonEntity: PokemonEntity) {
        viewModelScope.launch {
            pokemonEntity.isCaptured = !pokemonEntity.isCaptured
            repository.update(pokemonEntity)
        }
    }

}