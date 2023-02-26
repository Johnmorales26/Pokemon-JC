package com.johndev.pokedexjc.model.dataAbilities

data class EffectChange(
    val effect_entries: List<EffectEntry>,
    val version_group: VersionGroup
)