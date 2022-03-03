package com.misa.fresher.Views.Fragments.ShippingInfomation.Package

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

class PackageFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var listItemShip: MutableList<ItemShip>
    lateinit var packageViewModel: PackageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_package, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        packageViewModel = PackageViewModel(view.context)
        recyclerView = view.findViewById(R.id.recyclerview_shipping_package)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = PackageAdapter(packageViewModel.listItemShip)
    }

}