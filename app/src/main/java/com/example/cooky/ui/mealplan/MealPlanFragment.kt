package com.example.cooky.ui.mealplan

import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.data.remote.response.MealPlanResponse
import com.example.cooky.databinding.FragmentMealplanBinding
import com.example.cooky.util.*
import kotlinx.android.synthetic.main.fragment_mealplan.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MealPlanFragment : BaseFragment<FragmentMealplanBinding, MealPlanViewModel>() {

    private var diet = TITLE_DIET_ALL
    private var targetCalors = NUMBER_MIN_TARGET_CALOS
    private lateinit var currentMealPlan: MealPlanResponse

    override val layoutResource = R.layout.fragment_mealplan

    override val viewModel: MealPlanViewModel by viewModel()

    override fun setBindingVariables() {
        dataBinding.viewModel = viewModel
    }

    override fun initView() {
        initToolbar()
        initChipView()
        initMultiSlider()
        btnCreatePlan.setOnClickListener {
            viewModel.getMealPlan(targetCalors, diet)
        }
        btnDeletePlan.setOnClickListener {
            viewModel.deleteAllMealPlan()
        }
    }

    private fun initToolbar() {
        toolbarMealPlan.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initMultiSlider() {
        multiSliderCalo.setOnThumbValueChangeListener { _, _, _, value ->
            textValueCalo.text = value.toString()
            targetCalors = value
        }
    }

    private fun initChipView() {
        chipGroupDiet.setOnCheckedChangeListener { _, id ->
            when (id) {
                1 -> setDiet(TITLE_DIET_GLUTEN_FREE)
                2 -> setDiet(TITLE_DIET_KETOGENIC)
                3 -> setDiet(TITLE_DIET_VEGETARIAN)
                4 -> setDiet(TITLE_DIET_PESCETARIAN)
                5 -> setDiet(TITLE_DIET_PALEO)
                6 -> setDiet(TITLE_DIET_WHOLE30)
                else -> {
                    diet = NONE
                    textDietValue.text = TITLE_DIET_ALL
                }
            }
        }
    }

    private fun setDiet(diet: String) {
        this.diet = diet
        textDietValue.text = diet
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.apply {
            checkLocalMealPlan()
            mealPlan.observe(viewLifecycleOwner, Observer { currentMealPlan = it })
        }
    }
}
