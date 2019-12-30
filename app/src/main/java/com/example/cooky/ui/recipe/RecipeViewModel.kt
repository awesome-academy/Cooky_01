package com.example.cooky.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooky.base.BaseViewModel
import com.example.cooky.base.Status
import com.example.cooky.data.local.model.nutition.Nutrition
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.data.repository.InfoRepository
import com.example.cooky.util.DEFAULT_RECIPE_ID
import com.example.cooky.util.RECENTLY_RECIPE_SIZE
import kotlinx.coroutines.launch

class RecipeViewModel(private val repo: InfoRepository) : BaseViewModel() {

    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe> get() = _recipe

    private var isOnLoadingDatabase = false

    private var currentRecipeId = DEFAULT_RECIPE_ID

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _nutrition = MutableLiveData<Nutrition>()
    val nutrition: LiveData<Nutrition> get() = _nutrition

    private val recentlyRecipes = mutableListOf<IntroRecipe>()

    private fun getRecipe(id: Int) {
        viewModelScope.launch {
            val response = repo.getRecipesInformation(id)
            if (response.status == Status.SUCCESS) {
                response.data?.let {
                    _recipe.value = it
                    setCloseLoading()
                    val introRecipe = IntroRecipe(it.recipeId, it.image, it.title)
                    handleSaveRecentlyRecipe(introRecipe)
                }
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    private fun handleSaveRecentlyRecipe(introRecipe: IntroRecipe) {
        if (recentlyRecipes.contains(introRecipe)) {
            recentlyRecipes.remove(introRecipe)
        } else if (recentlyRecipes.size == RECENTLY_RECIPE_SIZE) {
            recentlyRecipes.removeAt(RECENTLY_RECIPE_SIZE - 1)
        }
        recentlyRecipes.add(0, introRecipe)
        viewModelScope.launch {
            repo.deleteAllIntroRecipes()
            repo.addIntroRecipes(recentlyRecipes)
        }
    }

    private fun getNutrition(id: Int) {
        viewModelScope.launch {
            val response = repo.getRecipeNutrition(id)
            if (response.status == Status.SUCCESS) {
                val nutritionResponse = response.data
                nutritionResponse?.nutritionId = id
                _nutrition.value = nutritionResponse
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    fun loadData(id: Int) {
        setOnLoading()
        currentRecipeId = id
        viewModelScope.launch {
            recentlyRecipes.addAll(repo.getAllIntroRecipes())
            val localRecipe = repo.getRecipeById(id)
            val localNutrition = repo.getNutritionById(id)
            setCloseLoading()
            if (localRecipe != null && localNutrition != null) {
                _recipe.value = localRecipe
                _nutrition.value = localNutrition
                _isFavorite.value = true
                localRecipe.let {
                    val introRecipe = IntroRecipe(it.recipeId, it.image, it.title)
                    handleSaveRecentlyRecipe(introRecipe)
                }
            } else {
                _isFavorite.value = false
                getRecipe(id)
                getNutrition(id)
                setOnLoading()
            }
        }
    }

    fun handleSaveFavorite() {
        if (!isOnLoadingDatabase) {
            isFavorite.value?.let {
                if (it)
                    removeFromFavorite()
                else
                    addToFavorite()
            }
        }
    }

    private fun addToFavorite() {
        if (!isOnLoadingDatabase) {
            _isFavorite.value = true
            isOnLoadingDatabase = true
            viewModelScope.launch {
                recipe.value?.let { repo.insertRecipe(it) }
                nutrition.value?.let { repo.insertNutrition(it) }
                isOnLoadingDatabase = false
            }
        }
    }

    private fun removeFromFavorite() {
        if (!isOnLoadingDatabase) {
            _isFavorite.value = false
            isOnLoadingDatabase = true
            viewModelScope.launch {
                repo.deleteRecipeById(currentRecipeId)
                repo.deleteNutritionByID(currentRecipeId)
                isOnLoadingDatabase = false
            }
        }
    }
}
