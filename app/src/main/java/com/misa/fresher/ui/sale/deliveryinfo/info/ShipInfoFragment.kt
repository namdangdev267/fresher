package com.misa.fresher.ui.sale.deliveryinfo.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentShipInfoBinding
import com.misa.fresher.model.InputInfo
import com.misa.fresher.ui.sale.adapter.ReceiverInputAdapter
import com.misa.fresher.util.enum.InputType

class ShipInfoFragment : Fragment() {

    private val binding by lazy { FragmentShipInfoBinding.inflate(layoutInflater) }
    private var adapter: ReceiverInputAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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