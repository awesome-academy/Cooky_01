package com.example.cooky.ui.search.bynutrients

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.base.LockableBottomSheetBehavior
import com.example.cooky.data.local.model.nutition.NutrientOption
import com.example.cooky.data.local.model.search.BasicSearchOption
import com.example.cooky.data.remote.api.*
import com.example.cooky.databinding.FragmentNutrientsSearchBinding
import com.example.cooky.ui.adapter.IntroAdapterVertical
import com.example.cooky.ui.adapter.NutrientPickerAdapter
import com.example.cooky.util.*
import com.example.cooky.util.hideKeyboard
import com.example.cooky.util.setVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_nutrients_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NutrientsSearchFragment :
    BaseFragment<FragmentNutrientsSearchBinding, NutrientsSearchViewModel>() {

    lateinit var searchView: SearchView
    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var searchOption = BasicSearchOption()
    private var nutrientOption = NutrientOption()
    private val recipeAdapter = IntroAdapterVertical {
        findNavController().navigate(
            NutrientsSearchFragmentDirections.actionGlobalRecipeDetail(it.id)
        )
    }

    private lateinit var nutrientPickerAdapter: NutrientPickerAdapter

    override val layoutResource: Int = R.layout.fragment_nutrients_search

    override val viewModel: NutrientsSearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            nutrientOption = it.getParcelable(NUTRIENT_OPTION) as NutrientOption
        }
    }

    override fun initView() {
        initToolbar()
        initBottomSheet()
        initAdapter()
        initNutrientRangePicker()
        searchOption.apply {
            sort = SORT_RANDOM
            sortDirection = DIRECTION_DESC
            number = QUERY_NUMBER_DEFAULT
        }
        nestedScrollView.setOnScrollChangeListener(viewModel.onScrollListener)
        obServeViewModelFavorite()
    }

    private fun initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetNutrient).apply {
            addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {

                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                        if (bottomSheetBehavior is LockableBottomSheetBehavior<ConstraintLayout>) {

                            (bottomSheetBehavior as LockableBottomSheetBehavior)
                                .swipeEnabled = false
                        }
                    }
                }
            })
        }
        textNutrientOption.setOnClickListener {
            bottomSheetBehavior.setVisible(true)
        }
        textDone.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun initAdapter() {
        recyclerViewRecipe.adapter = recipeAdapter
        nutrientPickerAdapter = NutrientPickerAdapter { title, thumbIndex, value ->
            nutrientOption.apply {
                when (title) {
                    CALORIES -> {
                        if (thumbIndex == FIRST_THUMP_INDEX) minCalories = value
                        else maxCalories = value
                    }
                    PROTEIN -> {
                        if (thumbIndex == FIRST_THUMP_INDEX) minProtein = value
                        else maxProtein = value
                    }
                    CALCIUM -> {
                        if (thumbIndex == FIRST_THUMP_INDEX) minCalcium = value
                        else maxCalcium = value
                    }
                    SUGAR -> {
                        if (thumbIndex == FIRST_THUMP_INDEX) minSugar = value
                        else maxSugar = value
                    }
                    CHOLESTEROL -> {
                        if (thumbIndex == FIRST_THUMP_INDEX) minSugar = value
                        else maxSugar = value
                    }
                    FAT -> {
                        if (thumbIndex == FIRST_THUMP_INDEX) minFat = value
                        else maxFat = value
                    }
                    CARBOHYDRATES -> {
                        if (thumbIndex == FIRST_THUMP_INDEX) minCarb = value
                        else maxCarb = value
                    }
                }
            }
        }
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
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchOption.query = it }
                viewModel.searchRecipe(searchOption, nutrientOption)
                searchView.hideKeyboard()
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun initNutrientRangePicker() {
        recyclerNutrientPicker.adapter = nutrientPickerAdapter
        val nutrientTitles = resources.getStringArray(R.array.array_nutrients)
        val nutrientPickers = nutrientTitles.map { title ->
            when (title) {
                CALORIES -> {
                    NutrientPicker(
                        title,
                        DEFAULT_MIN_CALO,
                        DEFAULT_MAX_CALO,
                        start = nutrientOption.minCalories,
                        end = nutrientOption.maxCalories
                    )
                }
                PROTEIN -> {
                    NutrientPicker(
                        title, start = nutrientOption.minProtein, end = nutrientOption.maxProtein
                    )
                }
                CALCIUM -> {
                    NutrientPicker(
                        title,
                        start = nutrientOption.minCalcium,
                        end = nutrientOption.maxCalcium
                    )
                }
                SUGAR -> {
                    NutrientPicker(
                        title,
                        start = nutrientOption.minSugar,
                        end = nutrientOption.maxSugar
                    )
                }
                CHOLESTEROL -> {
                    NutrientPicker(
                        title,
                        start = nutrientOption.minCholesterol,
                        end = nutrientOption.maxCholesterol
                    )
                }
                FAT -> {
                    NutrientPicker(
                        title,
                        start = nutrientOption.minFat,
                        end = nutrientOption.maxFat
                    )
                }
                CARBOHYDRATES -> {
                    NutrientPicker(
                        title,
                        start = nutrientOption.minCarb,
                        end = nutrientOption.maxCarb
                    )
                }
                else -> {
                    NutrientPicker(title)
                }
            }

        }
        nutrientPickerAdapter.submitList(nutrientPickers)
    }

    override fun setBindingVariables() {
        dataBinding.viewModel = viewModel
    }

    private fun obServeViewModelFavorite() {
        viewModel.listItem.observe(viewLifecycleOwner, Observer(recipeAdapter::submitList))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(NUTRIENT_OPTION, nutrientOption)
    }
}
