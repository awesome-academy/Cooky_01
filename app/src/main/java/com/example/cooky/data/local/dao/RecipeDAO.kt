package com.example.cooky.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cooky.data.local.model.recipe.Recipe

@Dao
interface RecipeDAO {
    @Query("SELECT * FROM recipe")
    suspend fun getRecipeList(): List<Recipe>

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    suspend fun getRecipeById(id: Int): Recipe

    @Query("DELETE FROM recipe WHERE recipeId = :id")
    suspend fun deleteRecipeById(id: Int)

    @Query("SELECT recipeId FROM recipe")
    suspend fun getAllRecipeIds(): List<Int>
}
