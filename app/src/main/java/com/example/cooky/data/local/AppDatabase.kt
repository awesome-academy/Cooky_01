package com.example.cooky.data.local

import android.content.Context
import androidx.room.*
import com.example.cooky.data.local.dao.NutritionDao
import com.example.cooky.data.local.dao.RecipeDAO
import com.example.cooky.data.local.model.nutition.Nutrition
import com.example.cooky.data.local.model.recipe.Recipe

@Database(
    entities = [Nutrition::class, Recipe::class],
    version = AppDatabase.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun nutritionDAO(): NutritionDao

    abstract fun recipeDAO(): RecipeDAO

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
