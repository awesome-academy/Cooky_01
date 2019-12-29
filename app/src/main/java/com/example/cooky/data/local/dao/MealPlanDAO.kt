package com.example.cooky.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cooky.data.local.TABLE_MEAL_PLAN
import com.example.cooky.data.remote.response.MealPlanResponse

@Dao
interface MealPlanDao {
    @Query("SELECT * FROM $TABLE_MEAL_PLAN")
    suspend fun getMealPlans(): List<MealPlanResponse>

    @Query("DELETE FROM $TABLE_MEAL_PLAN")
    suspend fun deleteAllMealPlan()

    @Insert
    suspend fun insertMealPlan(meanPlan: MealPlanResponse)
}
