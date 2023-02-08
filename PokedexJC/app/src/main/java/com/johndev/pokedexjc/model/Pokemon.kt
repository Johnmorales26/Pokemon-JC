package des.c5inco.pokedexer.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import com.johndev.pokedexjc.ui.theme.LightBlue

data class Pokemon(
    val id: Int,
    val name: String,
    val description: String,
    val typeOfPokemon: String,
    val category: String,
    val image: String,
    val height: Double,
    val weight: Double,
    val genderRate: Int = -1,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val evolutionChain: List<Evolution> = listOf(),
)

fun pokemonColor(type: String): Color {
    return mapTypeToColor(type.replaceFirstChar(Char::titlecase))
}