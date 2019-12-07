package com.example.cooky.ui.general.discover

import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.databinding.FragmentDiscoverBinding
import com.example.cooky.databinding.FragmentFavoriteBinding
import com.example.cooky.databinding.FragmentGeneralBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverFragment() : BaseFragment<FragmentDiscoverBinding, DiscoverViewModel>() {

    override val layoutResource: Int = R.layout.fragment_discover

    override val viewModel: DiscoverViewModel by viewModel()

    override fun initView() {
    }

    override fun setBindingVariables() {
    }
}
