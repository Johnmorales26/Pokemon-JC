package com.johndev.pokedexjc.navigation

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Handler
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.data.ViewModels.abilityViewModel
import com.johndev.pokedexjc.data.ViewModels.itemViewModel
import com.johndev.pokedexjc.data.ViewModels.movesViewModel
import com.johndev.pokedexjc.data.ViewModels.pokemonViewModel
import com.johndev.pokedexjc.model.entity.AbilityEntity
import com.johndev.pokedexjc.model.entity.ItemEntity
import com.johndev.pokedexjc.model.entity.MoveEntity
import com.johndev.pokedexjc.model.entity.PokemonEntity
import com.johndev.pokedexjc.ui.ability.viewModel.AbilityViewModel
import com.johndev.pokedexjc.ui.components.*
import com.johndev.pokedexjc.ui.items.viewModel.ItemViewModel
import com.johndev.pokedexjc.ui.moves.viewModel.MovesList
import com.johndev.pokedexjc.ui.moves.viewModel.MovesViewModel
import com.johndev.pokedexjc.ui.pokedex.PokedexList
import com.johndev.pokedexjc.ui.pokedex.viewModel.PokedexViewModel
import com.johndev.pokedexjc.ui.theme.*
import kotlinx.coroutines.*

@Composable
fun OnboardingScreen_One(navigationController: NavHostController) {
    var counter by remember { mutableStateOf(0) }
    var isVisibleStart by remember { mutableStateOf(false) }
    var isVisibleLoading by remember { mutableStateOf(true) }
    getPokemonsRetrofit(pokemonViewModel)
    getMovesRetrofit(movesViewModel)
    getItemsRetrofit(itemViewModel)
    getAbilitiesRetrofit(abilityViewModel)
    LocalContext.current
    val description = StringBuilder()
        .append(stringResource(id = R.string.app_name))
        .append(" It's a Pokedex with a beautiful and elegant design for everyone to use.")
        .toString()
    Column(
        modifier = Modifier
            .fillMaxSize()
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
            AnimatedVisibility(isVisibleStart) {
                FabCommon(
                    painterRes = R.drawable.ic_arrow_forward
                ) {
                    navigationController.navigate(Routes.OnboardingStart.route)
                }
            }
            AnimatedVisibility(visible = isVisibleLoading) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
        }
        val handler = Handler()
        if (counter != 100) {
            handler.postDelayed(
                {
                    counter += 1
                    println("Counter $counter")
                    if (counter == 100) isVisibleStart = true
                    if (counter == 100) isVisibleLoading = false
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
            .fillMaxSize(),
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
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CardMainMenu {
            when (it) {
                //  Launch Pokedex
                0 -> navigationController.navigate(Routes.PokedexScreen.route)
                //  Launch Moves
                1 -> navigationController.navigate(Routes.MovesScreen.route)
                //  Launch Ability
                2 -> navigationController.navigate(Routes.AbilityScreen.route)
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


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PokedexScreen(navigationController: NavHostController) {
    val radioSortBy = listOf(
        "ID (# / Number)", "Alphabetical (A - Z)", "Total", "PS", "Attack", "Defense",
        "Special Attack", "Special Defense", "Speed"
    )
    val radioOrder = listOf("Upward", "Falling")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioSortBy[0]) }
    val (selectedOptionOrder, onOptionSelectedOrder) = remember { mutableStateOf(radioOrder[0]) }
    val pokemonState by pokemonViewModel.getPokemonListOrdenate(selectedOption, selectedOptionOrder)
        .collectAsState(initial = null)
    //val pokemonListState: List<PokemonEntity> by pokemonViewModel.pokemonList.collectAsState(initial = emptyList())
    val skipHalfExpanded by remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded
    )
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBarActivities(
                titleAppBar = "Pokedex",
                isActionsVisible = true
            ) {
                when (it) {
                    0 -> navigationController.navigate(Routes.HomeScreen.route)
                    1 -> navigationController.navigate(Routes.FavoritesScreen.route)
                    2 -> navigationController.navigate(Routes.CapturedScreen.route)
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
        //backgroundColor = BackgroundColor,
        content = {
            ModalBottomSheetLayout(
                sheetState = state,
                sheetContent = {
                    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
                        Text(
                            text = "Sort by...",
                            style = MaterialTheme.typography.h6
                        )
                        Column(modifier = Modifier.selectableGroup()) {
                            radioSortBy.forEach { text ->
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                        .selectable(
                                            selected = (text == selectedOption),
                                            onClick = { onOptionSelected(text) },
                                            role = Role.RadioButton
                                        )
                                        .padding(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = (text == selectedOption),
                                        onClick = null // null recommended for accessibility with screenreaders
                                    )
                                    Text(
                                        text = text,
                                        style = MaterialTheme.typography.body1.merge(),
                                        modifier = Modifier.padding(start = 16.dp)
                                    )
                                }
                            }
                        }
                        Text(
                            text = "Order",
                            style = MaterialTheme.typography.h6
                        )
                        Row(modifier = Modifier.selectableGroup()) {
                            radioOrder.forEach { text ->
                                Row(
                                    Modifier
                                        .weight(1f)
                                        .height(40.dp)
                                        .selectable(
                                            selected = (text == selectedOptionOrder),
                                            onClick = { onOptionSelectedOrder(text) },
                                            role = Role.RadioButton
                                        )
                                        .padding(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = (text == selectedOptionOrder),
                                        onClick = null // null recommended for accessibility with screenreaders
                                    )
                                    Text(
                                        text = text,
                                        style = MaterialTheme.typography.body1.merge(),
                                        modifier = Modifier.padding(start = 16.dp)
                                    )
                                }
                            }
                        }
                        /*Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                pokemonListState.sortedByDescending { it.id }
                                //pokemonViewModel.orderPokemon(selectedOption, selectedOptionOrder)
                            }
                        ) {
                            Text(text = "Apply")
                        }*/
                    }
                }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    pokemonState?.let {
                        PokedexList(it) {
                            navigationController.navigate(Routes.DetailsPokemonScreen.createRoute(it))
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            AnimatedVisibility(!state.isVisible) {
                FloatingActionButton(onClick = { scope.launch { state.show() } }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_list),
                        contentDescription = null
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovesScreen(navigationController: NavHostController) {
    val moveListState: List<MoveEntity> by movesViewModel.movesList.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBarActivities("Moves") {
                navigationController.navigate(Routes.HomeScreen.route)
            }
        },
        modifier = Modifier.fillMaxSize(),
        //backgroundColor = BackgroundColor,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                MovesList(moveListState)
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsPokemonScreen(navigationController: NavHostController, pokemonId: Int) {
    val pokemonState by pokemonViewModel.getPokemonById(pokemonId).collectAsState(initial = null)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = colorResource(id = R.color.primaryColor),
        content = {
            if (pokemonState != null) {
                BackdropScaffoldPokemon(pokemonState!!, navigationController)
            } else {
                CircularProgressIndicator()
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ItemScreen(navigationController: NavHostController) {
    val itemsListState: List<ItemEntity> by itemViewModel.itemsList.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBarActivities("Items") {
                navigationController.navigate(Routes.HomeScreen.route)
            }
        },
        modifier = Modifier.fillMaxSize(),
        //backgroundColor = BackgroundColor,
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                items(itemsListState.size) { index ->
                    val item = itemsListState[index]
                    CardItem(
                        itemEntity = item
                    )
                }
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AbilityScreen(navigationController: NavHostController) {
    val abilityListState: List<AbilityEntity> by abilityViewModel.abilityList.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBarActivities("Abilities") {
                navigationController.navigate(Routes.HomeScreen.route)
            }
        },
        modifier = Modifier.fillMaxSize(),
        //backgroundColor = BackgroundColor,
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                items(abilityListState.size) { index ->
                    val item = abilityListState[index]
                    CardAbility(
                        abilityEntity = item
                    ) {

                    }
                }
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(navigationController: NavHostController) {
    val pokemonListState: List<PokemonEntity> by pokemonViewModel.pokemonFavoriteList.collectAsState(
        initial = emptyList()
    )
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBarExtra(
                titleRes = R.string.title_favorites,
                onNavigationClick = { navigationController.navigate(Routes.PokedexScreen.route) },
                onDeleteAll = {
                    if (pokemonListState.isNotEmpty()) pokemonListState.map {
                        pokemonViewModel.updateFavorite(
                            it
                        )
                    }
                    else Toast.makeText(context, "Your favorites are empty", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        },
        content = {
            if (pokemonListState.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    PokedexList(pokemonListState) {
                        navigationController.navigate(Routes.DetailsPokemonScreen.createRoute(it))
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnnouncementFavoritesEmpty()
                }
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CapturedScreen(navigationController: NavHostController) {
    val pokemonListState: List<PokemonEntity> by pokemonViewModel.pokemonCapturedList.collectAsState(
        initial = emptyList()
    )
    val pokemonListSize: Int by pokemonViewModel.pokemonListSize.collectAsState(initial = 0)
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBarExtra(
                titleRes = R.string.title_list_of_captured,
                isCaptured = true,
                percentText = StringBuilder().append(pokemonListSize).append("%").toString(),
                onNavigationClick = { navigationController.navigate(Routes.PokedexScreen.route) }
            ) {
                if (pokemonListState.isNotEmpty()) pokemonListState.map {
                    pokemonViewModel.updateCaptured(it)
                }
                else Toast.makeText(context, "Your captured list are empty", Toast.LENGTH_SHORT)
                    .show()
            }
        },
        content = {
            if (pokemonListState.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    PokedexList(pokemonListState) {
                        navigationController.navigate(Routes.DetailsPokemonScreen.createRoute(it))
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnnouncementCapturedEmpty()
                }
            }
        }
    )
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

@OptIn(DelicateCoroutinesApi::class)
private fun getAbilitiesRetrofit(abilityViewModel: AbilityViewModel) {
    GlobalScope.launch(Dispatchers.IO) {
        (1..358).forEach {
            //(1..100).forEach {
            abilityViewModel.getAbility(it)
        }
    }
}