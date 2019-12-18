package com.example.cooky.ui.search.bynutrients

import android.os.Parcelable
import com.example.cooky.data.remote.api.DEFAULT_MAX_NUTRIENT
import com.example.cooky.data.remote.api.DEFAULT_MIN_NUTRIENT
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NutrientPicker(
    var title: String,
    var min: Int = DEFAULT_MIN_NUTRIENT,
    var max: Int = DEFAULT_MAX_NUTRIENT,
    var start: Int = DEFAULT_MIN_NUTRIENT,
    var end: Int = DEFAULT_MAX_NUTRIENT
) : Parcelable
