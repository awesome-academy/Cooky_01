package com.example.cooky.data.repository

import com.example.cooky.base.BaseResponse
import com.example.cooky.data.local.model.autocomplete.QueryIngredientSearch
import com.example.cooky.data.local.model.autocomplete.QueryRecipeSearch
import com.example.cooky.data.local.model.nutition.NutrientOption
import com.example.cooky.data.local.model.search.BasicSearchOption
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
        searchOption: BasicSearchOption,
        nutrientOption: NutrientOption
    ): BaseResponse<IntroRecipeResponse>

    suspend fun searchRecipesByIngredients(
        searchOption: SearchOption
    ): BaseResponse<IntroRecipeResponse>

    suspend fun searchRandomRecipes(
        query: String = EMPTY_STRING,
        cuisine: String = EMPTY_STRING,
        type: String = EMPTY_STRING,
        diet: String = EMPTY_STRING,
        intolerances: String = EMPTY_STRING,
        number: Int = DEFAULT_NUMBER,
        isInstructionRequired: Boolean = true,
        sort: String = EMPTY_STRING,
        sortDirection: String = EMPTY_STRING
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
