package com.misa.fresher

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.misa.fresher.Fragment.PackageFragment
import com.misa.fresher.Fragment.ReceiverFragment
import com.misa.fresher.Fragment.ShipFragment


class ViewPagerActivity : AppCompatActivity() {
    var adapterViewPager: FragmentPagerAdapter? = null
    lateinit var vpPager: ViewPager
    lateinit var tabLayout: TabLayout

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        vpPager = findViewById<ViewPager>(R.id.vpPager)
        tabLayout = findViewById<TabLayout>(R.id.tabLayout_Shipping)

        var imgBackPayment = findViewById<ImageView>(R.id.imgBackPayment)

        imgBackPayment.setOnClickListener(View.OnClickListener {
            val intent = Intent(baseContext, PaymentActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        })

        adapterViewPager = MyPagerAdapter(
            supportFragmentManager
        )
        vpPager.adapter = adapterViewPager
        tabLayout.setupWithViewPager(vpPager)
//        setTabDividers()

        vpPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    private fun setTabDividers() {
        val root: View = tabLayout.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_BEGINNING
            val drawable = GradientDrawable()
            drawable.setColor(Color.WHITE)
            drawable.setSize(2, 2)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
    }
}

class MyPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager!!) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
//            0 -> ReceiverFragment.newInstance(0, "Page # 1")
            0 -> ReceiverFragment()
//            1 -> ShipFragment.newInstance(1, "Page # 2")
            1 -> ShipFragment()
//            2 -> PackageFragment.newInstance(2, "Page # 3")
            2 -> PackageFragment()
//            else -> PackageFragment.newInstance(2, "Page # 3")
            else -> PackageFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = ""
        when (position) {
            0 -> title = "Receiver"
            1 -> title = "Ship"
            2 -> title = "Package"
        }
        return title
    }

}
