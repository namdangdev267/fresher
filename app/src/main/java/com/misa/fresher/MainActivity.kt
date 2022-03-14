package com.misa.fresher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    var appBarConfiguration: AppBarConfiguration? = null
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        configureXML()
    }

    fun toggleDrawer(){
//        if (binding?.drawerLayout?.isDrawerOpen(binding!!.navSaleFragment))
        if (binding!!.drawerLayout.isDrawerOpen(binding!!.navSaleFragment)){
            binding!!.drawerLayout.closeDrawer(binding!!.navSaleFragment)
        } else{
            binding!!.drawerLayout.openDrawer(binding!!.navSaleFragment)
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
            (findViewById(R.id.navSaleFragment) as NavigationView).setupWithNavController(
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