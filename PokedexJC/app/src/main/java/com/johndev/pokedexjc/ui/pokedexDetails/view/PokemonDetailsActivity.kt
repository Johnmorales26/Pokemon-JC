package com.johndev.pokedexjc.ui.pokedexDetails.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.ui.components.BackdropScaffoldPokemon
import com.johndev.pokedexjc.ui.pokedexDetails.viewModel.DetailsViewModel
import com.johndev.pokedexjc.ui.theme.PokedexJCTheme

class PokemonDetailsActivity : ComponentActivity() {

    private lateinit var detailsViewModel: DetailsViewModel

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id_pokemon = intent.getIntExtra("id_passed", 0)
        detailsViewModel = DetailsViewModel()
        detailsViewModel.getPokemon(id_pokemon)
        setContent {
            PokedexJCTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = colorResource(id = R.color.primaryColor),
                    content = {
                        BackdropScaffoldPokemon(detailsViewModel)
                    }
                )
            }
        }
    }
}