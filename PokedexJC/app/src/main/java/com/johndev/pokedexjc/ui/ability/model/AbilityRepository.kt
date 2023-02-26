package com.johndev.pokedexjc.ui.ability.model

import com.johndev.pokedexjc.data.Daos.AbilityDao
import com.johndev.pokedexjc.data.PokemonApplication
import com.johndev.pokedexjc.model.entity.AbilityEntity
import kotlinx.coroutines.flow.Flow


class AbilityRepository {

    private val dao: AbilityDao by lazy { PokemonApplication.database.abilityDao() }

    suspend fun insert(abilityEntity: AbilityEntity) = dao.insert(abilityEntity)

    fun getAll(): Flow<List<AbilityEntity>> = dao.getAll()

}