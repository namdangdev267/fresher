package com.misa.fresher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.misa.fresher.adapters.ViewPagerAdapter
import com.misa.fresher.fragments.PackageFragment
import com.misa.fresher.fragments.RecipientFragment
import com.misa.fresher.fragments.ShipFragment

class TabLayoutExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)
        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(RecipientFragment(), "Người nhận")
        adapter.addFragment(ShipFragment(), "Giao hàng")
        adapter.addFragment(PackageFragment(), "Gói hàng")
        val viewPage = findViewById<ViewPager>(R.id.viewPage)
        viewPage.adapter = adapter
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPage)

    }
}