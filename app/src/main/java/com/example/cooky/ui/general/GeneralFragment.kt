package com.example.cooky.ui.general

import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.databinding.FragmentFavoriteBinding
import com.example.cooky.databinding.FragmentGeneralBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeneralFragment() : BaseFragment<FragmentGeneralBinding, GeneralViewModel>() {

    override val layoutResource: Int = R.layout.fragment_general

    override val viewModel: GeneralViewModel by viewModel()

    override fun initView() {
    }

    override fun setBindingVariables() {
    }

    companion object {
        fun newInstance() = GeneralFragment()
    }
}
