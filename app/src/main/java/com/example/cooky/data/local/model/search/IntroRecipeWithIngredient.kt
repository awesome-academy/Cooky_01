package com.example.cooky.data.local.model.search

import com.google.gson.annotations.SerializedName

data class IntroRecipeWithIngredient(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("missedIngredientCount")
    val missedIngredientCount: Int,
    @SerializedName("missedIngredients")
    val missedIngredients: List<MissedIngredient>,
    @SerializedName("title")
    val title: String
)
