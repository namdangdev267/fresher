package com.misa.fresher.ui

import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.misa.fresher.R
import com.misa.fresher.core.BaseActivity
import com.misa.fresher.databinding.ActivityMainBinding

/**
 * Activity chính chứa hầu hết các view của ứng dụng
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 1
 * @updated 3/9/2022: Tạo class
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val getInflater: (inflater: LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private var appBarConfiguration: AppBarConfiguration? = null

    override fun initUI() {
        configDrawer()
    }

    /**
     * Cài đặt navigation drawer
     *
     * @author Nguyễn Công Chính
     * @since 3/9/2022
     *
     * @version 1
     * @updated 3/9/2022: Tạo function
     */
    private fun configDrawer() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph, binding.root)
        appBarConfiguration?.let {
            binding.nvMenu.setupWithNavController(navController)
        }
    }

    /**
     * Đóng mở navigation drawer
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    fun toggleDrawer() {
        if (binding.root.isDrawerOpen(binding.nvMenu)) {
            binding.root.closeDrawer(binding.nvMenu)
        } else {
            binding.root.openDrawer(binding.nvMenu)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)

        return appBarConfiguration?.let { navController.navigateUp(it) } == true || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.root.isDrawerOpen(binding.nvMenu)) {
            toggleDrawer()
        } else {
            super.onBackPressed()
        }
    }
}