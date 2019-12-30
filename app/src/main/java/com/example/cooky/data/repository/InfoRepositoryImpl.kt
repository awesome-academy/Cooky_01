package com.example.cooky.data.repository

import com.example.cooky.base.BaseResponse
import com.example.cooky.data.local.dao.IntroRecipeDao
import com.example.cooky.data.local.dao.NutritionDao
import com.example.cooky.data.local.dao.RecipeDao
import com.example.cooky.data.local.model.autocomplete.QueryIngredientSearch
import com.example.cooky.data.local.model.autocomplete.QueryRecipeSearch
import com.example.cooky.data.local.model.nutition.Nutrition
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.data.remote.ResponseHandler
import com.example.cooky.data.remote.api.ApiService

class InfoRepositoryImpl(
    private val nutritionDao: NutritionDao,
    private val recipeDao: RecipeDao,
    private val apiService: ApiService,
    private val introDao: IntroRecipeDao,
    private val responseHandler: ResponseHandler
) : InfoRepository {
    override suspend fun getRecipesInformation(id: Int): BaseResponse<Recipe> =
        try {
            val response = apiService.getRecipesInformation(id)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }

    override suspend fun getRecipeNutrition(id: Int): BaseResponse<Nutrition> =
        try {
            val response = apiService.getRecipeNutrition(id)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }

    override suspend fun getAutocompleteRecipeSearch(
        query: String,
        number: Int
    ): BaseResponse<List<QueryRecipeSearch>> =
        try {
            val response = apiService.getAutocompleteRecipeSearch(query, number)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }

    override suspend fun getAutoCompleteIngredientSearch(
        query: String,
        number: Int
    ): BaseResponse<List<QueryIngredientSearch>> =
        try {
            val response = apiService.getAutoCompleteIngredientSearch(query, number)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }

    override suspend fun insertNutrition(nutrition: Nutrition) =
        nutritionDao.insertNutrition(nutrition)

    override suspend fun deleteNutritionByID(idNutrition: Int) =
        nutritionDao.deleteNutritionById(idNutrition)

    override suspend fun getNutritionById(idNutrition: Int) =
        nutritionDao.getNutritionById(idNutrition)

    override suspend fun getAllNutritions() = nutritionDao.getNutritionList()

    override suspend fun getAllNutritionIds() = nutritionDao.getAllNutritionIds()

    override suspend fun insertRecipe(recipe: Recipe) = recipeDao.insertRecipe(recipe)

    override suspend fun deleteRecipeById(idRecipe: Int) = recipeDao.deleteRecipeById(idRecipe)

    override suspend fun getRecipeById(idRecipe: Int) = recipeDao.getRecipeById(idRecipe)

    override suspend fun getAllRecipes() = recipeDao.getRecipeList()

    override suspend fun getAllRecipeIds() = recipeDao.getAllRecipeIds()

    override suspend fun getRecipesByRange(from: Int, to: Int) =
        recipeDao.getRecipeByRange(from, to)

    override suspend fun getRecipeCount() = recipeDao.getRecipeCount()

    override suspend fun getAllIntroRecipes(): List<IntroRecipe> = introDao.getAllIntroRecipes()

    override suspend fun addIntroRecipes(introRecipes: List<IntroRecipe>) =
        introDao.addAllIntroRecipes(introRecipes)

    override suspend fun deleteAllIntroRecipes() = introDao.deleteAllIntroRecipes()
}
