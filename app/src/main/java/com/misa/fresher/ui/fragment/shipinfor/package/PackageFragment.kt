package com.misa.fresher.ui.fragment.shipinfor.`package`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentPackageBinding

class PackageFragment(private var packageViewModel: PackageViewModel) : Fragment() {
    private val binding: FragmentPackageBinding by lazy { getInflater(layoutInflater) }

    val getInflater: (LayoutInflater) -> FragmentPackageBinding get() = FragmentPackageBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        packageViewModel = PackageViewModel(view.context)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview_shipping_package)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = PackageAdapter(packageViewModel.listItemShip)
    }

}