package com.example.cooky.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooky.base.BaseLoadMoreViewModel
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.repository.InfoRepository
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.data.repository.IntroRepository
import com.example.cooky.util.NUMBER_PER_LOADING
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val introRepo: IntroRepository,
    private val infoRepo: InfoRepository
) : BaseLoadMoreViewModel<Recipe>() {

    private val _recentlyIntro = MutableLiveData<List<IntroRecipe>>()
    val recentlyIntro: LiveData<List<IntroRecipe>> get() = _recentlyIntro

    private var currentLocalRecipeCount = 0
    private var localRecipePosition = 0
    override fun loadData() {

        if (localRecipePosition < currentLocalRecipeCount) {
            setOnLoading()
            viewModelScope.launch {
                val recipes = infoRepo.getRecipesByRange(localRecipePosition, NUMBER_PER_LOADING)
                onLoadSuccess(recipes)
                setCloseLoading()
                localRecipePosition += NUMBER_PER_LOADING
            }
        }
    }

    fun getAllLocalRecipes() {
        viewModelScope.launch {
            val localRecipeCount = infoRepo.getRecipeCount()
            if (currentLocalRecipeCount != localRecipeCount) {
                currentLocalRecipeCount = localRecipeCount
                reset()
                loadData()
            }
        }
    }

    private fun reset() {
        clearData()
        localRecipePosition = 0
    }

    fun getRecentlyIntro(){
        viewModelScope.launch {
            val response = introRepo.getAllIntroRecipes()
            _recentlyIntro.value = response
        }
    }
}
