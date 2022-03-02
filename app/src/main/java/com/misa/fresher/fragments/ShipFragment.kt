package com.misa.fresher.fragments

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ship, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rcv = view.findViewById<RecyclerView>(R.id.rcvShippingView_ship)
        val list = mutableListOf<ShippingView>(
            ShippingView.type5("Tổ chức","Cá nhân"),
            ShippingView.type1("Đối tác giao hàng",null,"Chạm để chọn",R.drawable.ic_down),
            ShippingView.type1("Loại dịch vụ",null,"Chạm để chọn",null),
            ShippingView.type2("Phí giao hàng trả đối tác","0,0",R.drawable.ic_calculator),
            ShippingView.type1("Mã vận đơn",null,"Chạm để nhập",null),
            ShippingView.type1("Loại dịch vụ",null,"Chạm để nhập",null),
            ShippingView.type2("Ngày giao hàng","02/03/2022",null)
        )
        val adpter = ReceiverViewAdapter(list)
        rcv.adapter=adpter
        rcv.layoutManager=LinearLayoutManager(requireContext())
    }
}