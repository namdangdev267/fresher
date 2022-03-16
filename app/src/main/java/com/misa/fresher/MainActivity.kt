package com.misa.fresher

import androidx.core.view.GravityCompat
import com.misa.fresher.base.BaseActivity
import com.misa.fresher.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    fun toggleDrawer() {
        val drawerLayout = binding.drawerLayoutMain
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else drawerLayout.openDrawer(GravityCompat.START)
    }
}