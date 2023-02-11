package com.johndev.pokedexjc.data

import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.model.MenuItem
import com.johndev.pokedexjc.model.Onboarding
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

fun getOnboardingMenu(): List<Onboarding> {
    return listOf(
        Onboarding(
            imgRes = R.drawable.poke003,
            titleRes = R.string.onboarding_section_pokedex,
            descriptionRes = R.string.onboarding_section_pokedex_description,
            colorSelected = "Bug"
        ),
        Onboarding(
            imgRes = R.drawable.poke006,
            titleRes = R.string.onboarding_section_moves,
            descriptionRes = R.string.onboarding_section_moves_description,
            colorSelected = "Fire"
        )
    )
}