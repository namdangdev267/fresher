package com.misa.fresher.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapters.ReceiverViewAdapter
import com.misa.fresher.model.ShippingView

class RecipientFragment : Fragment() {
    private var globalView: View? = null
    private var rcv: RecyclerView? = null
    private var list = mutableListOf<ShippingView>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_recipient, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        globalView = view
        initData()
        configRecyclerView()
    }

    private fun configRecyclerView() {
        rcv = globalView?.findViewById(R.id.rcvShippingView)
        val adapter = ReceiverViewAdapter(list)
        adapter.notifyDataSetChanged()
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initData() {
        list = mutableListOf(
            ShippingView.TouchTextView("Người nhận", "*", "Chạm để chọn", R.drawable.ic_add),
            ShippingView.TouchTextView("Số điện thoại", "*", "Chạm để nhập", null),
            ShippingView.TouchTextView("Địa chỉ", "*", "Chạm để nhập", null),
            ShippingView.TouchTextView("Khu vực", null, "Chạm để chọn", R.drawable.ic_forward),
            ShippingView.TouchTextView("Phường, xã", null, "Chạm để chọn", R.drawable.ic_forward),
            ShippingView.TouchEditText("Phí giao hàng thu khách", "0,0", R.drawable.ic_calculator),
            ShippingView.TwoCol(
                "Hình thức đặt cọc",
                "Số tiền cọc",
                "Chuyển khoản",
                "0,0",
                R.drawable.ic_down
            ),
            ShippingView.TouchTextView("Kênh bán hàng", null, "Chạm để chọn", R.drawable.ic_down),
            ShippingView.CheckBox("Thu COD (456)")
        )
    }
}