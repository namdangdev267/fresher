package com.misa.fresher.Views.Fragments.ShippingInfomation.Package

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemRecyclerView
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentPackageBinding
import com.misa.fresher.databinding.FragmentSaleBinding

class PackageFragment : Fragment() {
    lateinit var binding:FragmentPackageBinding
    lateinit var recyclerView: RecyclerView
    lateinit var packageViewModel: PackageViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel(context)
    }

    private fun initViewModel(context: Context) {
        packageViewModel = PackageViewModel(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_package, container, false)

        binding = FragmentPackageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configListView()
    }

    private fun configListView() {
        binding.recyclerviewShippingPackage.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerviewShippingPackage.adapter = PackageAdapter(packageViewModel.listItemRecyclerView)
    }

}