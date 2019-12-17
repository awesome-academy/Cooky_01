package com.example.cooky.ui.search.bynutrients

import androidx.lifecycle.viewModelScope
import com.example.cooky.base.BaseLoadMoreViewModel
import com.example.cooky.base.Status
import com.example.cooky.data.local.model.nutition.NutrientOption
import com.example.cooky.data.local.model.search.BasicSearchOption
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.data.repository.IntroRepository
import kotlinx.coroutines.launch

class NutrientsSearchViewModel(private val repo: IntroRepository) :
    BaseLoadMoreViewModel<IntroRecipe>() {

    private var searchOption = BasicSearchOption()
    private var nutrientOption = NutrientOption()

    override fun loadData() {
        viewModelScope.launch {
            val response = repo.searchRecipesByNutrients(
                searchOption,
                nutrientOption
            )
            if (response.status == Status.SUCCESS) {
                onLoadSuccess(response.data?.introRecipes)
            } else {
                response.message?.let(::setMessage)
            }
            setCloseLoading()
        }
    }

    fun searchRecipe(searchOption: BasicSearchOption, nutrientOption: NutrientOption) {
        setOnLoading()
        this.searchOption = searchOption
        this.nutrientOption = nutrientOption
        clearData()
        loadData()
    }
}
