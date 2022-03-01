package com.misa.fresher.Views.Fragments.ShippingInfomation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.misa.fresher.R
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

        var indicator = view.findViewById<View>(R.id.indicator)
        var tabLayout = view.findViewById<TabLayout>(R.id.tablayout_shipping_infomation)
        var viewPager = view.findViewById<ViewPager>(R.id.viewpager_shipping_infomation)
        setUpViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.post({
            val indicatorWidth  = tabLayout.getWidth() / tabLayout.getTabCount()
            val indicatorParams = indicator.getLayoutParams() as FrameLayout.LayoutParams
            indicatorParams.width = indicatorWidth
            indicator.setLayoutParams(indicatorParams)
        })


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                var indicatorWidth  = tabLayout.getWidth() / tabLayout.getTabCount()
                val params = indicator.getLayoutParams() as FrameLayout.LayoutParams

                val translationOffset: Float = (positionOffset + position) * indicatorWidth
                params.leftMargin = translationOffset.toInt()
                indicator.setLayoutParams(params)
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

    }


    fun setUpViewPager(viewPager: ViewPager){
        var adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(ReceiverFragment(),"Receiver")
        adapter.addFragment(ShipFragment(),"Ship")
        adapter.addFragment(PackageFragment(),"Package")
        viewPager.adapter = adapter

    }

    inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }

    }

}