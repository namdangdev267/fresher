package com.misa.fresher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    var appBarConfiguration : AppBarConfiguration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configUI()
        onLickMenuNav()
    }

    private fun configUI(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navConTroller = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navConTroller.graph,
        findViewById<DrawerLayout>(R.id.drawerLayout))
        appBarConfiguration.let {
            (findViewById(R.id.navSale) as NavigationView).setupWithNavController(navConTroller)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navConTroller = findNavController(R.id.fragmentContainerView)
        return appBarConfiguration?.let {
            navConTroller.navigateUp(it)
        } ==true || super.onSupportNavigateUp()
    }
    private fun onLickMenuNav(){
        val navView  = findViewById<NavigationView>(R.id.navSale)
        navView?.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_sale -> Toast.makeText(this,"SaleFragment", Toast.LENGTH_SHORT).show()
                R.id.nav_pay -> Toast.makeText(this,"PayFragment", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}