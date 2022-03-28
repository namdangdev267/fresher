package kma.longhoang.beta.fragment.delivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kma.longhoang.beta.R
import kma.longhoang.beta.adapter.ViewPagerDeliveryAdapter

class DeliveryInfoFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout(view)
        backFragment(view)
    }

    private fun setupTabLayout(view: View) {
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout_delivery)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager_delivery)
        val adapter = ViewPagerDeliveryAdapter(this)
        viewPager2?.adapter = adapter
        tabLayout?.let {
            viewPager2?.let { it1 ->
                TabLayoutMediator(
                    it, it1
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_info, container, false)
    }

    private fun backFragment(view: View) {
        view.findViewById<ImageButton>(R.id.button_back)?.setOnClickListener {
            activity?.onBackPressed()
        }
        view.findViewById<ImageButton>(R.id.button_delete)?.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}