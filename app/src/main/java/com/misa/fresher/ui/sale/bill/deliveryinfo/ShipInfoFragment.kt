package com.misa.fresher.ui.sale.bill.deliveryinfo

import android.text.InputType
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.model.*
import com.misa.fresher.databinding.FragmentShipInfoBinding
import com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.DeliveryInputAdapter
import com.misa.fresher.util.getDrawableById

/**
 * Màn nhập thông tin liên quan đến giao vận
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
class ShipInfoFragment: BaseFragment<FragmentShipInfoBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentShipInfoBinding
        get() = FragmentShipInfoBinding::inflate
    private var adapter: DeliveryInputAdapter? = null

    override fun initUI() {
        configRcv()
    }

    /**
     * Cài đặt Recycler view nhập liệu
     *
     * @author Nguyễn Công Chính
     * @since 3/15/2022
     *
     * @version 1
     * @updated 3/15/2022: Tạo function
     */
    private fun configRcv() {
        adapter = DeliveryInputAdapter(listOf(
            DeliveryTypeInputModel(),
            SpinnerInputModel("Đối tác giao hàng", false, listOf(
                "Viettel Post",
                "VNPost",
                "J&T Express",
                "Giao hàng tiết kiêm",
                "GHN",
            )),
            TapActionInputModel("Loại dịch vụ", false, null) {},
            TapInsertInputModel("Phí giao hàng trả đối tác", false,
                resources.getDrawableById(R.drawable.ic_calculator),
                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL),
            TapInsertInputModel("Mã vận đơn", false,
                null, InputType.TYPE_CLASS_TEXT),
            TapInsertInputModel("Ghi chú giao hàng", false,
                null, InputType.TYPE_CLASS_TEXT),
            TapActionInputModel("Ngày giao hàng", false, null) {},
        ), requireContext())
        binding.rcvShipInput.layoutManager = LinearLayoutManager(context)
        binding.rcvShipInput.adapter = adapter
        binding.rcvShipInput.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

}