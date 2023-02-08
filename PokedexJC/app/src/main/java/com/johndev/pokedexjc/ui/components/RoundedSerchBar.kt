package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.Image
import com.johndev.pokedexjc.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.ui.theme.PokedexJCTheme

@Composable
fun RoundedSearchBar() {
    Surface(
        modifier = Modifier.padding(bottom = 24.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(colorResource(id = R.color.primaryColor)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_search),
                contentDescription = null,
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .padding(start = 16.dp)
            )
            Text(
                text = "Search Pokemon, Move, Ability, etc.",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun RoundedSearchBarPreview() {
    PokedexJCTheme {
        RoundedSearchBar()
    }
}