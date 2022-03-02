package com.misa.fresher.ui.sale.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.misa.fresher.ui.sale.deliveryinfo.info.PackageInfoFragment
import com.misa.fresher.ui.sale.deliveryinfo.info.ReceiverInfoFragment
import com.misa.fresher.ui.sale.deliveryinfo.info.ShipInfoFragment

class DeliveryInfoAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ReceiverInfoFragment()
        1 -> ShipInfoFragment()
        else -> PackageInfoFragment()
    }
}