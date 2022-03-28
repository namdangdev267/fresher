package com.misa.fresher.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.data.model.ShippingView
import com.misa.fresher.ui.shipinformation.adapter.ShipInforViewAdapter

/**
* tạo class
* @Auther : NTBao
* @date : 3/18/2022
**/
class RecipientFragment : Fragment() {
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
        initData()
        configRecyclerView(view)
    }
    /**
    *
    * @Auther : NTBao
    * @date : 3/18/2022
    **/
    private fun configRecyclerView(view: View) {
        rcv = view.findViewById(R.id.rcvShippingView)
        val adapter = ShipInforViewAdapter(list)
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