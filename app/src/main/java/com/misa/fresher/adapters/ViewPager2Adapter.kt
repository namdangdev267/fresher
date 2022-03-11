package com.misa.fresher.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.misa.fresher.fragments.ShipInforFragment

class ViewPager2Adapter(fragment: ShipInforFragment) : FragmentStateAdapter(fragment) {
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