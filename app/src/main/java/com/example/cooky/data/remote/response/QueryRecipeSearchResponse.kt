package com.example.cooky.data.remote.response

import com.example.cooky.data.local.model.autocomplete.QueryRecipeSearch
import com.google.gson.annotations.SerializedName

data class QueryRecipeSearchResponse(
    @SerializedName("querySearchs")
    val querySearchs: List<QueryRecipeSearch>
)
