package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.model.dataPokemon.PokemonComplete
import com.johndev.pokedexjc.ui.pokedexDetails.viewModel.DetailsViewModel
import des.c5inco.pokedexer.model.pokemonColor
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun BackdropScaffoldPokemon(detailsViewModel: DetailsViewModel) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    val pokemon: PokemonComplete by detailsViewModel.pokemonDetails.observeAsState(initial = PokemonComplete())
    LaunchedEffect(scaffoldState) {
        scaffoldState.reveal()
    }
    BackdropScaffold(
            scaffoldState = scaffoldState,
            appBar = {
                TopAppBar(
                    title = { Text(pokemon.name.replaceFirstChar(Char::titlecase)) },
                    navigationIcon = {
                        if (scaffoldState.isConcealed) {
                            IconButton(onClick = { scope.launch { scaffoldState.reveal() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Localized description")
                            }
                        } else {
                            IconButton(onClick = { scope.launch { scaffoldState.conceal() } }) {
                                Icon(Icons.Default.Close, contentDescription = "Localized description")
                            }
                        }
                    },
                    actions = {
                        var clickCount by remember { mutableStateOf(0) }
                        IconButton(
                            onClick = {
                                // show snackbar as a suspend function
                                scope.launch {
                                    scaffoldState.snackbarHostState
                                        .showSnackbar("Snackbar #${++clickCount}")
                                }
                            }
                        ) {
                            Icon(Icons.Default.Favorite, contentDescription = "Localized description")
                        }
                    },
                    elevation = 0.dp,
                    backgroundColor = Color.Transparent
                )
            },
            backLayerBackgroundColor = pokemonColor(
                (if (pokemon.types == null) {
                    "Fire"
                } else {
                    pokemon.types!![0].type.name
                })
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
                    PokemonBackLayer(pokemon)
                }
            },
            frontLayerContent = {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PokemonFrontLayer(pokemon)
                }
            }
        )
}
