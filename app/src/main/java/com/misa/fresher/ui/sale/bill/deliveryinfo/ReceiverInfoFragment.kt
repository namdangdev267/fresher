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
import com.misa.fresher.util.guard

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
     * @version 2
     * @updated 3/15/2022: Tạo function
     * @updated 3/17/2022: Sử dụng toán tử elvis thay cho default
     */
    private fun configRcv() {
        adapter = DeliveryInputAdapter(listOf(
            TapActionInputModel("Người nhận", true, R.drawable.ic_plus,
                (activity as MainActivity).tempCustomer?.name ?: ""
            ) {
                (activity as MainActivity).tempCustomer =
                    FakeData.customers[Rand.instance.nextInt(FakeData.customers.size)]
                (activity as MainActivity).tempCustomer?.let {
                    adapter?.updateData(0, it.name)
                    adapter?.updateData(1, it.tel)
                    adapter?.updateData(2, it.address)
                }
            },
            TapInsertInputModel("Số điện thoại", true,
                null, InputType.TYPE_CLASS_PHONE,
                (activity as MainActivity).tempCustomer?.tel ?: ""
            ),
            TapInsertInputModel("Địa chỉ", true,
                null, InputType.TYPE_CLASS_TEXT,
                (activity as MainActivity).tempCustomer?.address ?: ""
            ),
            TapActionInputModel("Khu vực", false, R.drawable.ic_arrow_right) {},
            TapActionInputModel("Phường xã", false, R.drawable.ic_arrow_right) {},
            TapInsertInputModel("Phí giao hàng thu khách", false, R.drawable.ic_calculator,
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
     * @version 2
     * @updated 3/16/2022: Tạo function
     * @updated 3/18/2022: Sử dụng guard check null, áp dụng hàm collectData để lấy dữ liệu trong item
     */
    fun collectData(): Customer? {
        var customer: Customer? = null
        guard(
            adapter?.collectData(0),
            adapter?.collectData(1),
            adapter?.collectData(2)
        ) { name, tel, address ->
            customer = Customer(0L, name as String, tel as String, address as String)
        }
        return customer
    }
}