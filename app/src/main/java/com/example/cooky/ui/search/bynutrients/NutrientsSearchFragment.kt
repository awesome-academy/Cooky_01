package com.example.cooky.ui.search.bynutrients

import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.data.local.model.nutition.NutrientOption
import com.example.cooky.data.local.model.search.BasicSearchOption
import com.example.cooky.databinding.FragmentNutrientsSearchBinding
import com.example.cooky.ui.adapter.IntroAdapterVertical
import com.example.cooky.util.hideKeyboard
import kotlinx.android.synthetic.main.fragment_nutrients_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NutrientsSearchFragment :
    BaseFragment<FragmentNutrientsSearchBinding, NutrientsSearchViewModel>() {

    lateinit var searchView: SearchView
    private var searchOption = BasicSearchOption()
    private var nutrientOption = NutrientOption()
    private val recipeAdapter = IntroAdapterVertical {
        findNavController().navigate(
            NutrientsSearchFragmentDirections.actionGlobalRecipeDetail(it.id)
        )
    }

    override val layoutResource: Int = R.layout.fragment_nutrients_search

    override val viewModel: NutrientsSearchViewModel by viewModel()

    override fun initView() {
        initToolbar()
        searchOption.apply {
            sort = SORT_RANDOM
            sortDirection = DIRECTION_DESC
            number = QUERY_NUMBER
        }
        recyclerViewRecipe.adapter = recipeAdapter
        nestedScrollView.setOnScrollChangeListener(viewModel.onScrollListener)
    }

    private fun initToolbar() {
        toolBarNutrient.apply {
            title = context.getString(R.string.title_nutrients_search)
            inflateMenu(R.menu.menu_ingredients_search)
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            initMenu(menu)
        }
    }

    private fun initMenu(menu: Menu) {
        val itemSearch = menu.findItem(R.id.option_search)
        searchView = itemSearch.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchOption.query = it }
                viewModel.searchRecipe(searchOption, nutrientOption)
                searchView.hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun setBindingVariables() {
        dataBinding.viewModel = viewModel
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.apply {
            listItem.observe(viewLifecycleOwner, Observer(recipeAdapter::submitList))
        }
    }

    companion object {
        private const val SORT_RANDOM = "random"
        private const val DIRECTION_DESC = "desc"
        private const val QUERY_NUMBER = 20
    }
}
