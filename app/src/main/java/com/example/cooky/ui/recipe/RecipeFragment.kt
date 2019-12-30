package com.example.cooky.ui.recipe

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.databinding.FragmentRecipeBinding
import com.example.cooky.ui.adapter.BadNutritionAdapter
import com.example.cooky.ui.adapter.GoodNutritionAdapter
import com.example.cooky.ui.adapter.IngredientAdapter
import com.example.cooky.ui.adapter.StepAdapter
import com.example.cooky.util.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_recipe.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeFragment : BaseFragment<FragmentRecipeBinding, RecipeViewModel>() {

    private var titleAppBar = NONE
    private var recipeId = DEFAULT_ID
    private val ingredientAdapter = IngredientAdapter()
    private val badNutritionAdapter = BadNutritionAdapter()
    private val goodNutritionAdapter = GoodNutritionAdapter()
    private val stepAdapter = StepAdapter()
    override val layoutResource: Int = R.layout.fragment_recipe

    override val viewModel: RecipeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { recipeId = RecipeFragmentArgs.fromBundle(it).recipeId }
    }

    override fun initView() {
        initAppBar()
        initToolbar()
        initRecyclerView()
        handleArrowDownClick()
        ImageViewCompat.setImageTintList(
            floatingLike,
            ColorStateList.valueOf(Color.WHITE)
        )
    }

    private fun initAppBar() {
        var isShown = false
        var scrollRange = ON_EXPAND
        appBarLayout.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (scrollRange == ON_EXPAND) {
                    appBarLayout?.let { scrollRange = it.totalScrollRange }
                }
                if (scrollRange + verticalOffset == ON_COLLAPSING) {
                    collapsingToolbar.title = titleAppBar
                    isShown = true
                } else if (isShown) {
                    collapsingToolbar.title = NONE
                    isShown = false
                }
            })
    }

    private fun initToolbar() {
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        floatingLike.setOnClickListener {
            viewModel.handleSaveFavorite()
            viewModel.isFavorite.value?.let {
                val msg = if (it) {
                    getString(R.string.title_add_favorite)
                } else {
                    getString(R.string.title_remove_favorite)
                }
                Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        recyclerIngredient.apply {
            adapter = ingredientAdapter
            addItemDecoration(dividerItemDecoration)
        }
        recyclerBadNutrition.apply {
            adapter = badNutritionAdapter
            addItemDecoration(dividerItemDecoration)
        }
        recyclerGoodNutrition.apply {
            adapter = goodNutritionAdapter
            addItemDecoration(dividerItemDecoration)
        }
        recyclerStep.adapter = stepAdapter
    }


    private fun handleArrowDownClick() {
        btnArrowDownBad.setOnClickListener {
            if (recyclerBadNutrition.visibility == View.GONE) {
                btnArrowDownBad.animate()
                    .rotation(ROTATE_INVERSE)
                    .duration = ROTATE_DURATION
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(cardviewBad, AutoTransition())
                }
                recyclerBadNutrition.onShow(true)
            } else {
                btnArrowDownBad.animate()
                    .rotation(ROTATE_DEFAULT)
                    .duration = ROTATE_DURATION
                recyclerBadNutrition.onShow(false)
            }
        }

        btnArrowDownGood.setOnClickListener {
            if (recyclerGoodNutrition?.visibility == View.GONE) {
                btnArrowDownGood.animate()
                    .rotation(ROTATE_INVERSE).duration = ROTATE_DURATION
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(frameGood, AutoTransition())
                }
                recyclerGoodNutrition.onShow(true)
            } else {
                btnArrowDownGood.animate().rotation(ROTATE_DEFAULT)
                    .duration = ROTATE_DURATION
                recyclerGoodNutrition.onShow(false)
            }
        }
    }

    override fun setBindingVariables() {
        dataBinding.viewModel = viewModel
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.apply {
            nutrition.observe(viewLifecycleOwner, Observer {
                badNutritionAdapter.submitList(it.bad)
                goodNutritionAdapter.submitList(it.good)
            })
            recipe.observe(viewLifecycleOwner, Observer {
                titleAppBar = it.title
                ingredientAdapter.submitList(it.extendedIngredients)
                stepAdapter.submitList(it.analyzedInstructions[0].steps)
            })
            isFavorite.observe(viewLifecycleOwner, Observer {

                floatingLike.setImageResource(
                    if (it) R.drawable.ic_favorite_black_24dp
                    else R.drawable.ic_favorite_border_black_24dp
                )
            })
            loadData(recipeId)
        }
    }

    override fun handleNoInternet() {
        progressLoading.hide()
        context?.let {
            Snackbar.make(
                coordinatorLayout,
                getString(R.string.message_no_internet),
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(TITLE_TRY_AGAIN, object : View.OnClickListener {
                    override fun onClick(p0: View?) {
                            if (!isInternetConnected(it)) {
                                handleNoInternet()
                            } else {
                                viewModel.loadData(recipeId)
                            }
                    }
                })
                .setActionTextColor(ContextCompat.getColor(it, R.color.color_blue_light))
                .show()
        }
    }
}
