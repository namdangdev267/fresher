package com.misa.fresher.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapters.ShipInforViewAdapter
import com.misa.fresher.model.Receiver
import com.misa.fresher.model.ShippingView
import com.misa.fresher.viewModel.ShipInforViewModel
/**
* tạo class
* @Auther : NTBao
* @date : 3/18/2022
**/
class RecipientFragment : Fragment() {
    private var rcv: RecyclerView? = null
    private var list = mutableListOf<ShippingView>()
    private var receiver = getReceiver()
    private val viewModel: ShipInforViewModel by activityViewModels()
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
        val adapter = ShipInforViewAdapter(list, { getData(it) })
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun getData(it: ShippingView) {
        when(it){
            is ShippingView.TouchTextView -> {
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
            }
            is ShippingView.TouchEditText -> {} // chưa làm xong
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

    private fun getReceiver(
        name: String = "",
        phoneNum: String = "",
        adress: String = "",
        area: String = "",
        ward: String = "",
        shipPayByCust: String = "",
        depositMethod: String = "",
        deposit: String = "",
        saleChannel: String = "",
    ): Receiver {
        val receiver = Receiver(
            name,
            phoneNum,
            adress,
            area,
            ward,
            shipPayByCust,
            depositMethod,
            deposit,
            saleChannel
        )
        return receiver
    }
}