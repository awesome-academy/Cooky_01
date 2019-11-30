package com.example.cooky.ui.recipe

import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.databinding.FragmentRecipeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeFragment() : BaseFragment<FragmentRecipeBinding, RecipeViewModel>() {

    override val layoutResource: Int = R.layout.fragment_recipe

    override val viewModel: RecipeViewModel by viewModel()

    override fun initView() {
    }

    override fun setBindingVariables() {
    }

    companion object {
        fun newInstance() = RecipeFragment()
    }
}
