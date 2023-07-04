package com.johndev.pokedex.pokedexModule.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.johndev.pokedex.common.utils.PokedexUtils.disableScrolling
import com.johndev.pokedex.common.utils.PokedexUtils.reenableScrolling
import com.johndev.pokedex.pokedexModule.view.components.PokemonCard
import com.johndev.pokedex.R
import com.johndev.pokedex.pokedexModule.viewModel.PokedexViewModel
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(pokedexViewModel: PokedexViewModel) {
    pokedexViewModel.getPokemonPerPage()
    Scaffold(
        topBar = {
            Header()
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Body(pokedexViewModel = pokedexViewModel)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Header() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = null
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = null
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_generation),
                    contentDescription = null
                )
            }
        })
}

@Composable
private fun Body(pokedexViewModel: PokedexViewModel) {
    val listPokemon by pokedexViewModel.pokemonList.observeAsState(emptyList())
    val enableScroll by pokedexViewModel.enableScroll.observeAsState(false)
    val loadView by pokedexViewModel.loadView.observeAsState(false)
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .filter { it.isNotEmpty() }
            .collect { visibleItems ->
                val lastVisibleItemIndex = visibleItems.last().index
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisibleItemIndex == totalItems - 1) {
                    pokedexViewModel.disableScroll()
                    listState.disableScrolling(scope)
                    pokedexViewModel.loadMorePokemon()
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(listPokemon!!.size) { index ->
                listPokemon!![index]?.let {
                    PokemonCard(pokemonEntity = it) {
                        Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }

        if (loadView) {
            LoadView(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (enableScroll) {
        listState.reenableScrolling(scope)
        pokedexViewModel.disableScroll()
    }
}

@Composable
fun LoadView(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}
