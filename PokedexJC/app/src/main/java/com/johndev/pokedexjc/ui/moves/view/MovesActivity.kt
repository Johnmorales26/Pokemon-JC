package com.johndev.pokedexjc.ui.moves.view

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.data.ViewModels.movesViewModel
import com.johndev.pokedexjc.ui.components.TopAppBarActivities
import com.johndev.pokedexjc.ui.moves.viewModel.MovesScreen
import com.johndev.pokedexjc.ui.moves.viewModel.MovesViewModel
import com.johndev.pokedexjc.ui.theme.BackgroundColor
import com.johndev.pokedexjc.ui.theme.PokedexJCTheme

class MovesActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMovesRoom(movesViewModel)
        setContent {
            PokedexJCTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { TopAppBarActivities("Moves") {
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

    private fun getMovesRoom(movesViewModel: MovesViewModel) {
        movesViewModel.getAllMovesRoom()
    }

    @Composable
    fun Content() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            MovesScreen(movesViewModel)
        }
    }

}