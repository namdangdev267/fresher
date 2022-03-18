package com.misa.fresher.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.misa.fresher.fragment.PackageFagment
import com.misa.fresher.fragment.ReceiverFragment
import com.misa.fresher.fragment.ShipFragment

class ViewPagerAdapter(fragment :FragmentManager) : FragmentPagerAdapter(fragment) {
    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return "Người nhận"
            1->return "Giao Hàng"
            2->return "Gói Hàng"
        }
        return super.getPageTitle(position)
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ReceiverFragment()
            1 -> ShipFragment()
            2 -> PackageFagment()
            else -> ReceiverFragment()
        }
    }
}