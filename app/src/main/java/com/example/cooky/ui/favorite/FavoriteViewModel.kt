package com.example.cooky.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooky.base.BaseLoadMoreViewModel
import com.example.cooky.base.Status
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.repository.InfoRepository
import com.example.cooky.data.repository.IntroRepository
import com.example.cooky.util.NUMBER_PER_LOADING
import com.example.cooky.util.STRING_COMMA
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val introRepo: IntroRepository,
    private val infoRepo: InfoRepository
) : BaseLoadMoreViewModel<Recipe>() {

    private val _recentlyRecipe = MutableLiveData<List<Recipe>>()
    val recentlyRecipe: LiveData<List<Recipe>> get() = _recentlyRecipe
    private var currentLocalRecipeCount = 0
    private var position = 0
    override fun loadData() {

        if (position < currentLocalRecipeCount) {
            setOnLoading()
            viewModelScope.launch {
                val recipes = infoRepo.getRecipesByRange(position, NUMBER_PER_LOADING)
                onLoadSuccess(recipes)
                setCloseLoading()
                position += NUMBER_PER_LOADING
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
        position = 0
    }

    fun getRecentlyRecipes(ids: List<Int>) {
        val queryIds = ids.joinToString(STRING_COMMA)
        viewModelScope.launch {
            val response = introRepo.getManyRecipeByIds(queryIds)
            if (response.status == Status.SUCCESS) {
                _recentlyRecipe.value = response.data
            } else {
                response.message?.let(::setMessage)
            }
        }
    }
}
