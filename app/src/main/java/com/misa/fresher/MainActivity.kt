package com.misa.fresher

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.Fragment.Bill.ListBillFragment
import com.misa.fresher.Fragment.Sale.SaleFragment
import com.misa.fresher.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_sale.view.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
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
        val navHost =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHost.navController
        shareViewModel = ViewModelProvider(this).get(PublicViewModel::class.java)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.root.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)

        binding.navSaleFragment.setNavigationItemSelectedListener(this)

        displayScreen(-1)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_Sale -> return true
            R.id.nav_ListBill -> return true
            else -> return super.onOptionsItemSelected(item)
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

    fun displayScreen(id: Int) {
        when (id) {
            R.id.nav_Sale -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.navHostFragment, SaleFragment()).commit()
            }

            R.id.nav_ListBill -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.navHostFragment, ListBillFragment()).commit()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displayScreen(item.itemId)

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}