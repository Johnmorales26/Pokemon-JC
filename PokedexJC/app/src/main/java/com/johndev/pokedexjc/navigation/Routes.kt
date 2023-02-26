package com.johndev.pokedexjc.navigation

sealed class Routes(val route: String) {

    object OnboardingMain : Routes("OnboardingScreenMain")
    object OnboardingStart : Routes("OnboardingScreenStart")

    object HomeScreen : Routes("HomeScreen")
    object PokedexScreen : Routes("PokedexScreen")
    object MovesScreen : Routes("MovesScreen")
    object ItemScreen : Routes("ItemScreen")
    object AbilityScreen : Routes("AbilityScreen")

    object FavoritesScreen : Routes("FavoritesScreen")
    object CapturedScreen : Routes("CapturedScreen")

    object DetailsPokemonScreen : Routes("DetailsPokemonScreen/{idPokemon}") {
        fun createRoute(idPokemon: Int) = "DetailsPokemonScreen/$idPokemon"
    }

}