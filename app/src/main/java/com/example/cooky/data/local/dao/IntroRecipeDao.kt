package com.example.cooky.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cooky.data.local.model.search.IntroRecipe

@Dao
interface IntroRecipeDao {
    @Query("SELECT * FROM introrecipe")
    suspend fun getAllIntroRecipes(): List<IntroRecipe>

    @Query("DELETE FROM introrecipe")
    suspend fun deleteAllIntroRecipes()

    @Insert
    suspend fun addAllIntroRecipes(IntroRecipes: List<IntroRecipe>)
}
