package com.johndev.pokedexjc.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.johndev.pokedexjc.ui.components.AlertDialogDeveloping
import com.johndev.pokedexjc.ui.components.CardMainMenu

@Composable
fun HomeScreen(onActivityLaunch: (Int) -> Unit) {
    var openDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CardMainMenu {
            if (it == 0) {
                onActivityLaunch(it)
            } else {
                openDialog = true
            }
        }
        AlertDialogDeveloping(openDialog) {
            openDialog = it
        }
    }
}