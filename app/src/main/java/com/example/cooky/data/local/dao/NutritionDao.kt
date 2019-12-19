package com.example.cooky.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cooky.data.local.model.nutition.Nutrition

@Dao
interface NutritionDao {
    @Query("SELECT * FROM nutrition")
    suspend fun getNutritionList(): List<Nutrition>

    @Delete
    suspend fun deleteNutrition(nutrition: Nutrition)

    @Insert
    suspend fun insertNutrition(nutrition: Nutrition)

    @Query("SELECT * FROM nutrition WHERE nutritionId = :id")
    suspend fun getNutritionById(id: Int): Nutrition?

    @Query("DELETE FROM nutrition WHERE nutritionId = :id")
    suspend fun deleteNutritionById(id: Int)

    @Query("SELECT nutritionId FROM nutrition")
    suspend fun getAllNutritionIds(): List<Int>
}
