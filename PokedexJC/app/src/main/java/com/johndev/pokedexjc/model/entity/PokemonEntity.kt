package com.johndev.pokedexjc.model.entity

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
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
)

fun pokemonColor(type: String): Color {
    return mapTypeToColor(type.replaceFirstChar(Char::titlecase))
}