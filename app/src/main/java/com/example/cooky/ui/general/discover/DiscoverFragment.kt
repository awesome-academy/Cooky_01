package com.example.cooky.ui.general.discover

import androidx.lifecycle.Observer
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.data.local.model.search.BasicSearchOption
import com.example.cooky.databinding.FragmentDiscoverBinding
import com.example.cooky.ui.adapter.IntroAdapterVertical
import com.example.cooky.ui.general.GeneralViewModel
import kotlinx.android.synthetic.main.fragment_discover.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverFragment : BaseFragment<FragmentDiscoverBinding, DiscoverViewModel>() {

    private val searchAdapter = IntroAdapterVertical({
        // TODO: Handle on item click later
    })

    private var optionSearch = BasicSearchOption()

    private lateinit var generalViewModel: GeneralViewModel

    override val layoutResource: Int = R.layout.fragment_discover

    override val viewModel: DiscoverViewModel by viewModel()

    override fun initView() {
        recyclerViewSearch.adapter = searchAdapter
        nestedScrollView.setOnScrollChangeListener(viewModel.onScrollListener)
        parentFragment?.parentFragment?.let {
            generalViewModel = getSharedViewModel(from = { it })
        }
    }

    override fun setBindingVariables() {
        dataBinding.viewModel = viewModel
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.apply {
            listItem.observe(viewLifecycleOwner, Observer {
                searchAdapter.submitList(it)
                searchAdapter.notifyDataSetChanged()
            })
        }

        generalViewModel.searchOption.observe(viewLifecycleOwner, Observer {
            optionSearch = it
            viewModel.startSearching(it)
        })
    }
}
