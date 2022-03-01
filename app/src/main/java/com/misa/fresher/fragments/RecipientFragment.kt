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
        val rcv = view.findViewById<RecyclerView>(R.id.rcvShippingView)
        val list = mutableListOf<ShippingView>(
            ShippingView.type1("Người nhận", "*", "Chạm để chọn", R.drawable.ic_add),
            ShippingView.type1("Số điện thoại", "*", "Chạm để nhập", null),
            ShippingView.type1("Địa chỉ", "*", "Chạm để nhập", null),
            ShippingView.type1("Khu vực", null, "Chạm để chọn", R.drawable.ic_forward),
            ShippingView.type1("Phường, xã", null, "Chạm để chọn", R.drawable.ic_forward),
            ShippingView.type2("Phí giao hàng thu khách", "0,0", R.drawable.ic_calculator),
            ShippingView.type3(
                "Hình thức đặt cọc",
                "Số tiền cọc",
                "Chuyển khoản",
                "0,0",
                R.drawable.ic_down
            ),
            ShippingView.type1("Kênh bán hàng", null, "Chạm để chọn", R.drawable.ic_down),
            ShippingView.type4("Thu COD (456)")
        )
        val adapter = ReceiverViewAdapter(list)
        rcv.adapter = adapter
        rcv.layoutManager = LinearLayoutManager(requireContext())
    }
}