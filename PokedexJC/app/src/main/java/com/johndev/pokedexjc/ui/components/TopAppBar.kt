package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.ui.theme.PrimaryLightColor

@Composable
fun TopAppBarActivities(
    titleAppBar: String,
    isActionsVisible: Boolean = false,
    onNavigationClick: (Int) -> Unit
) {
    TopAppBar(
        backgroundColor = PrimaryLightColor,
        title = {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = titleAppBar,
                style = MaterialTheme.typography.h6
            )
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .padding(16.dp),
                onClick = { onNavigationClick(0) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null
                )
            }
        },
        actions = {
            if (isActionsVisible) {
                IconButton(
                    onClick = { onNavigationClick(1) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_stars),
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = { onNavigationClick(2) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check_circle),
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun TopAppBarExtra(
    titleRes: Int,
    onNavigationClick: () -> Unit,
    isCaptured: Boolean = false,
    percentText: String = "",
    onDeleteAll: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        if (isCaptured) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = percentText,
                style = MaterialTheme.typography.subtitle1
            )
        }
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = stringResource(id = titleRes),
            style = MaterialTheme.typography.h6
        )
        IconButton(onClick = { onNavigationClick() }) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_arrow_back
                ),
                contentDescription = null
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .padding(top = 8.dp, bottom = 8.dp)
        )
        IconButton(onClick = { onDeleteAll() }) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_delete
                ),
                contentDescription = null
            )
        }
    }
}