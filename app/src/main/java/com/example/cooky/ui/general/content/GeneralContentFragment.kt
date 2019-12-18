package com.example.cooky.ui.general.content

import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.databinding.FragmentGeneralContentBinding
import com.example.cooky.ui.adapter.IntroAdapterHorizontal
import com.example.cooky.ui.adapter.IntroAdapterVertical
import com.example.cooky.ui.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_general_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeneralContentFragment :
    BaseFragment<FragmentGeneralContentBinding, GeneralContentViewModel>() {

    private var mainNavController: NavController? = null
    private lateinit var suggestIntroRecipe: IntroRecipe

    private val dessertAdapter = IntroAdapterHorizontal {
        mainNavController?.navigate(HomeFragmentDirections.actionGlobalRecipeDetail(it.id))
    }

    private val mainAdapter = IntroAdapterHorizontal {
        mainNavController?.navigate(HomeFragmentDirections.actionGlobalRecipeDetail(it.id))
    }

    private val vietnameseAdapter = IntroAdapterHorizontal {
        mainNavController?.navigate(HomeFragmentDirections.actionGlobalRecipeDetail(it.id))
    }

    private val recentAdapter = IntroAdapterVertical {
        mainNavController?.navigate(HomeFragmentDirections.actionGlobalRecipeDetail(it.id))
    }

    override val layoutResource: Int = R.layout.fragment_general_content

    override val viewModel: GeneralContentViewModel by viewModel()

    override fun initView() {
        recyclerDessert.adapter = dessertAdapter
        recyclerMain.adapter = mainAdapter
        recyclerVietNamese.adapter = vietnameseAdapter
        recyclerViewRecent.adapter = recentAdapter
        nestedScrollView.setOnScrollChangeListener(viewModel.onScrollListener)
        mainNavController = activity?.findNavController(R.id.nav_host_fragment)
        imageSuggest.setOnClickListener {
            mainNavController?.navigate(
                HomeFragmentDirections.actionGlobalRecipeDetail(suggestIntroRecipe.id)
            )
        }
    }

    override fun setBindingVariables() {
        dataBinding.viewModel = viewModel
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.apply {
            dessertRecipes.observe(viewLifecycleOwner, Observer {
                dessertAdapter.submitList(it.introRecipes)
            })
            mainRecipes.observe(viewLifecycleOwner, Observer {
                mainAdapter.submitList(it.introRecipes)
            })
            vietnameseRecipes.observe(viewLifecycleOwner, Observer {
                vietnameseAdapter.submitList(it.introRecipes)
            })
            suggestRecipe.observe(viewLifecycleOwner, Observer {
                suggestIntroRecipe = it
            })
            listItem.observe(viewLifecycleOwner, Observer(recentAdapter::submitList))
            if (!isFirstLoaded) {
                firstLoadData()
                isFirstLoaded = true
            }
        }
    }
}
