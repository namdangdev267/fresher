package com.example.freshermobile.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.freshermobile.fragment.deliveryfragment.RecipientInfoFragment
import com.example.freshermobile.fragment.deliveryfragment.DetailDeliveryFragment
import com.example.freshermobile.fragment.deliveryfragment.DetailPackageFragment

class ViewPager2Adapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> RecipientInfoFragment()
            1-> DetailDeliveryFragment()
            2-> DetailPackageFragment()
            else -> RecipientInfoFragment()
        }
    }
}