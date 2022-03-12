package com.misa.fresher.fragments

import android.annotation.SuppressLint
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
    private var globalView: View? = null
    private var rcv: RecyclerView? = null
    private var list = mutableListOf<ShippingView>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_package, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        globalView = view
        initData()
        configRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun configRecyclerView() {
        rcv = globalView?.findViewById(R.id.rcvShippingView_package)
        val adapter = ReceiverViewAdapter(list)
        adapter.notifyDataSetChanged()
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initData() {
        list = mutableListOf(
            ShippingView.TouchEditText("Trọng lượng (g)", "300", null),
            ShippingView.ThreeCol("Kích thước (cm)", "10", "10", "10")
        )
    }
}