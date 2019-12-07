package com.example.cooky.ui.general.content

import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.databinding.FragmentGeneralContentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeneralContentFragment() :
    BaseFragment<FragmentGeneralContentBinding, GeneralContentViewModel>() {

    override val layoutResource: Int = R.layout.fragment_general_content

    override val viewModel: GeneralContentViewModel by viewModel()

    override fun initView() {
    }

    override fun setBindingVariables() {
    }
}
