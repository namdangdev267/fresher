package com.misa.fresher.Fragment.ShipInformation.Package

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemShipInfor
import com.misa.fresher.R

class PackageFragment: Fragment() {
    var listItemShip = mutableListOf<ItemShipInfor>()
    private var packageViewModel: PackageViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_package, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        packageViewModel = PackageViewModel(view.context)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview_shipping_package)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = PackageAdapter(packageViewModel!!.listItemShip)
    }

}