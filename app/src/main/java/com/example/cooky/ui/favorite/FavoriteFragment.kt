package com.example.cooky.ui.favorite

import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment() : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override val layoutResource: Int = R.layout.fragment_favorite

    override val viewModel: FavoriteViewModel by viewModel()

    override fun initView() {
    }

    override fun setBindingVariables() {
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}
