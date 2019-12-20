package com.example.cooky.ui.general

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.base.LockableBottomSheetBehavior
import com.example.cooky.base.SetupDrawer
import com.example.cooky.data.local.model.search.BasicSearchOption
import com.example.cooky.databinding.FragmentGeneralBinding
import com.example.cooky.ui.adapter.QuerySearchAdapter
import com.example.cooky.util.hideKeyboard
import com.example.cooky.util.setVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_general.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeneralFragment private constructor() :
    BaseFragment<FragmentGeneralBinding, GeneralViewModel>() {

    private lateinit var setupDrawer: SetupDrawer
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var searchView: SearchView
    private lateinit var navController: NavController
    private var isOnDiscoverFragment = false
    private var searchOption = BasicSearchOption()

    private val queryAdapter = QuerySearchAdapter {
        searchOption.query = it.title
        searchView.setQuery(it.title, true)
    }

    override val layoutResource: Int = R.layout.fragment_general

    override val viewModel: GeneralViewModel by viewModel()

    override fun initView() {
        initToolbar()
        initBottomSheet()
        activity?.let {
            navController = Navigation.findNavController(it, R.id.nav_host_fragment_general)
        }
        recyclerAutoComplete.adapter = queryAdapter
    }

    private fun initToolbar() {
        toolbarGeneral.apply {
            inflateMenu(R.menu.menu_general)
            title = context.getString(R.string.title_general)
            setNavigationIcon(R.drawable.ic_dehaze_black_24dp)
            setNavigationOnClickListener {
                setupDrawer.onShowDrawer()
            }
        }
        initMenu(toolbarGeneral.menu)
    }

    private fun initMenu(menu: Menu) {
        val itemSearch = menu.findItem(R.id.option_search)
        itemSearch.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                navController.navigate(R.id.destination_discover)
                isOnDiscoverFragment = true
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                if (isOnDiscoverFragment)
                    navController.popBackStack(R.id.destination_discover, true)
                return true
            }
        })

        searchView = itemSearch.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                bottomSheetBehavior.setVisible(false)
                searchView.hideKeyboard()
                isOnDiscoverFragment = true
                query?.let { searchOption.query = it }
                viewModel.searchRecipe(searchOption)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty() && it[it.length - 1] == CHAR_SPACE) {
                        viewModel.getAutoCompleteQuerys(it)

                    } else {
                        bottomSheetBehavior.setVisible(false)
                        queryAdapter.submitList(mutableListOf())
                    }
                }
                return true
            }
        })
    }

    private fun initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetGeneral).apply {
            isHideable = true
            setVisible(false)
            addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        (bottomSheetBehavior as? LockableBottomSheetBehavior)?.swipeEnabled = false
                    }
                }
            })
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.apply {
            autoCompleteQuerys.observe(viewLifecycleOwner, Observer {
                queryAdapter.submitList(it)
                bottomSheetBehavior.setVisible(true)
            })
        }
    }

    override fun setBindingVariables() {
        dataBinding.viewModel = viewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            setupDrawer = context as SetupDrawer
        } catch (e: Exception) {
            viewModel.setMessage(e.toString())
        }
    }

    companion object {
        fun newInstance() = GeneralFragment()
        private const val CHAR_SPACE = ' '
    }
}
