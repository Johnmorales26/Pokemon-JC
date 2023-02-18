package com.johndev.pokedexjc.ui.moves.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.pokedexjc.data.PokemonUtils
import com.johndev.pokedexjc.model.dataMoves.MoveRetrofit
import com.johndev.pokedexjc.model.entity.MoveEntity
import com.johndev.pokedexjc.retrofit.Client
import com.johndev.pokedexjc.ui.moves.model.MovesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovesViewModel : ViewModel() {

    private var repository = MovesRepository()

    private val _movesRoom = MutableLiveData<List<MoveEntity>>()
    val movesRoom: LiveData<List<MoveEntity>> = _movesRoom

    fun getMoveByRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAll()
            withContext(Dispatchers.Main) {
                _movesRoom.value = result
            }
        }
    }

    fun getMoveRetrofit(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Client.service.getMoves(id).enqueue(object : Callback<MoveRetrofit> {
                override fun onResponse(call: Call<MoveRetrofit>, response: Response<MoveRetrofit>) {
                    // Procesar respuesta exitosa
                    response.body()?.let {
                        viewModelScope.launch(Dispatchers.IO) {
                            var listPokemon: MutableList<String> = mutableListOf()
                            it.learned_by_pokemon.forEach {
                                listPokemon.add(it.name)
                            }
                            try {
                                val moveRoom = MoveEntity(
                                    id = it.id,
                                    name = PokemonUtils.titleCase(it.name),
                                    type_name = PokemonUtils.titleCase(it.type.name),
                                    category = PokemonUtils.titleCase(it.damage_class.name),
                                    contest_type = PokemonUtils.titleCase(it.type.name),
                                    damage_class = PokemonUtils.titleCase(it.damage_class.name),
                                    power = it.power,
                                    accuracy = it.accuracy,
                                    pp = it.pp,
                                    effect = PokemonUtils.titleCase(it.effect_entries[0].effect),
                                    learned_by_pokemon = listPokemon.toString()
                                )
                                repository.insert(moveRoom)
                            } catch (e: Exception) {

                            }
                        }
                    }
                }
                override fun onFailure(call: Call<MoveRetrofit>, t: Throwable) {
                    // Procesar error en la petición
                }
            })
        }
    }

}