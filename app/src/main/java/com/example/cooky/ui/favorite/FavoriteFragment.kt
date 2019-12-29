package com.example.cooky.ui.favorite

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.databinding.FragmentFavoriteBinding
import com.example.cooky.ui.adapter.RecentRecipeAdapter
import com.example.cooky.ui.adapter.RecipeAdapterVertical
import com.example.cooky.ui.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment private constructor() :
    BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override val layoutResource: Int = R.layout.fragment_favorite

    private val recentAdapter = RecentRecipeAdapter {
        parentFragment?.findNavController()?.navigate(
            HomeFragmentDirections.actionGlobalRecipeDetail(it.id)
        )
    }

    private val likeAdapter = RecipeAdapterVertical {
        parentFragment?.findNavController()?.navigate(
            HomeFragmentDirections.actionGlobalRecipeDetail(it.recipeId)
        )
    }

    override val viewModel: FavoriteViewModel by viewModel()

    override fun initView() {
        initRecyclerView()
        observeDataViewModel()
        nestedScrollView.setOnScrollChangeListener(viewModel.onScrollListener)
    }

    private fun initRecyclerView() {
        recyclerRecent.adapter = recentAdapter
        recyclerViewLike.adapter = likeAdapter
    }

    override fun setBindingVariables() {
        dataBinding.viewModel = viewModel
    }

    private fun observeDataViewModel() {
        super.observeViewModel()
        viewModel.apply {
            recentlyIntro.observe(viewLifecycleOwner, Observer(recentAdapter::submitList))
            getRecentlyIntro()
            listItem.observe(viewLifecycleOwner, Observer {
                likeAdapter.submitList(it)
                likeAdapter.notifyDataSetChanged()
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllLocalRecipes()
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}
