package com.example.cooky.ui.general

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cooky.R
import com.example.cooky.base.BaseFragment
import com.example.cooky.base.SetupDrawer
import com.example.cooky.databinding.FragmentGeneralBinding
import com.example.cooky.util.hideKeyboard
import com.example.cooky.util.setVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_general.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class GeneralFragment() : BaseFragment<FragmentGeneralBinding, GeneralViewModel>() {

    lateinit var setupDrawer: SetupDrawer
    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    lateinit var searchView: SearchView
    lateinit var navController: NavController
    private var isOnDiscoverFragment = false

    override val layoutResource: Int = R.layout.fragment_general

    override val viewModel: GeneralViewModel by viewModel()

    override fun initView() {
        initToolbar()
        initBottomSheet()
        activity?.let {
            navController = Navigation.findNavController(it, R.id.nav_host_fragment_general)
        }
    }

    private fun initToolbar() {
        toolbarGeneral.apply {
            inflateMenu(R.menu.menu_general)
            title = context.getString(R.string.title_general)
            setNavigationIcon(R.drawable.ic_dehaze_black_24dp)
            setNavigationOnClickListener {
                setupDrawer.onShowDrawer()
            }
            setOnMenuItemClickListener {
                if (it.itemId == R.id.option_change_label) {
                    if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
                        searchView.hideKeyboard()
                        bottomSheetBehavior.setVisible(true)
                    } else {
                        bottomSheetBehavior.setVisible(false)
                    }
                }
                true
            }
        }

        initMenu(toolbarGeneral.menu)
    }

    private fun initMenu(menu: Menu) {
        val itemSearch = menu.findItem(R.id.option_search)
        val itemLabel = menu.findItem(R.id.option_change_label)
        itemSearch.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                bottomSheetBehavior.setVisible(true)
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                bottomSheetBehavior.setVisible(false)
                itemLabel.setVisible(false)
                if (isOnDiscoverFragment)
                    navController.popBackStack(R.id.destination_discover, true)
                return true
            }
        })

        searchView = itemSearch.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                itemLabel.setVisible(true)
                bottomSheetBehavior.setVisible(false)
                searchView.hideKeyboard()
                navController.navigate(R.id.destination_discover)
                isOnDiscoverFragment = true
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetGeneral).apply {
            isHideable = true
            setVisible(false)
            setBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        bottomSheetBehavior.setVisible(true)
                    }
                }
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
    }
}
