package com.example.cooky.data.local.model.search

import com.example.cooky.data.remote.api.*

data class SearchOption(
    var query: String = EMPTY_STRING,
    var cuisine: String = EMPTY_STRING,
    var diet: String = EMPTY_STRING,
    var includeIngredients: String = EMPTY_STRING,
    var excludeIngredients: String = EMPTY_STRING,
    var intolerances: String = EMPTY_STRING,
    var number: Int = DEFAULT_NUMBER,
    var readyTime: Int = DEFAULT_READY_TIME,
    var sort: String = SORT_RANDOM,
    var sortDirection: String = DIRECTION_DESC
)
