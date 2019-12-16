package com.example.cooky.ui.general.discover

import androidx.lifecycle.viewModelScope
import com.example.cooky.base.BaseLoadMoreViewModel
import com.example.cooky.base.Status
import com.example.cooky.data.local.model.search.BasicSearchOption
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.data.repository.IntroRepository
import kotlinx.coroutines.launch

class DiscoverViewModel(private val repo: IntroRepository) : BaseLoadMoreViewModel<IntroRecipe>() {

    private var optionCurrent = BasicSearchOption()

    override fun loadData() = searchRecipe()

    fun startSearching(option: BasicSearchOption){
        optionCurrent = option
        setOnLoading()
        searchRecipe()
    }

    private fun searchRecipe() {
        viewModelScope.launch {
            val response = repo.searchRandomRecipes(
                query = optionCurrent.query,
                cuisine = optionCurrent.cuisine,
                diet = optionCurrent.diet,
                intolerances = optionCurrent.Intolerances,
                type = optionCurrent.type,
                sort = optionCurrent.sort,
                sortDirection = DIRECTION_DESC,
                number = NUMBER_RANDOM_QUERY
            )
            if (response.status == Status.SUCCESS) {
                onLoadSuccess(response.data?.introRecipes)
                setCloseLoading()
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    companion object {
        private const val DIRECTION_DESC = "desc"
        private const val NUMBER_RANDOM_QUERY = 20
    }
}
