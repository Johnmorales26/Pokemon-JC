package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.data.PokemonUtils.titleCase
import com.johndev.pokedexjc.data.ViewModels.pokemonViewModel
import com.johndev.pokedexjc.model.entity.PokemonEntity
import com.johndev.pokedexjc.model.entity.pokemonColor
import com.johndev.pokedexjc.navigation.Routes
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun BackdropScaffoldPokemon(
    pokemon: PokemonEntity,
    navigationController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)

    var isFavorite by remember { mutableStateOf(pokemon.isFavorite) }
    var isCaptured by remember { mutableStateOf(pokemon.isCaptured) }
    LaunchedEffect(scaffoldState) {
        scaffoldState.reveal()
    }
    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(
                title = { Text(titleCase(pokemon.name ?: "")) },
                navigationIcon = {
                    IconButton(onClick = { navigationController.navigate(Routes.PokedexScreen.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            pokemon.let {
                                isFavorite = !isFavorite
                                pokemonViewModel.updateFavorite(it)
                            }
                        }
                    ) {
                        if (isFavorite) {
                            Icon(Icons.Default.Favorite, contentDescription = null)
                        } else {
                            Icon(Icons.Default.FavoriteBorder, contentDescription = null)
                        }
                    }
                    IconButton(
                        onClick = {
                            pokemon.let {
                                isCaptured = !isCaptured
                                pokemonViewModel.updateCaptured(it)
                            }
                        }
                    ) {
                        if (isCaptured) {
                            Icon(painterResource(id = R.drawable.ic_check_circle), contentDescription = null)
                        } else {
                            Icon(painterResource(id = R.drawable.ic_check_circle_outline), contentDescription = null)
                        }
                    }
                    if (scaffoldState.isConcealed) {
                        IconButton(onClick = { scope.launch { scaffoldState.reveal() } }) {
                            Icon(painter = painterResource(id = R.drawable.ic_arrow_downward), contentDescription = "Localized description")
                        }
                    } else {
                        IconButton(onClick = { scope.launch { scaffoldState.conceal() } }) {
                            Icon(painter = painterResource(id = R.drawable.ic_arrow_upward), contentDescription = "Localized description")
                        }
                    }
                },
                elevation = 0.dp,
                backgroundColor = Color.Transparent
            )
        },
        backLayerBackgroundColor = pokemonColor(
            (pokemon.typeOfPokemon ?: "Fire")
        ),
        backLayerContent = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                RotatingPokeBall(
                    Modifier
                        .align(Alignment.TopCenter)
                        //.statusBarsPadding()
                        //.padding(top = 16.dp)
                        .padding(top = 60.dp)
                        .size(200.dp)
                    //.graphicsLayer { alpha = textAlphaTarget }
                )
                if (pokemon.name != null) {
                    PokemonBackLayer(pokemon)
                }
            }
        },
        frontLayerContent = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (pokemon.name != null) {
                    PokemonFrontLayer(pokemon)
                }
            }
        }
    )
}
