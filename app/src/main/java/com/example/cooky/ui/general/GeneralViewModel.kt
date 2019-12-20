package com.example.cooky.ui.general

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooky.base.BaseViewModel
import com.example.cooky.base.Status
import com.example.cooky.data.local.model.autocomplete.QueryRecipeSearch
import com.example.cooky.data.local.model.search.BasicSearchOption
import com.example.cooky.data.repository.IntroRepository
import kotlinx.coroutines.launch

class GeneralViewModel(private val repo: IntroRepository) : BaseViewModel() {
    private val _searchOption = MutableLiveData<BasicSearchOption>()
    val searchOption: LiveData<BasicSearchOption> get() = _searchOption

    private val _autoCompleteQuerys = MutableLiveData<List<QueryRecipeSearch>>()
    val autoCompleteQuerys: LiveData<List<QueryRecipeSearch>> get() = _autoCompleteQuerys

    fun searchRecipe(option: BasicSearchOption) {
        _searchOption.value = option
    }

    fun getAutoCompleteQuerys(query: String) {
        viewModelScope.launch {
            val response = repo.getAutocompleteRecipeSearch(query, NUMBER_AUTOCOMPLETE_QUERY)
            if (response.status == Status.SUCCESS) {
                _autoCompleteQuerys.value = response.data
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    companion object {
        private const val NUMBER_AUTOCOMPLETE_QUERY = 20
    }
}
