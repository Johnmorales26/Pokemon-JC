package com.johndev.pokedexjc.data

import com.johndev.pokedexjc.ui.ability.viewModel.AbilityViewModel
import com.johndev.pokedexjc.ui.items.viewModel.ItemViewModel
import com.johndev.pokedexjc.ui.moves.viewModel.MovesViewModel
import com.johndev.pokedexjc.ui.pokedex.viewModel.PokedexViewModel

object ViewModels {

    var pokemonViewModel: PokedexViewModel = PokedexViewModel()
    var movesViewModel: MovesViewModel = MovesViewModel()
    var itemViewModel: ItemViewModel = ItemViewModel()
    var abilityViewModel: AbilityViewModel = AbilityViewModel()

}