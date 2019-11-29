package com.example.cooky.data.repository

import com.example.cooky.base.BaseResponse
import com.example.cooky.data.local.model.autocomplete.QueryIngredientSearch
import com.example.cooky.data.local.model.autocomplete.QueryRecipeSearch
import com.example.cooky.data.local.model.nutition.NutrientOption
import com.example.cooky.data.local.model.search.SearchOption
import com.example.cooky.data.remote.api.DEFAULT_NUMBER
import com.example.cooky.data.remote.api.EMPTY_STRING
import com.example.cooky.data.remote.response.IntroRecipeResponse
import com.example.cooky.data.remote.response.RandomRecipeResponse

interface IntroRepository {
    suspend fun searchRecipes(
        searchOption: SearchOption
    ): BaseResponse<IntroRecipeResponse>

    suspend fun searchRecipesByNutrients(
        searchOption: SearchOption,
        nutrientOption: NutrientOption
    ): BaseResponse<IntroRecipeResponse>

    suspend fun searchRecipesByIngredients(
        searchOption: SearchOption
    ): BaseResponse<IntroRecipeResponse>

    suspend fun getRandomRecipes(
        number: Int = DEFAULT_NUMBER,
        tags: String = EMPTY_STRING
    ): BaseResponse<RandomRecipeResponse>

    suspend fun getAutocompleteRecipeSearch(
        query: String = EMPTY_STRING,
        number: Int = DEFAULT_NUMBER
    ): BaseResponse<List<QueryRecipeSearch>>

    suspend fun getAutoCompleteIngredientSearch(
        query: String = EMPTY_STRING,
        number: Int = DEFAULT_NUMBER
    ): BaseResponse<List<QueryIngredientSearch>>
}
