package com.misa.fresher.views.fragments.shippingInfomation.Ship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentShipBinding

class ShipFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var shipViewModel: ShipViewModel

    val binding:FragmentShipBinding by lazy {
        getInflater(layoutInflater)
    }

    val getInflater: (LayoutInflater) -> FragmentShipBinding
        get() = FragmentShipBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shipViewModel = ShipViewModel(view.context)
        binding.recyclerviewShippingShip.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerviewShippingShip.adapter = ShipAdapter(shipViewModel.listItemRecyclerView)


    }
}