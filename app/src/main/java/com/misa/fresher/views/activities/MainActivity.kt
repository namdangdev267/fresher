package com.misa.fresher.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.R
import com.misa.fresher.data.dao.itembill.ItemBillDao
import com.misa.fresher.data.database.AppDatabase
import com.misa.fresher.data.repositories.BillRepository
import com.misa.fresher.views.fragments.SharedViewModel
import com.misa.fresher.databinding.ActivityMainBinding
import com.misa.fresher.views.fragments.ShareViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    lateinit var sharedViewModel: SharedViewModel
    lateinit var binding: ActivityMainBinding
    var appBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val billDao = ItemBillDao(AppDatabase.getInstance(this))
        val billRepository  = BillRepository(billDao)
        val factory = ShareViewModelFactory(billRepository)
        sharedViewModel = ViewModelProvider(this,factory).get(SharedViewModel::class.java)
        configDrawer()
    }

    override fun onBackPressed() {
        if (binding.root.isDrawerOpen(binding.nvMenu)) {
            toggleDrawer(binding.nvMenu)
        } else {
            super.onBackPressed()
        }
    }

    private fun configDrawer() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        appBarConfiguration.let { binding.nvMenu.setupWithNavController(navController) }
    }

    fun toggleDrawer(view: View) {
        if (binding.root.isDrawerOpen(view)) {
            binding.root.closeDrawer(view)
        } else {
            binding.root.openDrawer(view)
        }
    }

}