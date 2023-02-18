package com.johndev.pokedexjc.model.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "AbilityEntity", indices = [Index(value = ["id"], unique = true)])
data class AbilityEntity(
    @PrimaryKey var id: Int? = null,
    var name: String? = null,
    var effect: String? = null,
    var short_effect: String? = null,
    var pokemon: String? = null
)
