package com.johndev.pokedexjc.data

import com.johndev.pokedexjc.model.MenuItem
import com.johndev.pokedexjc.ui.theme.*

fun getMenuItem(): List<MenuItem> {
    return listOf(
        MenuItem("Pokedex", Teal),
        MenuItem("Moves", Red),
        MenuItem("Abilities", LightBlue),
        MenuItem("Items", Yellow),
        MenuItem("Locations", Purple),
        MenuItem("Type charts", Brown)
    )
}