package com.misa.fresher.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    private val mFragmentList = ArrayList<Fragment>()

    private val mFragmentTittle = ArrayList<String>()

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun getFragmentTittle(position: Int): String = mFragmentTittle[position]
    fun addFragment(fragment: Fragment, tittle: String) {
        mFragmentList.add(fragment)
        mFragmentTittle.add(tittle)
    }
}