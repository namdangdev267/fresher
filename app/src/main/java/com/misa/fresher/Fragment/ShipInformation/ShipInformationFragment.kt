package com.misa.fresher.Fragment.ShipInformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.Fragment.ShipInformation.Package.PackageFragment
import com.misa.fresher.Fragment.ShipInformation.Receiver.ReceiverFragment
import com.misa.fresher.Fragment.ShipInformation.Ship.ShipFragment
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentShipInformationBinding

class ShipInformationFragment: Fragment() {
    lateinit var binding: FragmentShipInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShipInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureTabLayout()
        clickEvent()
    }

    private fun configureTabLayout() {
        val tabLayout = binding.tabLayoutShipping
        val viewPager = binding.vpPager

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(ReceiverFragment(), "Receiver")
        viewPagerAdapter.addFragment(ShipFragment(), "Ship")
        viewPagerAdapter.addFragment(PackageFragment(), "Package")
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = viewPagerAdapter.getPageTitle(position)
            if (!tab.isSelected){
                when(position){
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

    private fun clickEvent() {
        binding.imgBackPayment.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}

class ViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    fun addFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getItemCount(): Int  = fragmentList.size

    override fun createFragment(position: Int): Fragment  = fragmentList[position]

    fun getPageTitle(position: Int): String = fragmentTitleList[position]
}
