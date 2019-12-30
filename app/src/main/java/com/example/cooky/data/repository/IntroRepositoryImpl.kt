package com.example.cooky.data.repository

import com.example.cooky.base.BaseResponse
import com.example.cooky.data.local.dao.IntroRecipeDao
import com.example.cooky.data.local.dao.MealPlanDao
import com.example.cooky.data.local.model.autocomplete.QueryIngredientSearch
import com.example.cooky.data.local.model.autocomplete.QueryRecipeSearch
import com.example.cooky.data.local.model.nutition.NutrientOption
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.local.model.search.BasicSearchOption
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.data.local.model.search.SearchOption
import com.example.cooky.data.remote.ResponseHandler
import com.example.cooky.data.remote.api.ApiService
import com.example.cooky.data.remote.response.IntroRecipeResponse
import com.example.cooky.data.remote.response.MealPlanResponse
import com.example.cooky.data.remote.response.RandomRecipeResponse
import java.lang.Exception

class IntroRepositoryImpl(
    private val apiService: ApiService,
    private val responseHandler: ResponseHandler,
    private val mealPlanDao: MealPlanDao,
    private val introDao: IntroRecipeDao
) : IntroRepository {
    override suspend fun searchRecipes(searchOption: SearchOption): BaseResponse<IntroRecipeResponse> =
        try {
            val response = apiService.searchRecipes(
                searchOption.query,
                searchOption.cuisine,
                searchOption.diet,
                searchOption.excludeIngredients,
                searchOption.intolerances,
                searchOption.number
            )
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }

    override suspend fun searchRecipesByNutrients(
        searchOption: BasicSearchOption,
        nutrientOption: NutrientOption
    ): BaseResponse<IntroRecipeResponse> =
        try {
            val response = apiService.searchRecipesByNutrients(
                searchOption.query,
                searchOption.cuisine,
                searchOption.type,
                searchOption.diet,
                searchOption.Intolerances,
                searchOption.number,
                searchOption.isInstructionRequired,
                searchOption.sort,
                searchOption.sortDirection,
                nutrientOption.minCarb,
                nutrientOption.maxCarb,
                nutrientOption.minProtein,
                nutrientOption.maxProtein,
                nutrientOption.minCalories,
                nutrientOption.maxCalories,
                nutrientOption.minFat,
                nutrientOption.maxFat,
                nutrientOption.minCalcium,
                nutrientOption.maxCalcium,
                nutrientOption.minCholesterol,
                nutrientOption.maxCholesterol,
                nutrientOption.minSugar,
                nutrientOption.maxSugar,
                nutrientOption.minVitaminA,
                nutrientOption.maxVitaminA,
                nutrientOption.minVitaminC,
                nutrientOption.maxVitaminC,
                nutrientOption.minVitaminD,
                nutrientOption.maxVitaminD,
                nutrientOption.minVitaminE,
                nutrientOption.maxVitaminE,
                nutrientOption.minVitaminK,
                nutrientOption.maxVitaminK
            )
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }

    override suspend fun searchRecipesByIngredients(
        searchOption: SearchOption
    ): BaseResponse<IntroRecipeResponse> =
        try {
            val response = apiService.searchRecipesByIngredients(
                searchOption.query,
                searchOption.cuisine,
                searchOption.diet,
                searchOption.number,
                searchOption.readyTime,
                searchOption.sort,
                searchOption.sortDirection,
                searchOption.includeIngredients,
                searchOption.excludeIngredients
            )
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }

    override suspend fun searchRandomRecipes(
        query: String,
        cuisine: String,
        type: String,
        diet: String,
        intolerances: String,
        number: Int,
        isInstructionRequired: Boolean,
        sort: String,
        sortDirection: String
    ): BaseResponse<IntroRecipeResponse> =
        try {
            val response = apiService.searchRandomRecipes(
                query,
                cuisine,
                type,
                diet,
                intolerances,
                number,
                isInstructionRequired,
                sort,
                sortDirection
            )
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }


    override suspend fun getRandomRecipes(
        number: Int,
        tags: String
    ): BaseResponse<RandomRecipeResponse> =
        try {
            val response = apiService.getRandomRecipes(number, tags)
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

    override suspend fun getManyRecipeByIds(ids: String): BaseResponse<List<Recipe>> =
        try {
            val response = apiService.getManyRecipesByIds(ids)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }

    override suspend fun getMealPlans(
        timeFrame: String,
        targetCalos: Int,
        diet: String
    ): BaseResponse<MealPlanResponse> =
        try {
            val response = apiService.getMealPlan(timeFrame, targetCalos, diet)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }

    override suspend fun getMealPlans() = mealPlanDao.getMealPlans()

    override suspend fun insertMealPlan(mealPlan: MealPlanResponse) =
        mealPlanDao.insertMealPlan(mealPlan)

    override suspend fun deleteAllMealPlan() = mealPlanDao.deleteAllMealPlan()

    override suspend fun getAllIntroRecipes(): List<IntroRecipe> = introDao.getAllIntroRecipes()

    override suspend fun addIntroRecipes(introRecipes: List<IntroRecipe>) =
        introDao.addAllIntroRecipes(introRecipes)

    override suspend fun deleteAllIntroRecipes() = introDao.deleteAllIntroRecipes()
}
