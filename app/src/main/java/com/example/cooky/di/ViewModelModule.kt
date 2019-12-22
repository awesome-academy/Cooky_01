package com.example.cooky.di

import com.example.cooky.ui.favorite.FavoriteViewModel
import com.example.cooky.ui.general.GeneralViewModel
import com.example.cooky.ui.general.content.GeneralContentViewModel
import com.example.cooky.ui.general.discover.DiscoverViewModel
import com.example.cooky.ui.recipe.RecipeViewModel
import com.example.cooky.ui.search.bynutrients.NutrientsSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FavoriteViewModel(get()) }
    viewModel { RecipeViewModel(get()) }
    viewModel { GeneralViewModel() }
    viewModel { GeneralContentViewModel(get()) }
    viewModel { DiscoverViewModel(get()) }
    viewModel { NutrientsSearchViewModel(get()) }
}
