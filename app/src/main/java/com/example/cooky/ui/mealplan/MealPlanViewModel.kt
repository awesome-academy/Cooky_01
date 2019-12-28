package com.example.cooky.ui.mealplan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cooky.base.BaseViewModel
import com.example.cooky.base.Status
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.data.remote.response.MealPlanResponse
import com.example.cooky.data.repository.IntroRepository
import com.example.cooky.util.BREAKFAST_INDEX
import com.example.cooky.util.DINNER_INDEX
import com.example.cooky.util.LUNCH_INDEX
import kotlinx.coroutines.launch

class MealPlanViewModel(private val repo: IntroRepository) : BaseViewModel() {
    private val _mealPlan = MutableLiveData<MealPlanResponse>()
    val mealPlan: LiveData<MealPlanResponse> get() = _mealPlan

    private val _breakFast = MutableLiveData<IntroRecipe>()
    val breakFast: LiveData<IntroRecipe> get() = _breakFast

    private val _lunch = MutableLiveData<IntroRecipe>()
    val lunch: LiveData<IntroRecipe> get() = _lunch

    private val _dinner = MutableLiveData<IntroRecipe>()
    val dinner: LiveData<IntroRecipe> get() = _dinner

    private val _isCreatedMealPlan = MutableLiveData<Boolean>().apply { value = false }
    val isCreatedMealPlan: LiveData<Boolean> get() = _isCreatedMealPlan

    fun getMealPlan(targetCalos: Int, diet: String) {
        setOnLoading()
        _isCreatedMealPlan.value = false
        viewModelScope.launch {
            val response = repo.getMealPlans(targetCalos = targetCalos, diet = diet)
            if (response.status == Status.SUCCESS) {
                response.data?.let {
                    saveLocalMealPlan(it)
                    setValueMealPlan(it)
                }
                _isCreatedMealPlan.value = true
                setCloseLoading()
            } else {
                response.message?.let(::setMessage)
            }
        }
    }

    private fun saveLocalMealPlan(mealPlan: MealPlanResponse) {
        viewModelScope.launch {
            repo.deleteAllMealPlan()
            repo.insertMealPlan(mealPlan)
        }
    }

    fun checkLocalMealPlan() {
        viewModelScope.launch {
            val mealPlanList = repo.getMealPlans()
            if (mealPlanList.isNotEmpty()) {
                setValueMealPlan(mealPlanList[0])
                _isCreatedMealPlan.value = true
            }
        }
    }

    private fun setValueMealPlan(mealPlan: MealPlanResponse) {
        _mealPlan.value = mealPlan
        _breakFast.value = mealPlan.introRecipes[BREAKFAST_INDEX]
        _lunch.value = mealPlan.introRecipes[LUNCH_INDEX]
        _dinner.value = mealPlan.introRecipes[DINNER_INDEX]
    }

    fun deleteAllMealPlan() {
        viewModelScope.launch {
            repo.deleteAllMealPlan()
            _isCreatedMealPlan.value = false
        }
    }
}
