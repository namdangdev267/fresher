package com.misa.fresher

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var appBarConfiguration: AppBarConfiguration? = null
    lateinit var binding: ActivityMainBinding
    private lateinit var shareViewModel: PublicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(binding.root)

        shareViewModel = ViewModelProvider(this).get(PublicViewModel::class.java)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration =
            AppBarConfiguration(navController.graph, findViewById<DrawerLayout>(R.id.drawer_layout))
        appBarConfiguration.let {
            (findViewById<NavigationView>(R.id.navSaleFragment)).setupWithNavController(
                navController
            )
        }

//        configureXML()
    }

    fun toggleDrawer(view: View) {
        if (binding.root.isDrawerOpen(view)) {
            binding.root.closeDrawer(view)
        } else {
            binding.root.openDrawer(view)
        }
    }

    override fun onBackPressed() {
        if (binding.root.isDrawerOpen(binding.navSaleFragment)) {
            toggleDrawer(binding.navSaleFragment)
        } else {
            super.onBackPressed()
        }
    }

    private fun configureXML() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHost.navController
        appBarConfiguration = AppBarConfiguration(
            navController.graph,
            findViewById<DrawerLayout>(R.id.drawerLayout)
        )
        appBarConfiguration.let {
            (findViewById<NavigationView>(R.id.navSaleFragment)).setupWithNavController(
                navController
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return appBarConfiguration?.let {
            navController.navigateUp(it)
        } == true || super.onSupportNavigateUp()
    }

}