package com.misa.fresher.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.ShipInforModel
import com.misa.fresher.model.SelectedProduct
import com.misa.fresher.model.ShipInfor
import com.misa.fresher.viewpager.PackageFragment
import com.misa.fresher.viewpager.PageAdapter
import com.misa.fresher.viewpager.RecipentFragment
import com.misa.fresher.viewpager.ShipFragment

class ShippingInforFragment : Fragment() {
    var shipInfor: ShipInfor? = null
    val model: ShipInforModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shipping_infor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager(view)
        getShippingInfor(view)
    }

    /**
     *Thiết lập các fragment vào ViewPager
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun setUpViewPager(view: View) {
        val adapter=PageAdapter(this)
        adapter.addFragment(RecipentFragment(),resources.getString(R.string.receiver))
        adapter.addFragment(ShipFragment(), resources.getString(R.string.shipping))
        adapter.addFragment(PackageFragment(),resources.getString(R.string.packag))
        val vpShipInfo = view.findViewById<ViewPager2>(R.id.vpPager)
        vpShipInfo.adapter = adapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, vpShipInfo) { tab, position ->
            tab.text = adapter.getFragmentTittle(position)
            if (!tab.isSelected) {
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
                            R.drawable.tab_selected_mid
                        )
                    }
                }
            }
        }.attach()

    }

    private fun getShippingInfor(view: View) {
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val imbBack = view.findViewById<ImageButton>(R.id.imb_back)
        model.shipItem.observe(viewLifecycleOwner, Observer {
            shipInfor = it
        })
        btnSave?.setOnClickListener {
            activity?.onBackPressed()
        }
        imbBack?.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}


