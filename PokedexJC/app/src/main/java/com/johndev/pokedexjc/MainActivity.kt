package com.johndev.pokedexjc

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.preference.PreferenceManager
import com.johndev.pokedexjc.navigation.*
import com.johndev.pokedexjc.ui.theme.PokedexJCTheme

class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        setContent {
            PokedexJCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = startDestination(sharedPreferences)
                    ) {
                        composable(Routes.OnboardingMain.route) {
                            OnboardingScreen_One(
                                navigationController = navigationController
                            )
                        }
                        composable(Routes.OnboardingStart.route) {
                            OnboardingScreen_Two(
                                navigationController = navigationController,
                                        sharedPreferences = sharedPreferences
                            )
                        }
                        composable(Routes.HomeScreen.route) {
                            HomeScreen(
                                navigationController = navigationController
                            )
                        }
                        composable(Routes.PokedexScreen.route) {
                            PokedexScreen(
                                navigationController = navigationController
                            )
                        }
                        composable(Routes.MovesScreen.route) {
                            MovesScreen(
                                navigationController = navigationController
                            )
                        }
                        composable(Routes.ItemScreen.route) {
                            ItemScreen(
                                navigationController = navigationController
                            )
                        }
                        composable(
                            Routes.DetailsPokemonScreen.route,
                            arguments = listOf(navArgument("idPokemon") {
                                type = NavType.IntType
                            })
                        ) { backStaclEntry ->
                            DetailsPokemonScreen(
                                navigationController,
                                backStaclEntry.arguments?.getInt("idPokemon") ?: 0
                            )
                        }
                    }
                }
            }
        }
    }

    private fun startDestination(sharedPreferences: SharedPreferences): String {
        return if (sharedPreferences.getBoolean("isFirstStart", true)) {
            Routes.OnboardingMain.route
        } else {
            Routes.HomeScreen.route
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexJCTheme {

    }
}