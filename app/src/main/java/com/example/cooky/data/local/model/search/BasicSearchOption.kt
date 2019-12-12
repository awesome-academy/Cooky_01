package com.example.cooky.data.local.model.search

import com.example.cooky.data.remote.api.*

data class BasicSearchOption(
    var query: String = EMPTY_STRING,
    var cuisine: String = EMPTY_STRING,
    var diet: String = EMPTY_STRING,
    var Intolerances: String = EMPTY_STRING,
    var type: String = EMPTY_STRING,
    var number: Int = DEFAULT_NUMBER,
    var isInstructionRequired: Boolean = true,
    var sort: String = EMPTY_STRING,
    var sortDirection: String = EMPTY_STRING
)
