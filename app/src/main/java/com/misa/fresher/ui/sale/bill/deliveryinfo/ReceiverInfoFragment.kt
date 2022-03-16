package com.misa.fresher.ui.sale.bill.deliveryinfo

import android.text.InputType
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import com.misa.fresher.R
import com.misa.fresher.common.FakeData
import com.misa.fresher.common.Rand
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.entity.Customer
import com.misa.fresher.data.model.*
import com.misa.fresher.databinding.FragmentReceiverInfoBinding
import com.misa.fresher.ui.MainActivity
import com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.DeliveryInputAdapter
import com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder.TapActionViewHolder
import com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder.TapInsertViewHolder
import com.misa.fresher.util.default
import com.misa.fresher.util.getDrawableById

/**
 * Màn nhập các thông tin liên quan đến người nhận
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 2
 * @updated 3/15/2022: Tạo class
 * @updated 3/16/2022: Bổ sung hàm [collectData] để thu dữ liệu từ recycler view, parent có thể gọi hàm này để nhận được dữ liệu
 */
class ReceiverInfoFragment: BaseFragment<FragmentReceiverInfoBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentReceiverInfoBinding
        get() = FragmentReceiverInfoBinding::inflate

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
            TapActionInputModel("Người nhận", true,
                resources.getDrawableById(R.drawable.ic_plus),
                default((activity as MainActivity).tempCustomer?.name, "")
            ) {
                (activity as MainActivity).tempCustomer =
                    FakeData.customers[Rand.instance.nextInt(FakeData.customers.size)]
                (activity as MainActivity).tempCustomer?.let {
                    (adapter?.inputItems?.get(0) as? TapActionInputModel)?.input = it.name
                    (adapter?.inputItems?.get(1) as? TapInsertInputModel)?.input = it.tel
                    (adapter?.inputItems?.get(2) as? TapInsertInputModel)?.input = it.address
                    adapter?.notifyItemRangeChanged(0, 3)
                }
            },
            TapInsertInputModel("Số điện thoại", true,
                null, InputType.TYPE_CLASS_PHONE,
                default((activity as MainActivity).tempCustomer?.tel, "")
            ),
            TapInsertInputModel("Địa chỉ", true,
                null, InputType.TYPE_CLASS_TEXT,
                default((activity as MainActivity).tempCustomer?.address, "")
            ),
            TapActionInputModel("Khu vực", false,
                resources.getDrawableById(R.drawable.ic_arrow_right)) {},
            TapActionInputModel("Phường xã", false,
                resources.getDrawableById(R.drawable.ic_arrow_right)) {},
            TapInsertInputModel("Phí giao hàng thu khách", false,
                resources.getDrawableById(R.drawable.ic_calculator),
                InputType.TYPE_CLASS_NUMBER),
            TwoColumnInputModel(
                SpinnerInputModel("Hình thức đặt cọc", false, listOf(
                    "Chuyển khoản",
                    "Tiền mặt",
                    "ATM",
                    "VISA",
                    "MasterCard"
                )),
                TapInsertInputModel("Số tiền đã đặt cọc", false,
                    null, InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
            ),
            SpinnerInputModel("Kênh bán hàng", false, listOf(
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
            )),
            SingleCheckBoxInputModel("Thu COD")
        ), requireContext())
        binding.rcvReceiverInput.adapter = adapter
        binding.rcvReceiverInput.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    /**
     * Hàm thu thập dữ liệu từ recycler view
     *
     * @author Nguyễn Công Chính
     * @since 3/16/2022
     *
     * @version 1
     * @updated 3/16/2022: Tạo function
     */
    fun collectData(): Customer = Customer(
        0L,
        (binding.rcvReceiverInput.findViewHolderForAdapterPosition(0) as TapActionViewHolder).collectData(),
        (binding.rcvReceiverInput.findViewHolderForAdapterPosition(1) as TapInsertViewHolder).collectData(),
        (binding.rcvReceiverInput.findViewHolderForAdapterPosition(2) as TapInsertViewHolder).collectData(),
    )
}