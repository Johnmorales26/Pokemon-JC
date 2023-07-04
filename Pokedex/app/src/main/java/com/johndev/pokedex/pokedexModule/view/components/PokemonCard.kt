package com.johndev.pokedex.pokedexModule.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.johndev.pokedex.R
import com.johndev.pokedex.common.entities.PokemonEntity
import com.johndev.pokedex.common.utils.PokedexUtils.capitalize
import com.johndev.pokedex.common.utils.PokedexUtils.formatNumberId
import com.johndev.pokedex.common.utils.PokedexUtils.getImageUrl
import com.johndev.pokedex.common.utils.PokemonType.Companion.fromType

@Composable
fun PokemonCard(pokemonEntity: PokemonEntity, onClick: (PokemonEntity) -> Unit) {
    val type = pokemonEntity.types[0].type.name.capitalize()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.home_pokemon_card_height))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.common_padding_default)))
            .background(fromType(type).colorLightType.copy(alpha = 0.9f))
            .clickable { onClick(pokemonEntity) }
    ) {
        Content(type = type, pokemonEntity = pokemonEntity)
    }
}

@Composable
private fun Content(type: String, pokemonEntity: PokemonEntity) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        DataPokemon(Modifier.weight(2f), pokemonEntity = pokemonEntity)
        ImagePokemon(modifier = Modifier.weight(1f), idPokemon = pokemonEntity.id)
    }
}

@Composable
private fun DataPokemon(modifier: Modifier, pokemonEntity: PokemonEntity) {
    Box(modifier = modifier) {
        Icon(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.home_pokemon_icon_rectangle_small_size))
                .align(Alignment.TopCenter),
            painter = painterResource(id = R.drawable.rectangle_small),
            tint = Color(0x7ff3f1f5),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.common_padding_default)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = formatNumberId(pokemonEntity.id),
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.common_padding_min)))
            Text(
                text = pokemonEntity.name.capitalize(),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.common_padding_min)))
            LazyRow(content = {
                items(pokemonEntity.types.size) {
                    ChipType(type = pokemonEntity.types[it].type.name.capitalize())
                }
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChipType(type: String) {
    AssistChip(
        enabled = false,
        onClick = { },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.home_pokemon_icon_chip_size)),
                tint = fromType(type).colorDarkType,
                painter = painterResource(id = fromType(type).iconType),
                contentDescription = null
            )
        },
        label = {
            Text(
                text = type,
                color = fromType(type).colorDarkType
            )
        }
    )
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.common_padding_nano)))
}

@Composable
private fun ImagePokemon(modifier: Modifier, idPokemon: Int) {
    Box(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.home_pokemon_icon_pokeball_size)),
            painter = painterResource(id = R.drawable.pokeball),
            tint = Color(0x7ff3f1f5),
            contentDescription = null
        )
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.common_padding_nano)),
            model = getImageUrl(idPokemon),
            contentScale = ContentScale.Fit,
            contentDescription = null,
        )
    }
}
