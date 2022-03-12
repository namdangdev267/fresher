package kma.longhoang.beta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kma.longhoang.beta.adapter.ViewPagerDeliveryAdapter

class DeliveryActivity : AppCompatActivity() {
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)
        supportActionBar?.hide()
        tabLayout = findViewById(R.id.tabLayout_delivery)
        viewPager2 = findViewById(R.id.viewPager_delivery)
        val adapter = ViewPagerDeliveryAdapter(this)
        viewPager2.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager2
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.recipient)
                1 -> tab.text = getString(R.string.delivery)
                2 -> tab.text = getString(R.string.delivery_package)
                else ->
                    tab.text = getString(R.string.recipient)
            }
        }.attach()
    }
}