package com.johndev.pokedexjc.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "PokemonEntity", indices = [Index(value = ["id"], unique = true)])
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val base_experience: Int? = null,
    val height: Int? = null,
    val is_default: Boolean? = null,
    val location_area_encounters: String? = null,
    val name: String? = null,
    val order: Int? = null,
    val weight: Int? = null,
    var imageUrl: String? = null,
    var type: String? = null
)