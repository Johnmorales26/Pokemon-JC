package com.johndev.pokedexjc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.data.getMenuItem

@Composable
fun MenuMain(
    onOptionUnderDevelopment: (Int) -> Unit
) {
    val menuItems = getMenuItem()
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        content = {
            items(menuItems.size) {index ->
                val item = menuItems[index]
                MenuMainItemButton(
                    text = item.label,
                    color = item.color
                ) {
                    when(index) {
                        0 -> onOptionUnderDevelopment(0)
                        1 -> onOptionUnderDevelopment(1)
                        else -> onOptionUnderDevelopment(2)
                    }
                }
            }
        }
    )
}

@Composable
fun MenuMainItemButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        color = color,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(64.dp)
                .clickable { onClick() },
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = text,
                color = Color.White
            )
        }
    }
}