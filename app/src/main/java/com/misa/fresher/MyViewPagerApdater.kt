package com.misa.fresher

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.misa.fresher.fragment.Fragment_GiaoHang
import com.misa.fresher.fragment.Fragment_GoiHang
import com.misa.fresher.fragment.Fragment_NguoiNhan

class MyViewPagerApdater(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->{return "Người nhận" }
            1->{return "Giao Hàng"}
            2->{return "Gói Hàng"}
        }
        return super.getPageTitle(position)
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Fragment_NguoiNhan()
            1 -> Fragment_GiaoHang()
            2 -> Fragment_GoiHang()
            else -> Fragment_NguoiNhan()
        }
    }
}