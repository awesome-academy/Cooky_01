package com.example.cooky.di

import android.content.Context
import android.content.SharedPreferences
import com.example.cooky.data.local.AppDatabase
import com.example.cooky.data.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val SHARED_PREFERENCES_NAME = "SharedPreferences"

val sourceModule = module {
    single { AppDatabase.getInstance(androidContext()) }
    single { AppDatabase.getInstance(get()).nutritionDAO() }
    single { AppDatabase.getInstance(get()).recipeDAO() }
    single<InfoRepository> { InfoRepositoryImpl(get(), get(), get(), get()) }
    single<IntroRepository> { IntroRepositoryImpl(get(), get()) }
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }
}
