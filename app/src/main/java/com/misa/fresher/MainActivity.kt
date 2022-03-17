package com.misa.fresher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    var appBarConfiguration: AppBarConfiguration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configUi()
    }

    private fun configUi() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            navController.graph,
            findViewById<DrawerLayout>(R.id.drawerLayout)
        )
        appBarConfiguration.let {
            (findViewById(R.id.navSale) as NavigationView).setupWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return appBarConfiguration?.let {
            navController.navigateUp(it)
        } == true || super.onSupportNavigateUp()
    }

}
