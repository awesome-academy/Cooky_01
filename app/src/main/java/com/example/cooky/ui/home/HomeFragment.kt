package com.example.cooky.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cooky.R
import com.example.cooky.ui.favorite.FavoriteFragment
import com.example.cooky.ui.general.GeneralFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initViewPager()
        tabLayoutHome.setupWithViewPager(viewPagerHome)
    }

    private fun initViewPager() {
        val generalFragment = GeneralFragment.newInstance()
        val favoriteFragment = FavoriteFragment.newInstance()
        fragmentManager.let {
            viewPagerHome.adapter = PagerAdapter(
                listOf(generalFragment, favoriteFragment),
                listOf(GENERAL_FRAGMENT_TITLE, FAVORITE_FRAGMENT_TITLE),
                childFragmentManager
            )
        }
        viewPagerHome.swipeEnabled = false
    }

    companion object {
        fun newInstance() = HomeFragment()
        const val GENERAL_FRAGMENT_TITLE = "Home"
        const val FAVORITE_FRAGMENT_TITLE = "Favorite"
    }
}
