package com.johndev.pokedexjc.navigation

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.data.ViewModels.itemViewModel
import com.johndev.pokedexjc.data.ViewModels.movesViewModel
import com.johndev.pokedexjc.data.ViewModels.pokemonViewModel
import com.johndev.pokedexjc.model.entity.ItemEntity
import com.johndev.pokedexjc.ui.components.*
import com.johndev.pokedexjc.ui.items.viewModel.ItemViewModel
import com.johndev.pokedexjc.ui.moves.viewModel.MovesList
import com.johndev.pokedexjc.ui.moves.viewModel.MovesViewModel
import com.johndev.pokedexjc.ui.pokedex.PokedexList
import com.johndev.pokedexjc.ui.pokedex.viewModel.PokedexViewModel
import com.johndev.pokedexjc.ui.theme.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen_One(navigationController: NavHostController) {
    var counter by remember { mutableStateOf(0) }
    getPokemonsRetrofit(pokemonViewModel)
    getMovesRetrofit(movesViewModel)
    getItemsRetrofit(itemViewModel)
    LocalContext.current
    val description = StringBuilder()
        .append(stringResource(id = R.string.app_name))
        .append(" It's a Pokedex with a beautiful and elegant design for everyone to use.")
        .toString()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fire)
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to",
                modifier = Modifier,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier,
                style = MaterialTheme.typography.h3
            )
            Image(
                painter = painterResource(id = R.drawable.pokebola), contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Justify
            )
            Text(
                text = "Let's get started!",
                style = MaterialTheme.typography.subtitle1
            )
            if (counter >= 100) {
                FabCommon(
                    painterRes = R.drawable.ic_arrow_forward
                ) {
                    navigationController.navigate(Routes.OnboardingStart.route)
                }
            } else {
                CircularProgressIndicator(
                    color = SecondaryColor,
                )
                when (counter) {
                    in 0..25 -> {
                        Text(text = "${counter}% Download Pokemon")
                    }
                    in 26..45 -> {
                        Text(text = "${counter}% Download Items")
                    }
                    in 46..65 -> {
                        Text(text = "${counter}% Download Moves")
                    }
                    in 66..85 -> {
                        Text(text = "${counter}% Download Abilities")
                    }
                    else -> {
                        Text(text = "${counter}% Configuring the Pókedex")
                    }
                }
            }
        }
        val handler = Handler()
        if (counter != 100) {
            handler.postDelayed(
                {
                    counter += 1
                    println("Counter $counter")
                },
                800
            )
        }
    }
}

@Composable
fun OnboardingScreen_Two(
    navigationController: NavHostController,
    sharedPreferences: SharedPreferences
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fire),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Everything is ready!",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Everything on this page is optional.\nTo skip and use Pokedex JC, press the button below.",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify
            )
            ExtFabCommon(
                textRes = R.string.btn_start,
                iconRes = R.drawable.ic_check_circle_outline,
            ) {
                navigationController.navigate(Routes.HomeScreen.route)
                saveIsFirstStart(sharedPreferences, false)
            }
            Divider(
                color = SecondaryLightColor
            )
            Text(
                text = "Become a PRO Trainer",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "With Pokedex JC Pro, you enjoy an ad-free experience, full team builder, and Dark Mode!\n(One-time purchase, no subscriptions)",
                style = MaterialTheme.typography.body1,
            )
            ExtFabCommon(
                textRes = R.string.btn_upgrade_pro,
                iconRes = R.drawable.ic_credit_card,
            ) {
                navigationController.navigate(Routes.HomeScreen.route)
                saveIsFirstStart(sharedPreferences, false)
            }
        }
    }

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
    findAllData()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CardMainMenu {
            when (it) {
                //  Launch Pokedex
                0 -> navigationController.navigate(Routes.PokedexScreen.route)
                //  Launch Moves
                1 -> navigationController.navigate(Routes.MovesScreen.route)
                //  Launch Moves
                3 -> navigationController.navigate(Routes.ItemScreen.route)
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
                PokedexList(pokemonViewModel) {
                    navigationController.navigate(Routes.DetailsPokemonScreen.createRoute(it))
                }
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovesScreen(navigationController: NavHostController) {
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
                MovesList(movesViewModel)
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsPokemonScreen(navigationController: NavHostController, idPokemon: Int) {
    pokemonViewModel.findById(idPokemon)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = colorResource(id = R.color.primaryColor),
        content = {
            BackdropScaffoldPokemon(navigationController)
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ItemScreen(navigationController: NavHostController) {
    val itemsList: List<ItemEntity> by itemViewModel.allItems.observeAsState(initial = listOf())
    Scaffold(
        topBar = {
            TopAppBarActivities("Items") {
                navigationController.navigate(Routes.HomeScreen.route)
            }
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = BackgroundColor,
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                items(itemsList.size) { index ->
                    val item = itemsList[index]
                    CardItem(
                        itemEntity = item
                    )
                }
            }
        }
    )
}

private fun findAllData() {
    pokemonViewModel.getPokemonsByRoom()
    movesViewModel.getMoveByRoom()
    itemViewModel.getItemsByRoom()
}

@OptIn(DelicateCoroutinesApi::class)
private fun getPokemonsRetrofit(pokemonViewModel: PokedexViewModel) {
    GlobalScope.launch(Dispatchers.IO) {
        //(1..10271).forEach {
        (1..1000).forEach {
            pokemonViewModel.getPokemon(it)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun getItemsRetrofit(itemViewModel: ItemViewModel) {
    GlobalScope.launch(Dispatchers.IO) {
        //(1..2050).forEach {
        (1..1000).forEach {
            itemViewModel.getItem(it)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun getMovesRetrofit(movesViewModel: MovesViewModel) {
    GlobalScope.launch(Dispatchers.IO) {
        (1..918).forEach {
            //(1..100).forEach {
            movesViewModel.getMoveRetrofit(it)
        }
    }
}