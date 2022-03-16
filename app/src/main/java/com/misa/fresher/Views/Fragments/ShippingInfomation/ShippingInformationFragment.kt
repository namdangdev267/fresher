package com.misa.fresher.Views.Fragments.ShippingInfomation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.Views.Fragments.ShippingInfomation.Package.PackageFragment
import com.misa.fresher.Views.Fragments.ShippingInfomation.Receiver.ReceiverFragment
import com.misa.fresher.Views.Fragments.ShippingInfomation.Ship.ShipFragment
import com.misa.fresher.databinding.FragmentBillDetailBinding
import com.misa.fresher.databinding.FragmentShippingInformationBinding

class ShippingInformationFragment : Fragment() {

    lateinit var binding: FragmentShippingInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShippingInformationBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivShipInforBack.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_shippingInformationFragment_to_billDetailFragment)
        }


        val tabLayout = binding.tablayoutShippingInfomation
        val viewPager = binding.viewpagerShippingInfomation

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(ReceiverFragment(), "Receiver")
        viewPagerAdapter.addFragment(ShipFragment(), "Ship")
        viewPagerAdapter.addFragment(PackageFragment(), "Package")
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = viewPagerAdapter.getPageTitle(position)
            if (!tab.isSelected) {
                when (position) {
                    0 -> {
                        tab.view.background =
                            resources.getDrawable(R.drawable.bg_item_tablayout_left)
                    }
                    2 -> {
                        tab.view.background =
                            resources.getDrawable(R.drawable.bg_item_tablayout_right)
                    }
                    1 -> {
                        tab.view.background =
                            resources.getDrawable(R.drawable.bg_item_tablayout_middle)
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

        fun getPageTitle(position: Int): String {
            return mFragmentTitleList[position]
        }
    }

}