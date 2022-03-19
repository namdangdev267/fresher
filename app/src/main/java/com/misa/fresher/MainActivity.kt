package com.misa.fresher

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.misa.fresher.base.BaseActivity
import com.misa.fresher.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.drawerLeftMain.navToListBillFragment.setOnClickListener {
            findNavController(R.id.fragment_sale).navigate(R.id.action_fragment_sale_to_fragment_list_bills)
            toggleDrawer()
        }
    }

    fun toggleDrawer() {
        val drawerLayout = binding.drawerLayoutMain
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else drawerLayout.openDrawer(GravityCompat.START)
    }
}