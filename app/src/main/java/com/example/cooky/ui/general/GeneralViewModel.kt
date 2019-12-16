package com.example.cooky.ui.general

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cooky.base.BaseViewModel
import com.example.cooky.data.local.model.search.BasicSearchOption

class GeneralViewModel : BaseViewModel() {
    private val _searchOption = MutableLiveData<BasicSearchOption>()
    val searchOption: LiveData<BasicSearchOption> get() = _searchOption

    fun searchRecipe(option: BasicSearchOption) {
        _searchOption.value = option
    }
}
