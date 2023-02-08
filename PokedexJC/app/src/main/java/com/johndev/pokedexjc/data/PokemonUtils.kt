package com.johndev.pokedexjc.data

object PokemonUtils {

    fun getImagePokemon(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }

    fun formatId(id: Int): String = "#" + "$id".padStart(3, '0')

    fun calculateHeight(height: Int): String {
        return StringBuilder().append(height / 10.0).append("m").toString()
    }

    fun calculateWeight(weight: Int): String {
        return StringBuilder().append(weight / 10.0).append("kg").toString()
    }

}