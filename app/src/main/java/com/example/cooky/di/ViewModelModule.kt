package com.example.cooky.di

import com.example.cooky.ui.favorite.FavoriteViewModel
import com.example.cooky.ui.general.GeneralViewModel
import com.example.cooky.ui.general.content.GeneralContentViewModel
import com.example.cooky.ui.general.discover.DiscoverViewModel
import com.example.cooky.ui.recipe.RecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FavoriteViewModel() }
    viewModel { RecipeViewModel() }
    viewModel { GeneralViewModel() }
    viewModel { GeneralContentViewModel(get()) }
    viewModel { DiscoverViewModel() }
}
