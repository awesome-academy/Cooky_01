package com.example.cooky.data.local.model.recipe

import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("number")
    val number: Int,
    @SerializedName("step")
    val step: String
)
