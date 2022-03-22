package com.misa.fresher.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.misa.fresher.R
import com.misa.fresher.base.BaseActivity
import com.misa.fresher.databinding.ActivityMainBinding
import com.misa.fresher.ui.auth.AuthActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
    }

    fun initNavigation() {
        binding.drawerLeftMain.run {
            navToListBillFragment.setOnClickListener {
                findNavController(R.id.fragment_sale).navigate(R.id.action_fragment_sale_to_fragment_list_bills)
                toggleDrawer()
            }

            navToAuthActivity.setOnClickListener {
                startActivity(AuthActivity.getIntent(baseContext))
            }
        }
    }

    fun toggleDrawer() {
        val drawerLayout = binding.drawerLayoutMain
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else drawerLayout.openDrawer(GravityCompat.START)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }
}