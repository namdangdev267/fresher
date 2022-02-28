package com.misa.fresher.viewpager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import androidx.viewpager.widget.ViewPager

import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.misa.fresher.R


/// https://guides.codepath.com/android/ViewPager-with-FragmentPagerAdapter

class ViewPagerActivity: AppCompatActivity() {

    var adapterViewPager: FragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)
        val vpPager = findViewById<View>(R.id.vpPager) as ViewPager

        adapterViewPager = MyPagerAdapter(supportFragmentManager)
        vpPager.adapter = adapterViewPager

        // Attach the page change listener inside the activity
        // Attach the page change listener inside the activity
        vpPager.addOnPageChangeListener(object : OnPageChangeListener {
            // This method will be invoked when a new page becomes selected.
            override fun onPageSelected(position: Int) {
                Toast.makeText(
                    this@ViewPagerActivity,
                    "Selected page position: $position", Toast.LENGTH_SHORT
                ).show()
            }

            // This method will be invoked when the current page is scrolled
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            override fun onPageScrollStateChanged(state: Int) {
                // Code goes here
            }
        })
    }

    class MyPagerAdapter(fragmentManager: FragmentManager?) :
        FragmentPagerAdapter(fragmentManager!!) {

        // Returns total number of pages
        override fun getCount(): Int {
            return NUM_ITEMS
        }

        // Returns the fragment to display for that page
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> FirstFragment.newInstance(0, "Page # 1")
                1 -> FirstFragment.newInstance(1, "Page # 2")
                2 -> SecondFragment.newInstance(2, "Page # 3")
                else -> SecondFragment.newInstance(2, "Page # 3")
            }
        }

        // Returns the page title for the top indicator
        override fun getPageTitle(position: Int): CharSequence? {
            return "Page $position"
        }

        companion object {
            private const val NUM_ITEMS = 3
        }
    }

}