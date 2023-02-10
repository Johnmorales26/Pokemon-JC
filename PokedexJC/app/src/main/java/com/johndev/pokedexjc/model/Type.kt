package des.c5inco.pokedexer.model

import androidx.compose.ui.graphics.Color
import com.johndev.pokedexjc.ui.theme.*

enum class Type {
    Normal,
    Fire,
    Fighting,
    Water,
    Flying,
    Grass,
    Poison,
    Electric,
    Ground,
    Psychic,
    Rock,
    Ice,
    Bug,
    Dragon,
    Ghost,
    Dark,
    Steel,
    Fairy,
}

fun mapTypeToColor(type: String): Color {
    return when (Type.valueOf(type)) {
        Type.Grass -> LightTeal
        Type.Bug -> Bug
        Type.Fire -> Fire
        Type.Flying -> Flying
        Type.Water -> Blue
        Type.Ice -> Ice
        Type.Dragon -> Dragon
        Type.Normal -> Normal
        Type.Fighting -> Fighting
        Type.Electric -> Electric
        Type.Psychic -> Psychic
        Type.Poison -> Poison
        Type.Ghost -> LightPurple
        Type.Ground, Type.Rock -> LightBrown
        Type.Dark -> Dark
        else -> return LightBlue
    }
}

fun mapTypeToColorString(type: String): Color {
    return when (type) {
        "Grass" -> LightTeal
        "Bug" -> Bug
        "Fire" -> Fire
        "Flying" -> Flying
        "Water" -> Blue
        "Ice" -> Ice
        "Dragon" -> Dragon
        "Normal" -> Normal
        "Fighting" -> Fighting
        "Electric" -> Electric
        "Psychic" -> Psychic
        "Poison" -> Poison
        "Ghost" -> LightPurple
        "Ground", "Rock" -> LightBrown
        "Dark" -> Dark
        else -> return LightBlue
    }
}