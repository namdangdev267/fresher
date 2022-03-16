package com.misa.fresher.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapters.ReceiverViewAdapter
import com.misa.fresher.model.Receiver
import com.misa.fresher.model.ShippingView
import com.misa.fresher.viewModel.ShipInforViewModel

class RecipientFragment : Fragment() {
    private var rcv: RecyclerView? = null
    private var list = mutableListOf<ShippingView>()
    private var receiver = Receiver("", "", "", "", "", "", "", "", "")
    private val viewModel: ShipInforViewModel by activityViewModels()
    var name = ""
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
        initData()
        configRecyclerView(view)
    }

    private fun configRecyclerView(view: View) {
        rcv = view.findViewById(R.id.rcvShippingView)
        val adapter = ReceiverViewAdapter(list, { getData(it) })
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun getData(it: ShippingView) {
        it as ShippingView.TouchTextView
        if (it.tittle == "Người nhận") {
            receiver.name = it.hint
        }
        if (it.tittle == "Số điện thoại") {
            receiver.phoneNumber = it.hint
        }
        if (it.tittle == "Địa chỉ") {
            receiver.address = it.hint
        }
        if (it.tittle == "Khu vực") {
            receiver.area = it.hint
        }
        if (it.tittle == "Phường, xã") {
            receiver.ward = it.hint
        }
        viewModel.add(receiver)

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