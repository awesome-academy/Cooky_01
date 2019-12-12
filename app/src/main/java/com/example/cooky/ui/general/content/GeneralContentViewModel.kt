package com.example.cooky.ui.general.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooky.base.BaseLoadMoreViewModel
import com.example.cooky.base.Status
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.data.remote.response.IntroRecipeResponse
import com.example.cooky.data.repository.IntroRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class GeneralContentViewModel(private val repo: IntroRepository) : BaseLoadMoreViewModel<Recipe>() {
    private val _suggestRecipe = MutableLiveData<IntroRecipe>()
    val suggestRecipe: LiveData<IntroRecipe> get() = _suggestRecipe

    private val _mainRecipes = MutableLiveData<IntroRecipeResponse>()
    val mainRecipes: LiveData<IntroRecipeResponse> get() = _mainRecipes

    private val _dessertRecipes = MutableLiveData<IntroRecipeResponse>()
    val dessertRecipes: LiveData<IntroRecipeResponse> get() = _dessertRecipes

    private val _vietnameseRecipes = MutableLiveData<IntroRecipeResponse>()
    val vietnameseRecipes: LiveData<IntroRecipeResponse> get() = _vietnameseRecipes

    override fun loadData() {
        viewModelScope.launch {
            val response = repo.getRandomRecipes(number = 20)
            if (response.status == Status.SUCCESS) {
                onLoadSuccess(response.data?.recipes)
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    private fun getDessertRecipes() {
        viewModelScope.launch {
            val response = repo.searchRandomRecipes(
                type = TYPE_DESSERT,
                sort = SORT_RANDOM,
                sortDirection = DIRECTION_DESC,
                number = 10
            )
            if (response.status == Status.SUCCESS) {
                _dessertRecipes.value = response.data
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    private fun getMainRecipes() {
        setOnLoading()
        viewModelScope.launch {
            val response = repo.searchRandomRecipes(
                type = TYPE_MAIN,
                sort = SORT_RANDOM,
                sortDirection = DIRECTION_DESC,
                number = 10
            )
            if (response.status == Status.SUCCESS) {
                _mainRecipes.value = response.data
                val random = Random
                _suggestRecipe.value = response.data?.introRecipes?.get(random.nextInt(9))
                setCloseLoading()
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    private fun getVietNameseRecipes() {
        viewModelScope.launch {
            val response = repo.searchRandomRecipes(
                cuisine = CUISINE_VIETNAMESE,
                sort = SORT_RANDOM,
                sortDirection = DIRECTION_DESC,
                number = 10
            )
            if (response.status == Status.SUCCESS) {
                _vietnameseRecipes.value = response.data
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    fun firstLoadData() {
        getMainRecipes()
        getDessertRecipes()
        getVietNameseRecipes()
    }

    companion object {
        private const val CUISINE_VIETNAMESE = "vietnamese"
        private const val TYPE_DESSERT = "dessert"
        private const val TYPE_MAIN = "main course"
        private const val SORT_RANDOM = "random"
        private const val DIRECTION_DESC = "desc"
    }

}
