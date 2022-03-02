package com.misa.fresher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.adapters.ViewPager2Adapter
import com.misa.fresher.adapters.ViewPagerAdapter
import com.misa.fresher.fragments.PackageFragment
import com.misa.fresher.fragments.RecipientFragment
import com.misa.fresher.fragments.ShipFragment

class TabLayoutExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)
        innit()
    }

    private fun innit() {
        val adapter = ViewPager2Adapter(this)
        adapter.addFragment(RecipientFragment(), "Người nhận")
        adapter.addFragment(ShipFragment(), "Giao hàng")
        adapter.addFragment(PackageFragment(), "Gói Hàng")
        val viewPager = findViewById<ViewPager2>(R.id.viewPage2)
        viewPager.adapter = adapter
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getFragmentTittle(position)
            if (tab.isSelected) {
                when (position) {
                    0 -> {
                        tab.view.background = resources.getDrawable(R.drawable.tab_selected_left)
                    }
                    2 -> {
                        tab.view.background = resources.getDrawable(R.drawable.tab_selected_right)
                    }
                    else -> {
                        tab.view.background = resources.getDrawable(R.drawable.tab_selected_middle)
                    }
                }
            }
        }.attach()
    }

}