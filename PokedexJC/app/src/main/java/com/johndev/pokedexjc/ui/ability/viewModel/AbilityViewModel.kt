package com.johndev.pokedexjc.ui.ability.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.pokedexjc.model.dataAbilities.AbilityRetrofit
import com.johndev.pokedexjc.model.entity.AbilityEntity
import com.johndev.pokedexjc.retrofit.Client
import com.johndev.pokedexjc.ui.ability.model.AbilityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AbilityViewModel : ViewModel() {

    val repository = AbilityRepository()

    val abilityList: Flow<List<AbilityEntity>> = repository.getAll()

    fun getAbility(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Client.service.getAbility(id).enqueue(object : Callback<AbilityRetrofit> {
                override fun onResponse(
                    call: Call<AbilityRetrofit>,
                    response: Response<AbilityRetrofit>
                ) {
                    // Procesar respuesta exitosa
                    response.body()?.let {
                        try {
                            viewModelScope.launch {
                                val abilityEntity = AbilityEntity(
                                    id = it.id,
                                    name = it.name,
                                    effect = when(it.effect_entries.size) {
                                        2 -> it.effect_entries[1].effect
                                        1 -> it.effect_entries[0].effect
                                        else -> ""
                                    },
                                    short_effect = when(it.effect_entries.size) {
                                        2 -> it.effect_entries[1].short_effect
                                        1 -> it.effect_entries[0].short_effect
                                        else -> ""
                                    },
                                    pokemon = it.pokemon.toString().replace("[", "")
                                        .replace("]", "")
                                )
                                repository.insert(abilityEntity)
                            }
                        } catch (e: Exception) {
                            Log.e("ERROR_INSERT_ABILITY", "onResponse: $e")
                        }
                    }
                }

                override fun onFailure(call: Call<AbilityRetrofit>, t: Throwable) {
                    // Procesar error en la petición
                    Log.e("ERROR_PROCES_ITEM", "onFailure: $t")
                }
            })
        }
    }

}