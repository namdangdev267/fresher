package com.misa.fresher

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.misa.fresher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var appBarConfiguration: AppBarConfiguration? = null
    private val binding: ActivityMainBinding by lazy {
        getInflater(layoutInflater)
    }
    val getInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configUi()
        val img = R.drawable.ic_shopping_bag
    }


    /**
     * confic app bar
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configUi() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            navController.graph,
            binding.drawerLayout
        )
        appBarConfiguration.let {
            (binding.navSale).setupWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return appBarConfiguration?.let {
            navController.navigateUp(it)
        } == true || super.onSupportNavigateUp()
    }
    /**
    * Xử lý sự kiện mở đóng toggle
    * @Auther : NTBao
    * @date : 3/22/2022
    **/
    fun toggle(){
        val mDrawer = binding.drawerLayout
        val mNav = binding.navSale
        if(mDrawer.isDrawerOpen(mNav)){
            mDrawer.closeDrawer(mNav)
        }else{
            mDrawer.openDrawer(mNav)
        }
    }

    override fun onBackPressed() {
        val mDrawer = binding.drawerLayout
        val mNav = binding.navSale
        if (mDrawer.isDrawerOpen(mNav)) {
            mDrawer.closeDrawer(mNav)
        } else {
            super.onBackPressed()
        }
    }
}
