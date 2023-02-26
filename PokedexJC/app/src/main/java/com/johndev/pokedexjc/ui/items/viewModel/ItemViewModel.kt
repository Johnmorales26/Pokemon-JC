package com.johndev.pokedexjc.ui.items.viewModel

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.pokedexjc.model.dataItem.ItemRetrofit
import com.johndev.pokedexjc.model.entity.ItemEntity
import com.johndev.pokedexjc.model.entity.PokemonEntity
import com.johndev.pokedexjc.retrofit.Client
import com.johndev.pokedexjc.ui.items.model.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemViewModel : ViewModel() {

    val repository = ItemRepository()

    val itemsList: Flow<List<ItemEntity>> = repository.getAll()

    fun getItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Client.service.getItem(id).enqueue(object : Callback<ItemRetrofit> {
                override fun onResponse(call: Call<ItemRetrofit>, response: Response<ItemRetrofit>) {
                    // Procesar respuesta exitosa
                    response.body()?.let {
                        try {
                        viewModelScope.launch {
                            val itemEntity = ItemEntity(
                                id = it.id,
                                name = it.name,
                                category = it.category.name,
                                cost = it.cost,
                                fling_power = it.fling_power,
                                fling_effect = it.fling_effect,
                                description = if (it.effect_entries.isNotEmpty()) it.effect_entries[0].effect else "",
                                effect = if (it.effect_entries.isNotEmpty()) it.effect_entries[0].effect else "",
                                short_effect = if (it.effect_entries.isNotEmpty()) it.effect_entries[0].short_effect else "",
                                sprites = it.sprites.default
                            )
                            repository.insert(itemEntity)
                        }
                        }catch (e: Exception) {
                            Log.e("ERROR_INSERT_ITEM", "onResponse: $e", )
                        }
                    }
                }
                override fun onFailure(call: Call<ItemRetrofit>, t: Throwable) {
                    // Procesar error en la petición
                    Log.e("ERROR_PROCES_ITEM", "onFailure: $t", )
                }
            })
        }
    }

}