package com.misa.fresher.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapters.ReceiverViewAdapter
import com.misa.fresher.model.ShippingView

class PackageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_package, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rcv = view.findViewById<RecyclerView>(R.id.rcvShippingView_package)
        val list = mutableListOf<ShippingView>(
            ShippingView.type2("Trọng lượng (g)", "300", null),
            ShippingView.type6("Kích thước (cm)", "10", "10", "10")
        )
        val adapter = ReceiverViewAdapter(list)
        rcv.adapter = adapter
        rcv.layoutManager = LinearLayoutManager(requireContext())
    }
}