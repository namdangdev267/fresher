package com.misa.fresher

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return binding.navHostFragment.findNavController().navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { fragment ->
            if(fragment is NavHostFragment) {
                fragment.childFragmentManager.fragments.forEach { childFragment ->
                    if(childFragment is BaseFragment<*>) {
                        val handle = childFragment.onBackPressed()
                        if(handle) return
                    }
                }
            }
        }
        super.onBackPressed()
    }
}