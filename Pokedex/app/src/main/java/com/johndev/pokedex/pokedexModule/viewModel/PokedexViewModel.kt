package com.johndev.pokedex.pokedexModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.pokedex.common.entities.PokemonEntity
import com.johndev.pokedex.pokedexModule.model.PokedexRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokedexViewModel : ViewModel() {

    private val repository = PokedexRepository()

    private val _pokemonList = MutableLiveData<List<PokemonEntity?>?>(mutableListOf())
    val pokemonList: LiveData<List<PokemonEntity?>?> = _pokemonList

    private val _enableScroll = MutableLiveData(false)
    val enableScroll: LiveData<Boolean> = _enableScroll

    private val _loadView = MutableLiveData(false)
    val loadView: LiveData<Boolean> = _loadView

    private val _limit = MutableLiveData(20)
    private val _offset = MutableLiveData(0)

    fun getPokemonPerPage() {
        _loadView.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val downloadedPokemonList = repository.getPokemonForPage(
                limit = _limit.value!!,
                offset = _offset.value!!
            )?.results?.map { getPokemonByName(it.name) }
            val pokemonList = pokemonList.value?.toMutableList() ?: mutableListOf()
            downloadedPokemonList?.let { pokemonList.addAll(it) }
            val sortedList = pokemonList.sortedBy { it?.id }
            val distinctList = sortedList.distinctBy { it?.id }
            _pokemonList.postValue(distinctList)
            _enableScroll.postValue(true)
            _loadView.postValue(false)
        }
    }

    private suspend fun getPokemonByName(name: String) = withContext(Dispatchers.IO) {
        repository.getPokemonByName(name)
    }

    fun loadMorePokemon() {
        _limit.value = _limit.value?.plus(20)
        _offset.value = _offset.value?.plus(20)
        getPokemonPerPage()
    }

    fun disableScroll() {
        _enableScroll.value = false
    }

}