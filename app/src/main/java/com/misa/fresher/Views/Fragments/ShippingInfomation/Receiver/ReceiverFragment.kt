package com.misa.fresher.Views.Fragments.ShippingInfomation.Receiver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.InforShip
import com.misa.fresher.Models.ItemRecyclerView
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentReceiverBinding
import com.misa.fresher.databinding.FragmentSaleBinding

class ReceiverFragment : Fragment() {

    lateinit var binding: FragmentReceiverBinding
    lateinit var recyclerView: RecyclerView
    lateinit var listItemRecyclerView: MutableList<ItemRecyclerView>
    lateinit var receiverViewModel: ReceiverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiverViewModel = ReceiverViewModel(view.context)
        recyclerView = view.findViewById(R.id.recyclerview_shipping_receiver)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        var inforShip = InforShip("","","","","",0f,null,0f,null,false)
        recyclerView.adapter = ReceiverAdapter(receiverViewModel.listItemRecyclerView,inforShip)


    }

}

