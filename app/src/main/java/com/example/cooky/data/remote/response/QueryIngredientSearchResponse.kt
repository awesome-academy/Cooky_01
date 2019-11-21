package com.example.cooky.data.remote.response

import com.example.cooky.data.local.model.autocomplete.QueryIngredientSearch
import com.google.gson.annotations.SerializedName

data class QueryIngredientSearchResponse(
    @SerializedName("querySearchs")
    val querySearchs: List<QueryIngredientSearch>
)
