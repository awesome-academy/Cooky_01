package com.example.cooky.data.local.model.nutition

import com.google.gson.annotations.SerializedName

data class Good(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("indented")
    val indented: Boolean,
    @SerializedName("percentOfDailyNeeds")
    val percentOfDailyNeeds: Double,
    @SerializedName("title")
    val title: String
)
