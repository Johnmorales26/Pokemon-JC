package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.data.PokemonUtils
import com.johndev.pokedexjc.data.PokemonUtils.formatId
import com.johndev.pokedexjc.model.entity.PokemonEntity
import com.johndev.pokedexjc.ui.theme.PokedexJCTheme

@Composable
fun PokemonBackLayer(
    pokemon: PokemonEntity
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        val (namePokemon, typePokemon, idPokemon, imgPokemon) = createRefs()
        Text(
            text = pokemon.name!!.replaceFirstChar(Char::titlecase),
            color = colorResource(id = R.color.secondaryTextColor),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.constrainAs(namePokemon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        /*ChipType(
            label = if (pokemon.types == null) {
                "Fire"
            } else {
                pokemon.types[0].type.name.replaceFirstChar(Char::titlecase)
            },
            Modifier.constrainAs(typePokemon) {
                top.linkTo(namePokemon.bottom)
                start.linkTo(parent.start)
            },
            color = Color.Transparent
        )*/
        AsyncImage(
            model = PokemonUtils.getImagePokemon(pokemon.id),
            contentDescription = null,
            modifier = Modifier.constrainAs(imgPokemon) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(namePokemon.bottom)
                width = Dimension.value(200.dp)
                height = Dimension.value(200.dp)
            }
        )
        Text(
            text = formatId(pokemon.id),
            color = colorResource(id = R.color.secondaryTextColor),
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(idPokemon) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )
    }
}

@Preview
@Composable
private fun PokemonDetailsPreview() {

    PokedexJCTheme() {
        Surface(Modifier.fillMaxSize()) {

        }
    }
}