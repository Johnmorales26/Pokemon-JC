package com.johndev.pokedex.common.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.johndev.pokedex.R
import com.johndev.pokedex.ui.theme.*

sealed class PokemonType(
    val pokemonType: String,
    val colorType: Color,
    val colorDarkType: Color,
    val colorLightType: Color,
    @DrawableRes val iconType: Int
) {

    object BugType : PokemonType(
        pokemonType = "Bug",
        colorType = bugColor,
        colorDarkType = bugDarkColor,
        colorLightType = bugLightColor,
        iconType = R.drawable.bug
    )

    object DarkType : PokemonType(
        pokemonType = "Dark",
        colorType = darkColor,
        colorDarkType = darkDarkColor,
        colorLightType = darkLightColor,
        iconType = R.drawable.dark
    )

    object DragonType : PokemonType(
        pokemonType = "Dragon",
        colorType = dragonColor,
        colorDarkType = dragonDarkColor,
        colorLightType = dragonLightColor,
        iconType = R.drawable.dark
    )

    object ElectricType : PokemonType(
        pokemonType = "Electric",
        colorType = electricColor,
        colorDarkType = electricDarkColor,
        colorLightType = electricLightColor,
        iconType = R.drawable.electric
    )

    object FairyType : PokemonType(
        pokemonType = "Fairy",
        colorType = fairyColor,
        colorDarkType = fairyDarkColor,
        colorLightType = fairyLightColor,
        iconType = R.drawable.fairy
    )

    object FightingType : PokemonType(
        pokemonType = "Fighting",
        colorType = fightingColor,
        colorDarkType = fightingDarkColor,
        colorLightType = fightingLightColor,
        iconType = R.drawable.fighting
    )

    object FireType : PokemonType(
        pokemonType = "Fire",
        colorType = fireColor,
        colorDarkType = fireDarkColor,
        colorLightType = fireLightColor,
        iconType = R.drawable.fire
    )

    object FlyingType : PokemonType(
        pokemonType = "Flying",
        colorType = flyingColor,
        colorDarkType = flyingDarkColor,
        colorLightType = flyingLightColor,
        iconType = R.drawable.flying
    )

    object GhostType : PokemonType(
        pokemonType = "Ghost",
        colorType = ghostColor,
        colorDarkType = ghostDarkColor,
        colorLightType = ghostLightColor,
        iconType = R.drawable.ghost
    )

    object NormalType : PokemonType(
        pokemonType = "Normal",
        colorType = normalColor,
        colorDarkType = normalDarkColor,
        colorLightType = normalLightColor,
        iconType = R.drawable.normal
    )

    object GrassType : PokemonType(
        pokemonType = "Grass",
        colorType = grassColor,
        colorDarkType = grassDarkColor,
        colorLightType = grassLightColor,
        iconType = R.drawable.grass
    )

    object GroundType : PokemonType(
        pokemonType = "Ground",
        colorType = groundColor,
        colorDarkType = groundDarkColor,
        colorLightType = groundLightColor,
        iconType = R.drawable.ground
    )

    object IceType : PokemonType(
        pokemonType = "Ice",
        colorType = iceColor,
        colorDarkType = iceDarkColor,
        colorLightType = iceLightColor,
        iconType = R.drawable.ice
    )

    object PoisonType : PokemonType(
        pokemonType = "Poison",
        colorType = poisonColor,
        colorDarkType = poisonDarkColor,
        colorLightType = poisonLightColor,
        iconType = R.drawable.poison
    )

    object PsychicType : PokemonType(
        pokemonType = "Psychic",
        colorType = psychicColor,
        colorDarkType = psychicDarkColor,
        colorLightType = psychicLightColor,
        iconType = R.drawable.psychic
    )

    object RockType : PokemonType(
        pokemonType = "Rock",
        colorType = rockColor,
        colorDarkType = rockDarkColor,
        colorLightType = rockLightColor,
        iconType = R.drawable.rock
    )

    object SteelType : PokemonType(
        pokemonType = "Steel",
        colorType = steelColor,
        colorDarkType = steelDarkColor,
        colorLightType = steelLightColor,
        iconType = R.drawable.steel
    )

    object WaterType : PokemonType(
        pokemonType = "Water",
        colorType = waterColor,
        colorDarkType = waterDarkColor,
        colorLightType = waterLightColor,
        iconType = R.drawable.water
    )

    companion object {
        fun fromType(type: String): PokemonType {
            return when {
                type.contains("Bug") -> BugType
                type.contains("Dark") -> DarkType
                type.contains("Dragon") -> DragonType
                type.contains("Electric") -> ElectricType
                type.contains("Fairy") -> FairyType
                type.contains("Fighting") -> FightingType
                type.contains("Fire") -> FireType
                type.contains("Flying") -> FlyingType
                type.contains("Ghost") -> GhostType
                type.contains("Normal") -> NormalType
                type.contains("Grass") -> GrassType
                type.contains("Ground") -> GroundType
                type.contains("Ice") -> IceType
                type.contains("Poison") -> PoisonType
                type.contains("Psychic") -> PsychicType
                type.contains("Rock") -> RockType
                type.contains("Steel") -> SteelType
                type.contains("Water") -> WaterType
                else -> {
                    NormalType
                }
            }
        }
    }

}