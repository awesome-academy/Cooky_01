package com.example.cooky.data.remote.response

import com.example.cooky.data.local.model.search.IntroRecipe
import com.google.gson.annotations.SerializedName

data class IntroRecipeResponse(
    val number: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val introRecipes: List<IntroRecipe>,
    @SerializedName("totalResults")
    val totalResults: Int
)
