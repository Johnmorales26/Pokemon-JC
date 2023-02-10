package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.johndev.pokedexjc.R
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.ui.theme.PrimaryColor

@Composable
fun TopAppBarActivities(
    titleAppBar: String,
    onNavigationClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(PrimaryColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier
                .padding(16.dp),
            onClick = { onNavigationClick() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = titleAppBar,
            style = MaterialTheme.typography.h6
        )

    }
}