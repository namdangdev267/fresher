package com.misa.fresher.Fragment.ShipInformation.Receiver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.Models.ItemShipInfor
import com.misa.fresher.Fragment.ShipInformation.ShipInforViewModel
import com.misa.fresher.databinding.FragmentReceiverBinding

class ReceiverFragment: Fragment() {
    lateinit var binding: FragmentReceiverBinding
    lateinit var listItemShip: MutableList<ItemShipInfor>
    lateinit var shipInforViewModel: ShipInforViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shipInforViewModel = ShipInforViewModel(view.context)
        val rcvReceiver = binding.rcvReceiver
        rcvReceiver.layoutManager = LinearLayoutManager(this.context)
        rcvReceiver.adapter = ReceiverAdapter(shipInforViewModel.listItemShip)
    }
}