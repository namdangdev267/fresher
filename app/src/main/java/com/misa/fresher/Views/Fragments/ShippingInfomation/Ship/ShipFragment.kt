package com.misa.fresher.Views.Fragments.ShippingInfomation.Ship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemShip
import com.misa.fresher.R
import com.misa.fresher.Views.Fragments.ShippingInfomation.Receiver.ReceiverAdapter
import com.misa.fresher.Views.Fragments.ShippingInfomation.Receiver.ReceiverViewModel

class ShipFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var listItemShip: MutableList<ItemShip>
    lateinit var shipViewModel: ShipViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ship, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shipViewModel = ShipViewModel(view.context)
        recyclerView = view.findViewById(R.id.recyclerview_shipping_ship)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = ShipAdapter(shipViewModel.listItemShip)


    }



}