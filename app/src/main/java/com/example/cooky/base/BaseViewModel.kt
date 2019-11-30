package com.example.cooky.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    fun setMessage(message: String) {
        _message.value = message
    }

    fun setOnLoading() {
        _isLoading.value = true
    }

    fun setCloseLoading() {
        _isLoading.value = false
    }
}
