package com.example.cooky.data.repository

import com.example.cooky.base.BaseResponse
import com.example.cooky.data.local.model.autocomplete.QueryIngredientSearch
import com.example.cooky.data.local.model.autocomplete.QueryRecipeSearch
import com.example.cooky.data.local.model.nutition.Nutrition
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.remote.api.DEFAULT_NUMBER

interface InfoRepository {

    suspend fun getRecipesInformation(id: Int): BaseResponse<Recipe>

    suspend fun getRecipeNutrition(id: Int): BaseResponse<Nutrition>

    suspend fun getAutocompleteRecipeSearch(
        query: String,
        number: Int
    ): BaseResponse<List<QueryRecipeSearch>>

    suspend fun getAutoCompleteIngredientSearch(
        query: String,
        number: Int = DEFAULT_NUMBER
    ): BaseResponse<List<QueryIngredientSearch>>

    suspend fun insertNutrition(nutrition: Nutrition)

    suspend fun deleteNutritionByID(idNutrition: Int)

    suspend fun getNutritionById(idNutrition: Int): Nutrition?

    suspend fun getAllNutritions(): List<Nutrition>

    suspend fun getAllNutritionIds(): List<Int>

    suspend fun insertRecipe(recipe: Recipe)

    suspend fun deleteRecipeById(idRecipe: Int)

    suspend fun getRecipeById(idRecipe: Int): Recipe?

    suspend fun getAllRecipes(): List<Recipe>

    suspend fun getAllRecipeIds(): List<Int>
}
