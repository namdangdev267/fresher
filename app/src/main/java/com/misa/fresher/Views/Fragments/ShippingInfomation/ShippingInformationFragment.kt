package com.misa.fresher.Views.Fragments.ShippingInfomation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.Views.Fragments.ShippingInfomation.Package.PackageFragment
import com.misa.fresher.Views.Fragments.ShippingInfomation.Receiver.ReceiverFragment
import com.misa.fresher.Views.Fragments.ShippingInfomation.Ship.ShipFragment

class ShippingInformationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shipping_information, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var indicator = view.findViewById<View>(R.id.indicator)
        val tabLayout = view.findViewById<TabLayout>(R.id.tablayout_shipping_infomation)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewpager_shipping_infomation)


        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(ReceiverFragment(),"Receiver")
        viewPagerAdapter.addFragment(ShipFragment(),"Ship")
        viewPagerAdapter.addFragment(PackageFragment(),"Package")
        viewPager.adapter = viewPagerAdapter


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = viewPagerAdapter.getPageTitle(position)
            if(!tab.isSelected) {
                when (position) {
                    0 -> {
                        tab.view.background = resources.getDrawable(R.drawable.bg_item_tablayout_left)
                    }
                    2 -> {
                        tab.view.background = resources.getDrawable(R.drawable.bg_item_tablayout_right)
                    }
                    1 -> {
                        tab.view.background = resources.getDrawable(R.drawable.bg_item_tablayout_middle)
                    }
                }
            }
        }.attach()

    }



    inner class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()


        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getItemCount(): Int = mFragmentList.size

        override fun createFragment(position: Int): Fragment = mFragmentList[position]

        fun getPageTitle(position: Int): String
        {
            return mFragmentTitleList[position]
        }
    }

}