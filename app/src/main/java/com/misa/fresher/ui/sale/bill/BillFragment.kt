package com.misa.fresher.ui.sale.bill

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import com.misa.fresher.R
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.data.entity.Customer
import com.misa.fresher.data.entity.ProductItemBill
import com.misa.fresher.databinding.FragmentBillBinding
import com.misa.fresher.ui.main.MainActivity
import com.misa.fresher.ui.sale.SaleFragment
import com.misa.fresher.ui.sale.bill.adapter.ProductBillAdapter
import com.misa.fresher.util.get
import com.misa.fresher.util.toCurrency
import com.misa.fresher.util.toast
import java.util.*

/**
 * Màn hình thanh toán đơn hàng (bill)
 *
 * @author Nguyễn Công Chính
 * @since 3/13/2022
 *
 * @version 4
 * @updated 3/13/2022: Tạo class
 * @updated 3/15/2022: Cập nhật customer mỗi lần màn hình hiện ra
 * @updated 3/23/2022: Tạo khuôn presenter nhưng chưa chuyển hoàn toàn sang mvp
 * @updated 3/25/2022: Chuyển từ mvc -> mvp
 */
class BillFragment : BaseFragment<FragmentBillBinding, BillContract.Presenter>(),
    BillContract.View {

    override val getInflater: (LayoutInflater) -> FragmentBillBinding
        get() = FragmentBillBinding::inflate
    override val initPresenter: (Context) -> BillContract.Presenter
        get() = { BillPresenter(this, it) }

    private val selectedItems by lazy { arguments.get(SaleFragment.ARGUMENT_SELECTED_ITEMS, mutableListOf<ProductItemBill>()) }

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
        productAdapter?.updateData(selectedItems)
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
            presenter?.randomCustomer()
        }
        binding.tvBuyMore.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.btnDeliveryInfo.setOnClickListener {
            navigation.navigate(R.id.action_fragment_bill_to_fragment_delivery_info)
        }
        binding.llPayment.setOnClickListener {
            (activity as MainActivity).tempCustomer?.let {
                presenter?.saveBill(
                    Bill(
                        binding.tbBill.tvTitle.text.toString().toLong(),
                        it,
                        selectedItems.toList(),
                        Date()
                    )
                )
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
        presenter?.getNextId()
    }

    override fun getNextIdSuccess(id: Long) {
        binding.tbBill.tvTitle.text = id.toString()
    }

    override fun randomCustomerSuccess(customer: Customer) {
        binding.tvCustomer.marqueeRepeatLimit = 1
        binding.tvCustomer.ellipsize = TextUtils.TruncateAt.MARQUEE
        (activity as MainActivity).tempCustomer = customer
        binding.tvCustomer.text = customer.toString()
        binding.tvCustomer.isSelected = true
    }

    override fun randomCustomerFailure() {
        toast(requireContext(), getString(R.string.customer_not_found))
    }

    override fun saveBillSuccess() {
        toast(requireContext(), R.string.save_bill_success)

        (activity as MainActivity).tempCustomer = null
        selectedItems.clear()
        activity?.onBackPressed()
    }

    override fun saveBillFailure() {
        toast(requireContext(), R.string.save_bill_failure)
    }
}