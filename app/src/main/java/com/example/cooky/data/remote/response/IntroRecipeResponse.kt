package com.example.cooky.data.remote.response

import com.example.cooky.data.local.model.search.IntroRecipe
import com.google.gson.annotations.SerializedName

data class IntroRecipeResponse(
    @SerializedName("baseUri")
    val baseUri: String,
    @SerializedName("expires")
    val expires: Long,
    @SerializedName("number")
    val number: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("processingTimeMs")
    val processingTimeMs: Int,
    @SerializedName("results")
    val introRecipes: List<IntroRecipe>,
    @SerializedName("totalResults")
    val totalResults: Int
)
