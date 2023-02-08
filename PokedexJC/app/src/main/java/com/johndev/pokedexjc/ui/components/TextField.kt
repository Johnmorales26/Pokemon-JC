package com.johndev.pokedexjc.ui.components

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*

@Composable
fun TextFieldSearchMain() {
    var searchValue by remember { mutableStateOf("") }
    OutlinedTextField(
        value = searchValue,
        onValueChange = {
            searchValue = it
        }
    )
}