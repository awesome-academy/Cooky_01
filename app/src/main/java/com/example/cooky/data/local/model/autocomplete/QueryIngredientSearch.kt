package com.example.cooky.data.local.model.autocomplete

import com.google.gson.annotations.SerializedName

data class QueryIngredientSearch(
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)
