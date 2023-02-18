package com.johndev.pokedexjc.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.johndev.pokedexjc.R
import com.johndev.pokedexjc.data.getOnboardingMenu
import com.johndev.pokedexjc.model.Onboarding
import com.johndev.pokedexjc.model.mapTypeToColorString
import com.johndev.pokedexjc.ui.components.RotatingPokeBall
import com.johndev.pokedexjc.ui.theme.Bug
import com.johndev.pokedexjc.ui.theme.PokedexJCTheme

@Composable
fun OnboardingModelScreen(
    menu: Onboarding,
    bgColor: Color,
    onClickNext: () -> Unit,
    onClickPrev: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            RotatingPokeBall(
                Modifier
                    .align(Alignment.Center)
                    //.statusBarsPadding()
                    //.padding(top = 16.dp)
                    .size(300.dp)
                //.graphicsLayer { alpha = textAlphaTarget }
            )
            Image(
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = menu.imgRes),
                contentDescription = null
            )
        }
        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = menu.titleRes),
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                    Text(
                        text = stringResource(id = menu.descriptionRes),
                        textAlign = TextAlign.Justify
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { onClickPrev() }) {
                        Text(
                            text = "Prev",
                            color = colorResource(id = R.color.secondaryColor)
                        )
                    }
                    FloatingActionButton(
                        backgroundColor = mapTypeToColorString(menu.colorSelected),
                        onClick = { onClickNext() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_forward),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PokeDexCardPreview() {
    val menuOnboarding = getOnboardingMenu()
    PokedexJCTheme() {
        Surface {
            Column(
                Modifier.fillMaxSize(),
            ) {
                OnboardingModelScreen(
                    menu = menuOnboarding[0],
                    bgColor = Bug,
                    onClickNext = {  }
                ) {

                }
            }
        }
    }
}