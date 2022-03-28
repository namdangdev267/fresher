package com.misa.fresher.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment()
            else -> FirstFragment()
        }

}