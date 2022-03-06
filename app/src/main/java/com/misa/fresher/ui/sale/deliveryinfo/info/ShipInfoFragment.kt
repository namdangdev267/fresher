package com.misa.fresher.ui.sale.deliveryinfo.info

import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.databinding.FragmentShipInfoBinding
import com.misa.fresher.model.InputInfo
import com.misa.fresher.ui.sale.adapter.ReceiverInputAdapter
import com.misa.fresher.util.enum.InputType

class ShipInfoFragment : BaseFragment<FragmentShipInfoBinding>() {

    override val getInflater: (inflater: LayoutInflater) -> FragmentShipInfoBinding =
        FragmentShipInfoBinding::inflate
    private var adapter: ReceiverInputAdapter? = null

    override fun initUI() {
        adapter = context?.let { ReceiverInputAdapter(fakeData(), it) }
        binding.rcvShipInput.layoutManager = LinearLayoutManager(context)
        binding.rcvShipInput.adapter = adapter
        binding.rcvShipInput.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun fakeData(): Array<InputInfo> =
        arrayOf(
            InputInfo("", false, InputType.DELIVERY_TYPE),
            InputInfo(
                "Đối tác giao hàng", false, InputType.SPINNER, null, arrayOf(), arrayOf(
                    "Viettel Post",
                    "VNPost",
                    "J&T Express",
                    "Giao hàng tiết kiêm",
                    "GHN",
                )
            ),
            InputInfo("Loại dịch vụ", false, InputType.TAP_ACTION) {},
            InputInfo(
                "Phí giao hàng trả đối tác",
                false,
                InputType.TAP_INSERT,
                resources.getDrawable(R.drawable.ic_calculator, null)
            ),
            InputInfo(
                "Mã vận đơn",
                false,
                InputType.TAP_INSERT
            ),
            InputInfo(
                "Ghi chú giao hàng",
                false,
                InputType.TAP_INSERT
            ),
            InputInfo("Ngày giao hàng", false, InputType.TAP_ACTION) {},
        )
}