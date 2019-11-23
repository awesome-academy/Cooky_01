package com.example.cooky.data.local.model.nutition

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "nutrition")
data class Nutrition(
    @PrimaryKey
    var nutritionId: Int = 0,
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
