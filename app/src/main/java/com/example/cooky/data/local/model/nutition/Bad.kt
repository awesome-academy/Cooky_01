package com.example.cooky.data.local.model.nutition

import com.google.gson.annotations.SerializedName

data class Bad(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("percentOfDailyNeeds")
    val percentOfDailyNeeds: Double,
    @SerializedName("title")
    val title: String
)
