package com.example.cooky.data.local.model.search

import com.google.gson.annotations.SerializedName

data class IntroRecipeWithNutrient(
    @SerializedName("calories")
    val calories: Int,
    @SerializedName("carbs")
    val carbs: String,
    @SerializedName("fat")
    val fat: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("imageType")
    val imageType: String,
    @SerializedName("protein")
    val protein: String,
    @SerializedName("title")
    val title: String
)
