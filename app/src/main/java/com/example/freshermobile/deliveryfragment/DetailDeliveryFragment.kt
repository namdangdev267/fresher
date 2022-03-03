package com.example.freshermobile.deliveryfragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freshermobile.R
import com.example.freshermobile.adapter.DeliveryAdapter
import com.example.freshermobile.model.DeliveryModel
import java.time.Instant
import java.util.*

class DetailDeliveryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView_delivery)
        val list = mutableListOf<DeliveryModel>(
            DeliveryModel.RadioModel(null, "Tổ chức", "Cá nhân"),
            DeliveryModel.EnterEditText("Đối tác giao hàng", null, getString(R.string.tap_to_select), R.drawable.ic_baseline_keyboard_arrow_down_24),
            DeliveryModel.RadioModel("Hình thức giao hàng", "Bưu cục đến lấy", "Gửi hàng tại bưu cục"),
            DeliveryModel.EnterTextView("Địa chỉ lấy hàng", null, "namfashion", R.drawable.ic_baseline_keyboard_arrow_down_24),
            DeliveryModel.EnterEditText("Loại dịch vụ", null, getString(R.string.tap_to_select), R.drawable.ic_baseline_keyboard_arrow_down_24),
            DeliveryModel.EnterTextView("Phí giao hàng trả đối tác", null, "0,0", R.drawable.ic_baseline_calculate_24),
            DeliveryModel.EnterEditText("Mã vận đơn", null, getString(R.string.tap_to_enter), null),
            DeliveryModel.EnterEditText("Ghi chú giao hàng", null, getString(R.string.tap_to_enter), null),
            DeliveryModel.EnterTextView("Ngày giao hàng", getString(R.string.star), Date.from(Instant.now()).toString(), null),
        )
        val adapter = DeliveryAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_delivery, container, false)
    }


}