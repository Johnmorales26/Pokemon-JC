package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.data.PokemonUtils.calculateHeight
import com.johndev.pokedexjc.data.PokemonUtils.calculateWeight
import com.johndev.pokedexjc.model.dataMoves.MoveEntity
import com.johndev.pokedexjc.model.dataMoves.categoryIcon
import com.johndev.pokedexjc.model.dataMoves.type
import com.johndev.pokedexjc.ui.theme.PokedexJCTheme
import des.c5inco.pokedexer.model.Pokemon
import des.c5inco.pokedexer.model.mapTypeToColor
import des.c5inco.pokedexer.model.mapTypeToColorString
import des.c5inco.pokedexer.model.pokemonColor

@Composable
fun CardMainMenu(
    onShowDialog: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
                    bottomStart = 32.dp,
                    bottomEnd = 32.dp
                )
    ) {
        Box {
            Column(
                modifier = Modifier
                    .background(colorResource(id = R.color.primaryLightColor))
                    .padding(top = 32.dp, bottom = 16.dp, start = 32.dp, end = 32.dp)
            ) {
                Text(
                    text = "What Pokémon \nare you looking for?",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(
                        top = 64.dp,
                        bottom = 24.dp
                    )
                )
                RoundedSearchBar()
                MenuMain {
                    onShowDialog(it)
                }
            }
        }
    }
}

@Composable
fun PokeDexCard(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onPokemonSelected: (Pokemon) -> Unit = {}
) {
    Surface(
        modifier = modifier,
        color = pokemonColor(pokemon.typeOfPokemon),
        shape = RoundedCornerShape(16.dp)
    ) {
        PokeDexCardContent(
            modifier = Modifier.clickable {
                onPokemonSelected(pokemon)
            },
            pokemon = pokemon
        )
    }
}

@Composable
private fun PokeDexCardContent(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Box(modifier.height(120.dp)) {
        Column(
            Modifier.padding(top = 24.dp, start = 12.dp)
        ) {
            PokemonName(pokemon.name)
            Spacer(Modifier.height(8.dp))
            PokemonTypeLabels(pokemon.typeOfPokemon, TypeLabelMetrics.SMALL)
        }
        Text(
            text = StringBuilder().append("#").append(pokemon.id).toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 10.dp, end = 12.dp)
                .graphicsLayer {
                    alpha = 0.1f
                }
        )
        /*PokeBall(
            Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 5.dp, y = 10.dp)
                .requiredSize(96.dp),
            Color.White,
            0.25f
        )*/

        AsyncImage(
            model = pokemon.image,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 6.dp, end = 6.dp)
                .size(80.dp)
        )
    }
}

@Composable
private fun PokemonName(name: String?) {
    Text(
        text = name ?: "",
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.White,
    )
}

@Preview
@Composable
private fun PokeDexCardPreview() {
    PokedexJCTheme {
        Surface {
            Column(
                Modifier.width(200.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                /*PokeDexCard(
                    modifier = Modifier.fillMaxWidth(),
                    pokemon = SamplePokemonData[0]
                )
                PokeDexCard(
                    modifier = Modifier.fillMaxWidth(),
                    pokemon = SamplePokemonData[3]
                )
                PokeDexCard(
                    modifier = Modifier.fillMaxWidth(),
                    pokemon = SamplePokemonData[6]
                )*/
            }
        }
    }
}

@Composable
fun CardPokemonSize(height: Int, weight: Int) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                    text = "Height",
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp, top = 8.dp),
                    text = calculateHeight(height),
                    style = MaterialTheme.typography.h6
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                    text = "Weight",
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp, top = 8.dp),
                    text = calculateWeight(weight),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Composable
fun CardMoves(move: MoveEntity) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.weight(3f),
                text = move.name,
                style = MaterialTheme.typography.subtitle2
            )
            ChipType(
                label = move.type,
                modifier = Modifier.weight(2.5f),
                color = mapTypeToColorString(move.type.replaceFirstChar(Char::titlecase))
            )
            Image(
                modifier = Modifier.weight(1.5f).size(24.dp),
                painter = painterResource(id = move.categoryIcon()),
                contentDescription = null
            )
            Text(
                modifier = Modifier.weight(1.5f),
                text = move.power.toString(),
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier.weight(1.5f),
                text = move.accuracy.toString(),
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface {
        Column(
            Modifier.fillMaxWidth()
        ) {

        }
    }
}