package com.johndev.pokedexjc.model.entity

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.johndev.pokedexjc.data.PokemonUtils.getImagePokemon
import com.johndev.pokedexjc.model.dataPokemon.PokemonRetrofit
import com.johndev.pokedexjc.model.mapTypeToColor

@Entity(tableName = "PokemonEntity")//, indices = [Index(value = ["id"], unique = true)])
data class PokemonEntity(
    @PrimaryKey val id: Int = 0,
    val name: String? = null,
    val typeOfPokemon: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val moves: String? = null,
    var types: String? = null,
    var imageUrl: String? = null,
    var imagesCarousel: String? = null,
    val order: Int? = null,
    val base_experience: Int? = null,
    val hp: Int? = null,
    val attack: Int? = null,
    val defense: Int? = null,
    val special_attack: Int? = null,
    val special_defense: Int? = null,
    val speed: Int? = null,
    var isFavorite: Boolean = false,
    var isCaptured: Boolean = false,
)

fun pokemonColor(type: String): Color {
    return mapTypeToColor(type.replaceFirstChar(Char::titlecase))
}

fun mapPokemonRetrofitToEntity(pokemonRetrofit: PokemonRetrofit): PokemonEntity {
    return try {
        PokemonEntity(
            id = pokemonRetrofit.id,
            name = pokemonRetrofit.name,
            typeOfPokemon = pokemonRetrofit.types[0].type.name,
            height = pokemonRetrofit.height,
            weight = pokemonRetrofit.weight,
            moves = pokemonRetrofit.moves.joinToString(separator = ",") { it.move.name },
            types = pokemonRetrofit.types.joinToString(separator = ",") { it.type.name },
            imageUrl = getImagePokemon(pokemonRetrofit.id),
            imagesCarousel = "",
            order = pokemonRetrofit.order,
            base_experience = pokemonRetrofit.base_experience,
            hp = pokemonRetrofit.stats[0].base_stat,
            attack = pokemonRetrofit.stats[1].base_stat,
            defense = pokemonRetrofit.stats[2].base_stat,
            special_attack = pokemonRetrofit.stats[3].base_stat,
            special_defense = pokemonRetrofit.stats[4].base_stat,
            speed = pokemonRetrofit.stats[5].base_stat
        )
    } catch (e: Exception) {
        Log.e("Error_Insert_Pokemon", "onResponse: $e")
        PokemonEntity() // retorna una instancia vacía si ocurre un error
    }
}