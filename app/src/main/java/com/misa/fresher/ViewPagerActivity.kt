package com.misa.fresher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.misa.fresher.viewpager.PageAdapter

class ViewPagerActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)
       val vpPager=findViewById<ViewPager>(R.id.vpPager)
        vpPager.adapter= PageAdapter(supportFragmentManager)
        val tabLayout=findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(vpPager)
    }
}