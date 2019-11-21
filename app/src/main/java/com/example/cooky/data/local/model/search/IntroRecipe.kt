package com.example.cooky.data.local.model.search

import com.google.gson.annotations.SerializedName

data class IntroRecipe(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("imageUrls")
    val imageUrls: List<String>,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,
    @SerializedName("servings")
    val servings: Int,
    @SerializedName("title")
    val title: String
)
