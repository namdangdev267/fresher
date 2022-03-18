package com.misa.fresher.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.R
import com.misa.fresher.views.fragments.SharedViewModel
import com.misa.fresher.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var sharedViewModel: SharedViewModel
    lateinit var binding: ActivityMainBinding
    var appBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        configDrawer()
    }


    private fun configDrawer() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, findViewById<DrawerLayout>(R.id.drawer_layout))
        appBarConfiguration.let {
            (findViewById<NavigationView>(R.id.nv_menu)).setupWithNavController(
                navController
            )
        }
    }

    fun toggleDrawer(view: View) {
        if (binding.root.isDrawerOpen(view)) {
            binding.root.closeDrawer(view)
        } else {
            binding.root.openDrawer(view)
        }
    }


}