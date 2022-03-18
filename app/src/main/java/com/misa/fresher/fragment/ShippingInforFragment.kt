package com.misa.fresher.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
    var listSelectedProduct = mutableListOf<SelectedProduct>()
    var shipInfor: ShipInfor? = null
    var model: ShipInforModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProvider(requireActivity()).get(ShipInforModel::class.java)
        return inflater.inflate(R.layout.fragment_shipping_infor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        listSelectedProduct = getList()
        getShippingInfor()
    }
    /**
     *Thiết lập các fragment vào ViewPager
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun setUpViewPager() {
        val vpPager = view?.findViewById<ViewPager>(R.id.vpPager)
        vpPager?.adapter = PageAdapter((activity as AppCompatActivity).supportFragmentManager)
        val tabLayout = view?.findViewById<TabLayout>(R.id.tabLayout)
        tabLayout?.setupWithViewPager(vpPager)
    }

    private fun getShippingInfor() {
        val btnSave = view?.findViewById<Button>(R.id.btnSave)
        btnSave?.setOnClickListener {
          model?.shipItem?.observe(viewLifecycleOwner, Observer {
              shipInfor=it
              Log.e("tesst",shipInfor?.addres.toString())
              activity?.onBackPressed()
          })
        }

    }

    private fun getList(): MutableList<SelectedProduct> {
        val list = arguments?.get("listproduct").let {
            it as MutableList<SelectedProduct>
        }
        return list
    }
}


