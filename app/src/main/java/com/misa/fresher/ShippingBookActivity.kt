package com.misa.fresher

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.databinding.ActivityShippingBookBinding
import com.misa.fresher.fragment.ShippingBookFragment


class ShippingBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShippingBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShippingBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
    }

    private fun initToolbar() {
        val toolbar = binding.shipBookToolbar
        val viewPager2 = binding.shipBookViewPager
        val tabLayout = binding.shipBookTabLayout

        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(
            ShippingBookFragment.Receiver(),
            getString(R.string.receiver_fragment_name)
        )
        viewPagerAdapter.addFragment(
            ShippingBookFragment.Shipping(),
            getString(R.string.shipping_fragment_name)
        )
        viewPagerAdapter.addFragment(
            ShippingBookFragment.Package(),
            getString(R.string.package_fragment_name)
        )
        viewPagerAdapter.addFragment(
            ShippingBookFragment.Package(),
            getString(R.string.package_fragment_name)
        )

        viewPager2.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = viewPagerAdapter.getFragmentTitle(position)
            if(!tab.isSelected) {
                when (position) {
                    0 -> {
                        tab.view.background = resources.getDrawable(R.drawable.bg_tab_selected_round_left)
                    }
                    viewPagerAdapter.itemCount - 1 -> {
                        tab.view.background = resources.getDrawable(R.drawable.bg_tab_selected_round_right)
                    }
                    else -> {
                        tab.view.background = resources.getDrawable(R.drawable.bg_tab_selected_middle)
                    }
                }
            }
        }.attach()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true;
    }

    class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        private var fragmentList: ArrayList<Fragment> = ArrayList()
        private var fragmentTitleList: ArrayList<String> = ArrayList()

        override fun getItemCount(): Int = fragmentList.size
        override fun createFragment(position: Int): Fragment = fragmentList[position]
        fun getFragmentTitle(position: Int): String = fragmentTitleList[position]
        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }
    }
}