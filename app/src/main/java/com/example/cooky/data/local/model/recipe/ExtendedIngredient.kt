package com.example.cooky.data.local.model.recipe

import com.google.gson.annotations.SerializedName

data class ExtendedIngredient(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("measures")
    val measures: Measures,
    @SerializedName("name")
    val name: String
)
