package com.johndev.pokedexjc.ui.pokedex.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.ui.components.TopAppBarActivities
import com.johndev.pokedexjc.ui.pokedex.PokedexScreen
import com.johndev.pokedexjc.ui.pokedex.viewModel.PokedexViewModel
import com.johndev.pokedexjc.ui.pokedexDetails.view.PokemonDetailsActivity
import com.johndev.pokedexjc.ui.theme.BackgroundColor
import com.johndev.pokedexjc.ui.theme.PokedexJCTheme

class PokedexActivity : ComponentActivity() {

    private lateinit var pokedexViewModel: PokedexViewModel

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokedexViewModel = PokedexViewModel()
        getPokemonsAPI(pokedexViewModel)
        getPokemonsRoom(pokedexViewModel)
        setContent {
            PokedexJCTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { TopAppBarActivities {
                        finish()
                    } },
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = BackgroundColor,
                    content = {
                        Content()
                    }
                )
            }
        }
    }

    @Composable
    fun Content() {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            PokedexScreen(pokedexViewModel) {
                val intent = Intent(context, PokemonDetailsActivity::class.java).apply {
                    putExtra("id_passed", it)
                }
                startActivity(intent)
            }
        }
    }

    fun getPokemonsAPI(pokemonViewModel: PokedexViewModel) {
        (1..100).forEach {
            pokemonViewModel.getPokemon(it)
        }
    }

    fun getPokemonsRoom(pokemonViewModel: PokedexViewModel) {
        pokemonViewModel.getAllPokemon()
    }

}