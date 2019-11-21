package com.example.cooky.data.local.model.autocomplete

import com.google.gson.annotations.SerializedName

data class QueryRecipeSearch(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)
