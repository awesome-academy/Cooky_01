package com.example.cooky.data.local.model.nutition

import com.google.gson.annotations.SerializedName

data class Nutrition(
    @SerializedName("bad")
    val bad: List<Bad>,
    @SerializedName("calories")
    val calories: String,
    @SerializedName("carbs")
    val carbs: String,
    @SerializedName("expires")
    val expires: Long,
    @SerializedName("fat")
    val fat: String,
    @SerializedName("good")
    val good: List<Good>,
    @SerializedName("isStale")
    val isStale: Boolean,
    @SerializedName("protein")
    val protein: String
)
