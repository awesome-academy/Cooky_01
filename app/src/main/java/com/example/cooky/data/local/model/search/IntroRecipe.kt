package com.example.cooky.data.local.model.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "introrecipe")
data class IntroRecipe(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @PrimaryKey
    @SerializedName("title")
    val title: String
)
