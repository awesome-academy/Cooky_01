package com.example.cooky.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooky.base.BaseLoadMoreViewModel
import com.example.cooky.base.Status
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.repository.IntroRepository
import com.example.cooky.util.STRING_COMMA
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repo: IntroRepository) : BaseLoadMoreViewModel<Recipe>() {
    private val _recentlyRecipe = MutableLiveData<List<Recipe>>()
    val recentlyRecipe: LiveData<List<Recipe>> get() = _recentlyRecipe

    override fun loadData() {
    }

    fun getRecentlyRecipes(ids: List<Int>) {
        val queryIds = ids.joinToString(STRING_COMMA)
        viewModelScope.launch {
            val response = repo.getManyRecipeByIds(queryIds)
            if (response.status == Status.SUCCESS) {
                _recentlyRecipe.value = response.data
            } else {
                response.message?.let(::setMessage)
            }
        }
    }
}
