package com.misa.fresher.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.misa.fresher.R
import com.misa.fresher.adapter.ViewPagerAdapter

class ShippingInformationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_shipping_information,container,false)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = ViewPagerAdapter(requireFragmentManager())
        tabLayout.setupWithViewPager(viewPager)
        return view
    }
}