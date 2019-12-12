package com.example.cooky.data.repository

import com.example.cooky.base.BaseResponse
import com.example.cooky.data.local.model.autocomplete.QueryIngredientSearch
import com.example.cooky.data.local.model.autocomplete.QueryRecipeSearch
import com.example.cooky.data.local.model.nutition.NutrientOption
import com.example.cooky.data.local.model.search.SearchOption
import com.example.cooky.data.remote.ResponseHandler
import com.example.cooky.data.remote.api.ApiService
import com.example.cooky.data.remote.response.IntroRecipeResponse
import com.example.cooky.data.remote.response.RandomRecipeResponse
import java.lang.Exception

class IntroRepositoryImpl(
    private val apiService: ApiService,
    private val responseHandler: ResponseHandler
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
        searchOption: SearchOption,
        nutrientOption: NutrientOption
    ): BaseResponse<IntroRecipeResponse> =
        try {
            val response = apiService.searchRecipesByNutrients(
                searchOption.query,
                searchOption.cuisine,
                searchOption.diet,
                searchOption.number,
                searchOption.readyTime,
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
        try{
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
        }catch (e: Exception){
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
}
