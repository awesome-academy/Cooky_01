package com.example.cooky.data.remote.response

import com.example.cooky.data.local.model.recipe.Recipe
import com.google.gson.annotations.SerializedName

data class RandomRecipeResponse(
    @SerializedName("recipes")
    val recipes: List<Recipe>
)
