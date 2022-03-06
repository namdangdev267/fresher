package com.misa.fresher.ui.sale.deliveryinfo.info

import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.databinding.FragmentReceiverInfoBinding
import com.misa.fresher.model.InputInfo
import com.misa.fresher.ui.sale.adapter.ReceiverInputAdapter
import com.misa.fresher.util.enum.InputType

class ReceiverInfoFragment : BaseFragment<FragmentReceiverInfoBinding>() {

    override val getInflater: (inflater: LayoutInflater) -> FragmentReceiverInfoBinding =
        FragmentReceiverInfoBinding::inflate
    private var adapter: ReceiverInputAdapter? = null

    override fun initUI() {
        adapter = context?.let { ReceiverInputAdapter(fakeData(), it) }
        binding.rcvReceiverInput.layoutManager = LinearLayoutManager(context)
        binding.rcvReceiverInput.adapter = adapter
        binding.rcvReceiverInput.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun fakeData(): Array<InputInfo> =
        arrayOf(
            InputInfo(
                "Người nhận",
                true,
                InputType.TAP_ACTION,
                resources.getDrawable(R.drawable.ic_add, null)
            ) {},
            InputInfo("Số điện thoại", true, InputType.TAP_INSERT),
            InputInfo("Địa chỉ", true, InputType.TAP_INSERT),
            InputInfo(
                "Khu vực",
                false,
                InputType.TAP_ACTION,
                resources.getDrawable(R.drawable.ic_navigate_next, null)
            ) {},
            InputInfo(
                "Phường xã",
                false,
                InputType.TAP_ACTION,
                resources.getDrawable(R.drawable.ic_navigate_next, null)
            ) {},
            InputInfo(
                "Phí giao hàng thu khách",
                false,
                InputType.TAP_INSERT,
                resources.getDrawable(R.drawable.ic_calculator, null)
            ),
            InputInfo(
                "", false, InputType.TWO_COL, null, arrayOf(
                    InputInfo(
                        "Hình thức đặt cọc", false, InputType.SPINNER, null, arrayOf(), arrayOf(
                            "Chuyển khoản",
                            "Tiền mặt",
                            "ATM",
                            "VISA",
                            "MasterCard"
                        )
                    ),
                    InputInfo("Số tiền đặt cọc", false, InputType.TAP_INSERT),
                )
            ),
            InputInfo(
                "Kênh bán hàng", false, InputType.SPINNER, null, arrayOf(), arrayOf(
                    "Facebook",
                    "Zalo",
                    "Instagram",
                    "Tiki",
                    "Lazada",
                    "Facebook",
                    "Zalo",
                    "Instagram",
                    "Tiki",
                    "Lazada"
                )
            ),
            InputInfo("Thu COD", false, InputType.CHECK_BOX),
        )
}