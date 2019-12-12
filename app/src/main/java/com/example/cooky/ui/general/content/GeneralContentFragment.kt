package com.example.cooky.ui.general.content

import androidx.lifecycle.Observer
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.databinding.FragmentGeneralContentBinding
import com.example.cooky.ui.adapter.IntroAdapterHorizontal
import com.example.cooky.ui.adapter.IntroAdapterVertical
import com.example.cooky.util.showToast
import kotlinx.android.synthetic.main.fragment_general_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeneralContentFragment :
    BaseFragment<FragmentGeneralContentBinding, GeneralContentViewModel>() {

    private val dessertAdapter = IntroAdapterHorizontal {
        // TODO: Handle on item click later
    }

    private val mainAdapter = IntroAdapterHorizontal {
        // TODO: Handle on item click later
    }

    private val vietnameseAdapter = IntroAdapterHorizontal {
        // TODO: Handle on item click later
    }

    private val recentAdapter = IntroAdapterVertical {
        // TODO: Handle on item click later
    }

    override val layoutResource: Int = R.layout.fragment_general_content

    override val viewModel: GeneralContentViewModel by viewModel()

    override fun initView() {
        recyclerDessert.adapter = dessertAdapter
        recyclerMain.adapter = mainAdapter
        recyclerVietNamese.adapter = vietnameseAdapter
        recyclerViewRecent.adapter = recentAdapter
        nestedScrollView.setOnScrollChangeListener(viewModel.onScrollListener)
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
            listItem.observe(viewLifecycleOwner, Observer(recentAdapter::submitList))
            firstLoadData()
        }
    }
}
