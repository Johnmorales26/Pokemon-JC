package com.johndev.pokedexjc.model.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.model.Type

@Entity(tableName = "MoveEntity")//, indices = [Index(value = ["id"], unique = true)])
data class MoveEntity(
    @PrimaryKey var id: Int = 0,
    var name: String? = null,
    var type_name: String? = null,
    val category: String? = null,
    var contest_type: String? = null,
    var damage_class: String? = null,
    var power: Int? = null,
    var accuracy: Int? = null,
    var pp: Int? = null,
    var effect: String? = null,
    var learned_by_pokemon: String? = null
)

fun MoveEntity.category(): MoveCategory {
    return when(category) {
        "physical" -> MoveCategory.Physical
        "special" -> MoveCategory.Special
        else -> MoveCategory.Status
    }
}

fun MoveEntity.type(): Type {
    return when(type_name) {
        else -> Type.Normal
    }
}

fun MoveEntity.categoryIcon(): Int {
    return when(category?.lowercase()) {
        "physical" -> R.drawable.ic_move_physical
        "special" -> R.drawable.ic_move_special
        else -> R.drawable.ic_move_status
    }
}

enum class MoveCategory {
    Physical,
    Special,
    Status
}
