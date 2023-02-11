package com.johndev.pokedexjc.navigation

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.data.ViewModels
import com.johndev.pokedexjc.data.getOnboardingMenu
import com.johndev.pokedexjc.ui.components.AlertDialogDeveloping
import com.johndev.pokedexjc.ui.components.BackdropScaffoldPokemon
import com.johndev.pokedexjc.ui.components.CardMainMenu
import com.johndev.pokedexjc.ui.components.TopAppBarActivities
import com.johndev.pokedexjc.ui.moves.viewModel.MovesList
import com.johndev.pokedexjc.ui.moves.viewModel.MovesViewModel
import com.johndev.pokedexjc.ui.onboarding.OnboardingModelScreen
import com.johndev.pokedexjc.ui.pokedex.PokedexList
import com.johndev.pokedexjc.ui.pokedex.viewModel.PokedexViewModel
import com.johndev.pokedexjc.ui.pokedexDetails.viewModel.DetailsViewModel
import com.johndev.pokedexjc.ui.theme.BackgroundColor
import com.johndev.pokedexjc.ui.theme.Bug
import com.johndev.pokedexjc.ui.theme.Fire

@Composable
fun OnboardingScreen_One(navigationController: NavHostController) {
    val context = LocalContext.current
    val menuOnboarding = getOnboardingMenu()
    OnboardingModelScreen(
        menu = menuOnboarding[0],
        bgColor = Bug,
        {
            //  Next Button
            navigationController.navigate(Routes.OnboardingScreen_Two.route)
        },
        {
            //  Prev Button
            Toast.makeText(context, "No Exist", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun OnboardingScreen_Two(
    navigationController: NavHostController,
    sharedPreferences: SharedPreferences
) {
    val menuOnboarding = getOnboardingMenu()
    OnboardingModelScreen(menu = menuOnboarding[1],
        bgColor = Fire,
        {
            //  Next Button
            navigationController.navigate(Routes.HomeScreen.route)
            saveIsFirstStart(sharedPreferences, false)
        },
        {
            //  Prev Button
            navigationController.navigate(Routes.OnboardingScreen_One.route)
        }
    )
}

fun saveIsFirstStart(sharedPreferences: SharedPreferences, isFirstStart: Boolean = true) {
    sharedPreferences.edit {
        putBoolean("isFirstStart", isFirstStart)
        apply()
    }
}

@Composable
fun HomeScreen(navigationController: NavHostController) {
    var openDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CardMainMenu {
            when (it) {
                //  Launch Pokedex
                0 -> navigationController.navigate(Routes.PokedexScreen.route)
                //  Launch Moves
                1 -> navigationController.navigate(Routes.MovesScreen.route)
                else -> openDialog = true
            }
        }
        AlertDialogDeveloping(openDialog) {
            openDialog = it
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PokedexScreen(navigationController: NavHostController) {
    getPokemonsRoom(ViewModels.pokemonViewModel)
    Scaffold(
        topBar = {
            TopAppBarActivities("Pokedex") {
                navigationController.navigate(Routes.HomeScreen.route)
            }
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = BackgroundColor,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                PokedexList(ViewModels.pokemonViewModel) {
                    navigationController.navigate(Routes.DetailsPokemonScreen.createRoute(it))
                }
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovesScreen(navigationController: NavHostController) {
    getMovesRoom(ViewModels.movesViewModel)
    Scaffold(
        topBar = {
            TopAppBarActivities("Moves") {
                navigationController.navigate(Routes.HomeScreen.route)
            }
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = BackgroundColor,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                MovesList(ViewModels.movesViewModel)
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsPokemonScreen(navigationController: NavHostController, idPokemon: Int) {
    val detailsViewModel = DetailsViewModel()
    detailsViewModel.getPokemon(idPokemon)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = colorResource(id = R.color.primaryColor),
        content = {
            BackdropScaffoldPokemon(detailsViewModel, navigationController)
        }
    )
}

private fun getPokemonsRoom(pokemonViewModel: PokedexViewModel) {
    pokemonViewModel.getAllPokemon()
}

private fun getMovesRoom(movesViewModel: MovesViewModel) {
    movesViewModel.getAllMovesRoom()
}