package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.misa.fresher.ui.sale.bill.deliveryinfo.packageinfo.PackageInfoFragment
import com.misa.fresher.ui.sale.bill.deliveryinfo.receiverinfo.ReceiverInfoFragment
import com.misa.fresher.ui.sale.bill.deliveryinfo.shipinfo.ShipInfoFragment

/**
 * Adapter cho 3 tab tại màn hình thông tin giao hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
class DeliveryInfoAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ReceiverInfoFragment()
        1 -> ShipInfoFragment()
        else -> PackageInfoFragment()
    }
}