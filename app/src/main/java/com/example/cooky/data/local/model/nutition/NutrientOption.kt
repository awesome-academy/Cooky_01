package com.example.cooky.data.local.model.nutition

import android.os.Parcelable
import com.example.cooky.data.remote.api.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NutrientOption(
    var minCarb: Int = DEFAULT_MIN_NUTRIENT,
    var maxCarb: Int = DEFAULT_MAX_NUTRIENT,
    var minProtein: Int = DEFAULT_MIN_NUTRIENT,
    var maxProtein: Int = DEFAULT_MAX_NUTRIENT,
    var minCalories: Int = DEFAULT_MIN_CALO,
    var maxCalories: Int = DEFAULT_MAX_CALO,
    var minFat: Int = DEFAULT_MIN_NUTRIENT,
    var maxFat: Int = DEFAULT_MAX_NUTRIENT,
    var minCalcium: Int = DEFAULT_MIN_NUTRIENT,
    var maxCalcium: Int = DEFAULT_MAX_NUTRIENT,
    var minCholesterol: Int = DEFAULT_MIN_NUTRIENT,
    var maxCholesterol: Int = DEFAULT_MAX_NUTRIENT,
    var minSugar: Int = DEFAULT_MIN_NUTRIENT,
    var maxSugar: Int = DEFAULT_MAX_NUTRIENT,
    var minVitaminA: Int = DEFAULT_MIN_NUTRIENT,
    var maxVitaminA: Int = DEFAULT_MAX_NUTRIENT,
    var minVitaminC: Int = DEFAULT_MIN_NUTRIENT,
    var maxVitaminC: Int = DEFAULT_MAX_NUTRIENT,
    var minVitaminD: Int = DEFAULT_MIN_NUTRIENT,
    var maxVitaminD: Int = DEFAULT_MAX_NUTRIENT,
    var minVitaminE: Int = DEFAULT_MIN_NUTRIENT,
    var maxVitaminE: Int = DEFAULT_MAX_NUTRIENT,
    var minVitaminK: Int = DEFAULT_MIN_NUTRIENT,
    var maxVitaminK: Int = DEFAULT_MAX_NUTRIENT
) : Parcelable
