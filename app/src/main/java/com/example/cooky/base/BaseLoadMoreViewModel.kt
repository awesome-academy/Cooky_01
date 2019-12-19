package com.example.cooky.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseLoadMoreViewModel<Item> : BaseViewModel() {

    private val _listItem = MutableLiveData<MutableList<Item>>()
    val listItem: LiveData<MutableList<Item>> get() = _listItem

    private val _isLoadMore = MutableLiveData<Boolean>().apply { value = false }
    val isLoadMore: LiveData<Boolean> get() = _isLoadMore

    val onScrollListener = object : ScrollingEndNestedScrollView() {
        override val distanceToBottom: Int = RANGE_LOADMORE
        override fun loadMore() {
            if (isLoadMore.value == false) {
                _isLoadMore.value = true
                loadData()
            }
        }
    }

    abstract fun loadData()

    fun clearData() {
        _listItem.value = mutableListOf()
    }

    fun onLoadSuccess(items: List<Item>?) {
        viewModelScope.launch {
            val newList = listItem.value ?: mutableListOf()
            items?.let(newList::addAll)
            _listItem.value = newList
            _isLoadMore.value = false
        }
    }

    fun onLoadFailed(msg: String) {
        viewModelScope.launch {
            setMessage(msg)
            _isLoadMore.value = false
        }
    }

    companion object {
        private const val RANGE_LOADMORE = 500
    }
}
