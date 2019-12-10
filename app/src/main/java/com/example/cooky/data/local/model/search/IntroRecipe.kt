package com.example.cooky.data.local.model.search

import com.google.gson.annotations.SerializedName

data class IntroRecipe(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String
)
