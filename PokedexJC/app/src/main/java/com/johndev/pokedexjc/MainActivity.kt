package com.johndev.pokedexjc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.johndev.pokedexjc.data.ViewModels.movesViewModel
import com.johndev.pokedexjc.data.ViewModels.pokemonViewModel
import com.johndev.pokedexjc.ui.home.HomeScreen
import com.johndev.pokedexjc.ui.moves.view.MovesActivity
import com.johndev.pokedexjc.ui.moves.viewModel.MovesViewModel
import com.johndev.pokedexjc.ui.pokedex.view.PokedexActivity
import com.johndev.pokedexjc.ui.pokedex.viewModel.PokedexViewModel
import com.johndev.pokedexjc.ui.theme.PokedexJCTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            getPokemonsAPI(pokemonViewModel)
            getMovesRetrofit(movesViewModel)
            PokedexJCTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = colorResource(id = R.color.primaryColor),
                    content = {
                        HomeScreen { activityLaunch ->
                            when(activityLaunch) {
                                0 -> {
                                    val intent = Intent(context, PokedexActivity::class.java)
                                    startActivity(intent)
                                }
                                1 -> {
                                    val intent = Intent(context, MovesActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    private fun getPokemonsAPI(pokemonViewModel: PokedexViewModel) {
        (1..100).forEach {
            pokemonViewModel.getPokemon(it)
        }
    }

    private fun getMovesRetrofit(movesViewModel: MovesViewModel) {
        (1..100).forEach {
            movesViewModel.getMoveRetrofit(it)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexJCTheme {
        HomeScreen {

        }
    }
}