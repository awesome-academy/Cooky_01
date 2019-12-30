package com.example.cooky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.cooky.base.SetupDrawer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SetupDrawer {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(nav_view, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.destination_home) {
                onUnLockDrawer()
            } else {
                onLockDrawer()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onShowDrawer() {
        layoutDrawer.openDrawer(GravityCompat.START)
    }

    override fun onHideDrawer() {
        layoutDrawer.closeDrawer(GravityCompat.START)
    }

    override fun onLockDrawer() {
        layoutDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun onUnLockDrawer() {
        layoutDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
}
