package com.example.cooky.ui.favorite

import android.content.SharedPreferences
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.databinding.FragmentFavoriteBinding
import com.example.cooky.ui.adapter.RecentRecipeAdapter
import com.example.cooky.ui.adapter.RecipeAdapterVertical
import com.example.cooky.ui.home.HomeFragmentDirections
import com.example.cooky.util.RECENTLY_ID
import com.example.cooky.util.STRING_NULL
import com.example.cooky.util.stringToListInteger
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment private constructor() :
    BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override val layoutResource: Int = R.layout.fragment_favorite

    private var recentlyIds = mutableListOf<Int>()

    private val recentAdapter = RecentRecipeAdapter {
        parentFragment?.findNavController()?.navigate(
            HomeFragmentDirections.actionGlobalRecipeDetail(it.recipeId)
        )
    }

    private val likeAdapter = RecipeAdapterVertical {
        parentFragment?.findNavController()?.navigate(
            HomeFragmentDirections.actionGlobalRecipeDetail(it.recipeId)
        )
    }

    private val sharedPreferences: SharedPreferences by inject()
    override val viewModel: FavoriteViewModel by viewModel()

    override fun initView() {
        initRecyclerView()
        getRecentlyRecipeIds()
        observeDataViewModel()
        nestedScrollView.setOnScrollChangeListener(viewModel.onScrollListener)
    }

    private fun initRecyclerView() {
        recyclerRecent.adapter = recentAdapter
        recyclerViewLike.adapter = likeAdapter
    }

    private fun getRecentlyRecipeIds() {
        val recentlyIdsString = sharedPreferences.getString(RECENTLY_ID, STRING_NULL)
        if (recentlyIdsString != STRING_NULL) {
            val newRecentlyIds = stringToListInteger(recentlyIdsString!!)
            if (recentlyIds.isEmpty()) {
                recentlyIds.addAll(newRecentlyIds)
                viewModel.getRecentlyRecipes(recentlyIds)
            } else if (recentlyIds.first() != newRecentlyIds.first()) {
                recentlyIds = newRecentlyIds.toMutableList()
                viewModel.getRecentlyRecipes(recentlyIds)
            }
        }
    }

    override fun setBindingVariables() {
        dataBinding.viewModel = viewModel
    }

    private fun observeDataViewModel() {
        super.observeViewModel()
        viewModel.apply {
            recentlyRecipe.observe(viewLifecycleOwner, Observer(recentAdapter::submitList))
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
