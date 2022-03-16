package com.misa.fresher

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.misa.fresher.Models.Product
import com.misa.fresher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var appBarConfiguration: AppBarConfiguration? = null
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureXML()
    }

    fun toggleDrawer(view: View) {
        if (binding.root.isDrawerOpen(view)) {
            binding.root.closeDrawer(view)
        } else {
            binding.root.openDrawer(view)
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
            binding.navSaleFragment.setupWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return appBarConfiguration?.let {
            navController.navigateUp(it)
        } == true || super.onSupportNavigateUp()
    }

}