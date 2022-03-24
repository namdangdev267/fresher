package com.misa.fresher.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
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
        getShippingInfor()
    }
    /**
     *Thiết lập các fragment vào ViewPager
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun setUpViewPager(view: View) {
        val vpPager = view.findViewById<ViewPager>(R.id.vpPager)
        vpPager.adapter = PageAdapter((activity as AppCompatActivity).supportFragmentManager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        tabLayout?.setupWithViewPager(vpPager)
    }

    private fun getShippingInfor() {
        val btnSave = view?.findViewById<Button>(R.id.btnSave)
        model.shipItem.observe(viewLifecycleOwner, Observer {
            shipInfor=it
        })
        btnSave?.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}


