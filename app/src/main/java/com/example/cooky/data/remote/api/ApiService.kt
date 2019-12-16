package com.example.cooky.data.remote.api

import com.example.cooky.data.local.model.autocomplete.QueryIngredientSearch
import com.example.cooky.data.local.model.autocomplete.QueryRecipeSearch
import com.example.cooky.data.local.model.nutition.Nutrition
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.remote.response.IntroRecipeResponse
import com.example.cooky.data.remote.response.RandomRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("$PATH_RECIPE/$PATH_SEARCH")
    suspend fun searchRecipes(
        @Query(QUERY_QUERY) query: String,
        @Query(QUERY_CUISINE) cuisine: String,
        @Query(QUERY_DIET) diet: String,
        @Query(QUERY_EXCLUDE_INGREDIENTS) excludeIngredients: String,
        @Query(QUERY_INTOLERANCES) intolerances: String,
        @Query(QUERY_NUMBER) number: Int
    ): IntroRecipeResponse

    @GET("$PATH_RECIPE/$PATH_COMPLEXSEARCH")
    suspend fun searchRecipesByNutrients(
        @Query(QUERY_QUERY) query: String,
        @Query(QUERY_CUISINE) cuisine: String,
        @Query(QUERY_DIET) diet: String,
        @Query(QUERY_NUMBER) number: Int,
        @Query(QUERY_MAX_READYTIME) readyTime: Int,
        @Query(QUERY_SORT) sort: String,
        @Query(QUERY_SORTDIRECTION) sortDirection: String,
        @Query(QUERY_MIN_CARB) minCarb: Int,
        @Query(QUERY_MAX_CARB) maxCarb: Int,
        @Query(QUERY_MIN_PROTEIN) minProtein: Int,
        @Query(QUERY_MAX_PROTEIN) maxProtein: Int,
        @Query(QUERY_MIN_CALORIES) minCalories: Int,
        @Query(QUERY_MAX_CALORIES) maxCalories: Int,
        @Query(QUERY_MIN_FAT) minFat: Int,
        @Query(QUERY_MAX_FAT) maxFat: Int,
        @Query(QUERY_MIN_CALCIUM) minCalcium: Int,
        @Query(QUERY_MAX_CALCIUM) maxCalcium: Int,
        @Query(QUERY_MIN_CHOLESTEROL) minCholesterol: Int,
        @Query(QUERY_MAX_CHOLESTEROL) maxCholesterol: Int,
        @Query(QUERY_MIN_SUGAR) minSugar: Int,
        @Query(QUERY_MAX_SUGAR) maxSugar: Int,
        @Query(QUERY_MIN_VITAMIN_A) minVitaminA: Int,
        @Query(QUERY_MAX_VITAMIN_A) maxVitaminA: Int,
        @Query(QUERY_MIN_VITAMIN_C) minVitaminC: Int,
        @Query(QUERY_MAX_VITAMIN_C) maxVitaminC: Int,
        @Query(QUERY_MIN_VITAMIN_D) minVitaminD: Int,
        @Query(QUERY_MAX_VITAMIN_D) maxVitaminD: Int,
        @Query(QUERY_MIN_VITAMIN_E) minVitaminE: Int,
        @Query(QUERY_MAX_VITAMIN_E) maxVitaminE: Int,
        @Query(QUERY_MIN_VITAMIN_K) minVitaminK: Int,
        @Query(QUERY_MAX_VITAMIN_K) maxVitaminK: Int
    ): IntroRecipeResponse

    @GET("$PATH_RECIPE/$PATH_COMPLEXSEARCH")
    suspend fun searchRecipesByIngredients(
        @Query(QUERY_QUERY) query: String,
        @Query(QUERY_CUISINE) cuisine: String,
        @Query(QUERY_DIET) diet: String,
        @Query(QUERY_NUMBER) number: Int,
        @Query(QUERY_MAX_READYTIME) readyTime: Int,
        @Query(QUERY_SORT) sort: String,
        @Query(QUERY_SORTDIRECTION) sortDirection: String,
        @Query(QUERY_INCLUDE_INGREDIENTS) includeIngredients: String,
        @Query(QUERY_EXCLUDE_INGREDIENTS) excludeIngredients: String
    ): IntroRecipeResponse

    @GET("$PATH_RECIPE/$PATH_COMPLEXSEARCH")
    suspend fun searchRandomRecipes(
        @Query(QUERY_QUERY) query: String,
        @Query(QUERY_CUISINE) cuisine: String,
        @Query(QUERY_TYPE) type: String,
        @Query(QUERY_DIET) diet: String,
        @Query(QUERY_INTOLERANCES) intolerances: String,
        @Query(QUERY_NUMBER) number: Int,
        @Query(QUERY_INSTRUCTIONS_REQUIRED) isInstructionRequired: Boolean,
        @Query(QUERY_SORT) sort: String,
        @Query(QUERY_SORTDIRECTION) sortDirection: String
    ): IntroRecipeResponse

    @GET("$PATH_RECIPE/$PATH_RANDOM")
    suspend fun getRandomRecipes(
        @Query(QUERY_NUMBER) number: Int,
        @Query(QUERY_TAG) tags: String
    ): RandomRecipeResponse

    @GET("$PATH_RECIPE/$PATH_INFORMATION")
    suspend fun getRecipesInformation(
        @Path(QUERY_ID) id: Int
    ): Recipe

    @GET("$PATH_RECIPE/$PATH_AUTOCOMPLETE")
    suspend fun getAutocompleteRecipeSearch(
        @Query(QUERY_QUERY) query: String,
        @Query(QUERY_NUMBER) number: Int
    ): List<QueryRecipeSearch>

    @GET("$PATH_FOOD/$PATH_INGREDIENTS/$PATH_AUTOCOMPLETE")
    suspend fun getAutoCompleteIngredientSearch(
        @Query(QUERY_QUERY) query: String,
        @Query(QUERY_NUMBER) number: Int
    ): List<QueryIngredientSearch>

    @GET("$PATH_RECIPE/$PATH_NUTRITION")
    suspend fun getRecipeNutrition(
        @Path(QUERY_ID) id: Int
    ): Nutrition
}
