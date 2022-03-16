package kma.longhoang.beta.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kma.longhoang.beta.fragment.delivery.DeliveryFragment
import kma.longhoang.beta.fragment.delivery.PackageFragment
import kma.longhoang.beta.fragment.delivery.RecipientFragment
class ViewPagerDeliveryAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> RecipientFragment()
            1-> DeliveryFragment()
            2-> PackageFragment()
            else -> RecipientFragment()
        }
    }
}