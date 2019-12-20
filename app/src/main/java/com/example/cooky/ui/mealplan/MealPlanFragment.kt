package com.example.cooky.ui.mealplan

import androidx.navigation.findNavController
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.databinding.FragmentMealplanBinding
import kotlinx.android.synthetic.main.fragment_mealplan.*

import org.koin.androidx.viewmodel.ext.android.viewModel

class MealPlanFragment : BaseFragment<FragmentMealplanBinding, MealPlanViewModel>() {

    override val layoutResource: Int = R.layout.fragment_mealplan

    override val viewModel: MealPlanViewModel by viewModel()

    override fun setBindingVariables() {
        dataBinding.viewModel = viewModel
    }

    override fun initView() {
        initToolbar()
        initMultiSlider()
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
        }
    }
}
