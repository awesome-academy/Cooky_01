package com.example.cooky.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooky.base.BaseViewModel
import com.example.cooky.base.Status
import com.example.cooky.data.local.model.nutition.Nutrition
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.repository.InfoRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val repo: InfoRepository) : BaseViewModel() {

    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe> get() = _recipe

    private val _isFavorite = MutableLiveData<Boolean>().apply { value = false }
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _nutrition = MutableLiveData<Nutrition>()
    val nutrition: LiveData<Nutrition> get() = _nutrition

    private fun getRecipe(id: Int) {
        viewModelScope.launch {
            val response = repo.getRecipesInformation(id)
            if (response.status == Status.SUCCESS) {
                _recipe.value = response.data
                setCloseLoading()
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    private fun getNutrition(id: Int) {
        viewModelScope.launch {
            val response = repo.getRecipeNutrition(id)
            if (response.status == Status.SUCCESS) {
                _nutrition.value = response.data
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    fun loadData(idRecipe: Int) {
        setOnLoading()
        getRecipe(idRecipe)
        getNutrition(idRecipe)
    }

    fun onCheckedFavorite() {
        _isFavorite.value = _isFavorite.value?.not()
    }
}
