package com.misa.fresher.ui.sale.bill

import android.text.TextUtils
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import com.misa.fresher.R
import com.misa.fresher.common.FakeData
import com.misa.fresher.common.Rand
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.data.entity.ProductItemBill
import com.misa.fresher.databinding.FragmentBillBinding
import com.misa.fresher.ui.MainActivity
import com.misa.fresher.ui.sale.SaleFragment
import com.misa.fresher.ui.sale.bill.adapter.ProductBillAdapter
import com.misa.fresher.util.get
import com.misa.fresher.util.getColorById
import com.misa.fresher.util.toCurrency
import com.misa.fresher.util.toast
import java.util.*

/**
 * Màn hình thanh toán đơn hàng (bill)
 *
 * @author Nguyễn Công Chính
 * @since 3/13/2022
 *
 * @version 2
 * @updated 3/13/2022: Tạo class
 * @updated 3/15/2022: Cập nhật customer mỗi lần màn hình hiện ra
 */
class BillFragment: BaseFragment<FragmentBillBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentBillBinding
        get() = FragmentBillBinding::inflate

    private val selectedItems by lazy { arguments.get(SaleFragment.SELECTED_ITEMS, mutableListOf<ProductItemBill>()) }

    private var productAdapter: ProductBillAdapter? = null

    override fun initUI() {
        configToolbar()
        configProductRcv()
        configOtherView()

        initProductItems()
        updateTotalAmount()
    }

    override fun updateUI() {
        updateCustomer()
    }

    /**
     * Cập nhật khách hàng mỗi lần màn hình được mở
     *
     * @author Nguyễn Công Chính
     * @since 3/15/2022
     *
     * @version 2
     * @updated 3/15/2022: Tạo function
     * @updated 3/16/2022: Cập nhật thêm trường hợp nếu tempCustomer null
     */
    private fun updateCustomer() {
        (activity as MainActivity).tempCustomer?.let {
            binding.tvCustomer.marqueeRepeatLimit = 1
            binding.tvCustomer.ellipsize = TextUtils.TruncateAt.MARQUEE
            binding.tvCustomer.text = it.toString()
            binding.tvCustomer.isSelected = true
        } ?: run {
            binding.tvCustomer.ellipsize = TextUtils.TruncateAt.END
            binding.tvCustomer.text = ""
        }
    }

    /**
     * Cập nhật tổng giá trị đơn hàng
     *
     * @author Nguyễn Công Chính
     * @since 3/14/2022
     *
     * @version 1
     * @updated 3/14/2022: Tạo function
     */
    private fun updateTotalAmount() {
        binding.tvCount.text = selectedItems.sumOf { it.quantity }.toString()
        binding.tvTotal.text = selectedItems.sumOf { it.item.price * it.quantity }.toCurrency()
    }

    /**
     * Khởi tạo danh sách sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/14/2022
     *
     * @version 1
     * @updated 3/14/2022: Tạo function
     */
    private fun initProductItems() {
        productAdapter?.updateProductList(selectedItems)
    }

    /**
     * Hàm cài đặt recycler view sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/14/2022
     *
     * @version 1
     * @updated 3/14/2022: Tạo function
     */
    private fun configProductRcv() {
        productAdapter = ProductBillAdapter(mutableListOf(), requireContext(), this::updateTotalAmount)
        binding.rcvProduct.adapter = productAdapter
        binding.rcvProduct.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    /**
     * Hàm cài đặt các view phụ khác
     *
     * @author Nguyễn Công Chính
     * @since 3/14/2022
     *
     * @version 2
     * @updated 3/14/2022: Tạo function
     * @updated 3/16/2022: Đặt sự kiện cho nút thanh toán
     */
    private fun configOtherView() {
        binding.tvCustomer.setOnClickListener {
            binding.tvCustomer.marqueeRepeatLimit = 1
            binding.tvCustomer.ellipsize = TextUtils.TruncateAt.MARQUEE
            (activity as MainActivity).tempCustomer = FakeData.customers[Rand.instance.nextInt(FakeData.customers.size)]
            binding.tvCustomer.text = (activity as MainActivity).tempCustomer.toString()
            binding.tvCustomer.setTextColor(resources.getColorById(R.color.primary_text_in_white))
            binding.tvCustomer.isSelected = true
        }
        binding.tvBuyMore.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.btnDeliveryInfo.setOnClickListener {
            navigation.navigate(R.id.action_fragment_bill_to_fragment_delivery_info)
        }
        binding.llPayment.setOnClickListener {
            (activity as MainActivity).tempCustomer?.let {
                val bill = Bill(
                    binding.tbBill.tvTitle.text.toString().toLong(),
                    it,
                    selectedItems.toList(),
                    Calendar.getInstance()
                )
                FakeData.bills.add(bill)
                toast(requireContext(), R.string.save_bill_success)

                (activity as MainActivity).tempCustomer = null
                selectedItems.clear()
                activity?.onBackPressed()
            } ?: run {
                toast(requireContext(), R.string.please_select_receiver)
            }
        }
    }

    /**
     * Cài đặt toolbar
     *
     * @author Nguyễn Công Chính
     * @since 3/13/2022
     *
     * @version 1
     * @updated 3/13/2022: Tạo function
     */
    private fun configToolbar() {
        binding.tbBill.root.inflateMenu(R.menu.menu_bill)
        binding.tbBill.btnNav.setImageResource(R.drawable.ic_arrow_back)
        binding.tbBill.btnNav.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.tbBill.tvTitle.text = Rand.generateBillCode().toString()
    }
}