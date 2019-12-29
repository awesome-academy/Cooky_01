package com.example.cooky.data.local

import android.content.Context
import androidx.room.*
import com.example.cooky.data.local.dao.IntroRecipeDao
import com.example.cooky.data.local.dao.MealPlanDao
import com.example.cooky.data.local.dao.NutritionDao
import com.example.cooky.data.local.dao.RecipeDao
import com.example.cooky.data.local.model.nutition.Nutrition
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.data.remote.response.MealPlanResponse

@Database(
    entities = [Nutrition::class, Recipe::class, MealPlanResponse::class, IntroRecipe::class],
    version = AppDatabase.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun nutritionDAO(): NutritionDao

    abstract fun recipeDAO(): RecipeDao

    abstract fun meanPlanDAO(): MealPlanDao

    abstract fun introRecipeDAO(): IntroRecipeDao

    companion object {
        private const val DATABASE_NAME = "cooky_database"
        internal const val DATABASE_VERSION = 1
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries().build().also { INSTANCE = it }
        }
    }
}
