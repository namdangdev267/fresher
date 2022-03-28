package com.misa.fresher.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
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
import com.misa.fresher.viewpager.PageAdapter

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
        val vpShipInfo = view.findViewById<ViewPager2>(R.id.vpPager)
        vpShipInfo.adapter = PageAdapter(this)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, vpShipInfo) { tab, position ->
            when (position) {
                0 -> tab.text = "Người nhận"
                1 -> tab.text = "Giao hàng"
                2 -> tab.text = "Gói hàng"
            }
        }.attach()
    }

    private fun getShippingInfor(view: View) {
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val imbBack=view.findViewById<ImageButton>(R.id.imb_back)
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


