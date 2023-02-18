package com.johndev.pokedexjc.model.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ItemEntity")//, indices = [Index(value = ["id"], unique = true)])
data class ItemEntity(
    @PrimaryKey var id: Int = 0,
    var name: String? = null,
    var category: String? = null,
    var cost: Int? = null,
    var fling_power: Int? = null,
    var fling_effect: Int? = null,
    var description: String? = null,
    var effect: String? = null,
    var short_effect: String? = null,
    var sprites: String? = null
)
