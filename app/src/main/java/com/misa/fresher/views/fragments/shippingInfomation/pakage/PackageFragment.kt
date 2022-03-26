package com.misa.fresher.views.fragments.shippingInfomation.pakage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.databinding.FragmentPackageBinding

class PackageFragment : Fragment() {
//    lateinit var binding:FragmentPackageBinding
    lateinit var recyclerView: RecyclerView
    lateinit var packageViewModel: PackageViewModel

    val binding:FragmentPackageBinding by lazy {
        getInflater(layoutInflater)
    }

    val getInflater: (LayoutInflater) -> FragmentPackageBinding
        get() = FragmentPackageBinding::inflate

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