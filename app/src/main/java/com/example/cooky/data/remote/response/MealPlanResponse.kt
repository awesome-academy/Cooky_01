package com.example.cooky.data.remote.response

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cooky.data.local.TABLE_MEAL_PLAN
import com.example.cooky.data.local.model.nutition.Nutrient
import com.example.cooky.data.local.model.search.IntroRecipe
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_MEAL_PLAN)
data class MealPlanResponse(
    @PrimaryKey(autoGenerate = true)
    var meanPlanId: Int = 0,
    @SerializedName("meals")
    val introRecipes: List<IntroRecipe>,
    @SerializedName("nutrients")
    @Embedded
    val nutrient: Nutrient
)
