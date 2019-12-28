package com.example.cooky.data.local.model.nutition

import com.google.gson.annotations.SerializedName

data class Nutrient(
    @SerializedName("calories")
    val calories: Float,
    @SerializedName("carbohydrates")
    val carbohydrates: Float,
    @SerializedName("fat")
    val fat: Float,
    @SerializedName("protein")
    val protein: Float
)
