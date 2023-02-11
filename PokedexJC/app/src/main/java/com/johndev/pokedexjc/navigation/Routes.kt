package com.johndev.pokedexjc.navigation

sealed class Routes(val route: String) {

    object OnboardingScreen_One : Routes("OnboardingScreen_One")
    object OnboardingScreen_Two : Routes("OnboardingScreen_Two")

    object HomeScreen : Routes("HomeScreen")
    object PokedexScreen : Routes("PokedexScreen")
    object MovesScreen : Routes("MovesScreen")
    object DetailsPokemonScreen : Routes("DetailsPokemonScreen/{idPokemon}") {
        fun createRoute(idPokemon: Int) = "DetailsPokemonScreen/$idPokemon"
    }

}