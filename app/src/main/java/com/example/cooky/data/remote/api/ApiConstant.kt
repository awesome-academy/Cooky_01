package com.example.cooky.data.remote.api

const val BASE_URL = "https://api.spoonacular.com/"
const val PATH_RECIPE = "recipes"
const val PATH_FOOD = "food"
const val PATH_INGREDIENTS = "ingredients"
const val PATH_SEARCH = "search"
const val PATH_RANDOM = "random"
const val PATH_INFORMATION = "{id}/information"
const val PATH_AUTOCOMPLETE = "autocomplete"
const val PATH_NUTRITION = "{id}/nutritionWidget.json"
const val PATH_FINDBYNUTRIENTS = "findByNutrients"
const val PATH_COMPLEXSEARCH = "complexSearch"
const val PATH_INFORMATIONBULK = "informationBulk"
const val PATH_MEALPLAN = "mealplans"
const val PATH_GENERATE = "generate"

const val QUERY_QUERY = "query"
const val QUERY_CUISINE = "cuisine"
const val QUERY_INSTRUCTIONS_REQUIRED = "instructionsRequired"
const val QUERY_DIET = "diet"
const val QUERY_TYPE = "type"
const val QUERY_INCLUDE_INGREDIENTS = "includeIngredients"
const val QUERY_EXCLUDE_INGREDIENTS = "excludeIngredients"
const val QUERY_INTOLERANCES = "intolerances"
const val QUERY_NUMBER = "number"
const val QUERY_TAG = "tags"
const val QUERY_ID = "id"
const val QUERY_IDS = "ids"
const val QUERY_MAX_READYTIME = "maxReadyTime"
const val QUERY_SORT = "sort"
const val QUERY_SORTDIRECTION = "sortDirection"
const val QUERY_TIMEFRAME = "timeFrame"
const val QUERY_TARGET_CALOS = "targetCalories"

const val TOKEN = "apiKey"
const val CODE_TIMEOUT = -1
const val CODE_UNAUTHORISED = 401
const val CODE_NOTFOUND = 404
const val CODE_NO_INTERNET = 999
