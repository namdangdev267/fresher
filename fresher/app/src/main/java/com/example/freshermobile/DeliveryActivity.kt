package com.example.freshermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.freshermobile.adapter.ViewPager2Adapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DeliveryActivity : AppCompatActivity() {

    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)
        supportActionBar?.hide()
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager)
        val adapter = ViewPager2Adapter(this)
        viewPager2.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager2
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.recipient)
                1 -> tab.text = getString(R.string.delivery)
                2 -> tab.text = getString(R.string.delivery_package)
                else ->
                    tab.text = getString(R.string.recipient)
            }
        }.attach()
    }
}