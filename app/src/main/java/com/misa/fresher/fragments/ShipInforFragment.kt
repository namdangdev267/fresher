package com.misa.fresher.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.adapters.ViewPager2Adapter

class ShipInforFragment : Fragment() {
    private lateinit var globalView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ship_infor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        globalView = view
        configUi()
        buttonEvent()
    }

    private fun buttonEvent() {
        globalView.findViewById<ImageButton>(R.id.btnBack).setOnClickListener{
            findNavController().navigate(R.id.action_shipInforFragment_to_saleFragment)
        }
    }

    private fun configUi() {
        val ViewAdapter = ViewPager2Adapter(this)
        ViewAdapter.addFragment(RecipientFragment(), "Người nhận")
        ViewAdapter.addFragment(ShipFragment(), "Giao hàng")
        ViewAdapter.addFragment(PackageFragment(), "Gói Hàng")
        val viewPager = globalView.findViewById<ViewPager2>(R.id.viewPage2)
        viewPager?.adapter = ViewAdapter
        val tabLayout = globalView.findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = ViewAdapter.getFragmentTittle(position)
            if (tab.isSelected) {
                when (position) {
                    0 -> {
                        tab.view.background = AppCompatResources.getDrawable(
                            requireContext(),
                            R.drawable.tab_selected_left
                        )
                    }
                    2 -> {
                        tab.view.background = AppCompatResources.getDrawable(
                            requireContext(),
                            R.drawable.tab_selected_right
                        )
                    }
                    else -> {
                        tab.view.background = AppCompatResources.getDrawable(
                            requireContext(),
                            R.drawable.tab_selected_middle
                        )
                    }
                }
            }
        }.attach()
    }
}