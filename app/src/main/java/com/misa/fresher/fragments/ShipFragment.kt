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

class ShipFragment : Fragment() {
    private var list = mutableListOf<ShippingView>()
    private var rcv: RecyclerView? = null
    private var globalView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ship, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        globalView = view
        initData()
        configRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun configRecyclerView() {
        rcv = globalView?.findViewById(R.id.rcvShippingView_ship)
        val adpter = ReceiverViewAdapter(list)
        adpter.notifyDataSetChanged()
        rcv?.adapter = adpter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initData() {
        list = mutableListOf<ShippingView>(
            ShippingView.RadionGroup("Tổ chức", "Cá nhân"),
            ShippingView.TouchTextView(
                "Đối tác giao hàng",
                null,
                "Chạm để chọn",
                R.drawable.ic_down
            ),
            ShippingView.TouchTextView("Loại dịch vụ", null, "Chạm để chọn", null),
            ShippingView.TouchEditText(
                "Phí giao hàng trả đối tác",
                "0,0",
                R.drawable.ic_calculator
            ),
            ShippingView.TouchTextView("Mã vận đơn", null, "Chạm để nhập", null),
            ShippingView.TouchTextView("Loại dịch vụ", null, "Chạm để nhập", null),
            ShippingView.TouchEditText("Ngày giao hàng", "02/03/2022", null)
        )
    }
}