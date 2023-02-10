package com.johndev.pokedexjc.ui.moves.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.pokedexjc.model.dataMoves.MoveEntity
import com.johndev.pokedexjc.model.dataMoves.MoveRetrofit
import com.johndev.pokedexjc.ui.moves.model.MoveRepository
import com.johndev.testingretrofit.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovesViewModel : ViewModel() {

    private val repository = MoveRepository()

    private val _moveResult = MutableLiveData<MoveRetrofit>()

    private val _moveRoom = MutableLiveData<List<MoveEntity>>()
    val moveRoom: LiveData<List<MoveEntity>> = _moveRoom

    private val _saveID = MutableLiveData<Long>()
    val saveID : LiveData<Long> = _saveID

    fun getMoveRetrofit(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Client.service.getMoves(id).enqueue(object : Callback<MoveRetrofit> {
                override fun onResponse(call: Call<MoveRetrofit>, response: Response<MoveRetrofit>) {
                    // Procesar respuesta exitosa
                    response.body()?.let {
                        _moveResult.value = it
                        viewModelScope.launch(Dispatchers.IO) {
                            val moveRoom = MoveEntity(
                                id = it.id,
                                accuracy = it.accuracy,
                                name = it.name.replaceFirstChar(Char::titlecase),
                                power = it.power,
                                category = it.damage_class.name,
                                type = it.type.name
                            )
                            addMoveRoom(moveRoom)
                        }
                    }
                }
                override fun onFailure(call: Call<MoveRetrofit>, t: Throwable) {
                    // Procesar error en la petición
                }
            })
        }
    }

    fun addMoveRoom(moveEntity: MoveEntity) {
        viewModelScope.launch {
            try {
                val resultSave = repository.addMove(moveEntity)
                Log.i("ID_MOVE", "addMoveRoom: $resultSave")
                _saveID.value = resultSave
            } catch (e: Exception) {
                //snackbarMsg.value = R.string.save_error_note
            }
        }
    }

    fun getAllMovesRoom() {
        viewModelScope.launch {
            try {
                val moveSelected = repository.getAllMoves()
                _moveRoom.value = moveSelected
            } catch (e: Exception) {
                //snackbarMsg.value = R.string.save_error_note
            }
        }
    }

}