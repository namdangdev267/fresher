package com.misa.fresher.Views.Fragments.ShippingInfomation.Receiver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemShip
import com.misa.fresher.R

class ReceiverFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var listItemShip:MutableList<ItemShip>
    lateinit var receiverViewModel:ReceiverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_receiver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiverViewModel= ReceiverViewModel(view.context)
        recyclerView = view.findViewById(R.id.recyclerview_receiver_shipping)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = ReceiverAdapter(receiverViewModel.listItemShip)


    }

}

