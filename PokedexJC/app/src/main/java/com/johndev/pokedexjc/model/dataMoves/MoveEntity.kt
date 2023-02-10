package com.johndev.pokedexjc.model.dataMoves

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.johndev.pokedexjc.R
import des.c5inco.pokedexer.model.Move

@Entity(tableName = "MoveEntity", indices = [Index(value = ["id"], unique = true)])
data class MoveEntity(
    @PrimaryKey val id: Int,
    val accuracy: Int,
    //@Ignore val contest_type: ContestType,
    val name: String,
    val power: Int,
    //@Ignore val type: Type,
    val category: String,
    val type: String
)

fun MoveEntity.category(): MoveCategory {
    return when(category) {
        "physical" -> MoveCategory.Physical
        "special" -> MoveCategory.Special
        else -> MoveCategory.Status
    }
}

fun MoveEntity.type(): des.c5inco.pokedexer.model.Type {
    return when(type) {
        else -> des.c5inco.pokedexer.model.Type.Normal
    }
}

fun MoveEntity.categoryIcon(): Int {
    return when(category.lowercase()) {
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