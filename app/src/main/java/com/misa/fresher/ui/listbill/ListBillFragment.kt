package com.misa.fresher.ui.listbill

import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.misa.fresher.R
import com.misa.fresher.common.FakeData
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.model.FilterBillModel
import com.misa.fresher.databinding.FragmentListBillBinding
import com.misa.fresher.ui.MainActivity
import com.misa.fresher.ui.listbill.adapter.BillAdapter
import com.misa.fresher.util.enum.TimeFilterType
import com.misa.fresher.util.toCurrency

/**
 * Màn hình Danh sách hóa đơn
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 2
 * @updated 3/9/2022: Tạo class
 * @updated 3/16/2022: Thêm nội dung cho màn hình
 */
class ListBillFragment : BaseFragment<FragmentListBillBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentListBillBinding
        get() = FragmentListBillBinding::inflate

    private var billAdapter: BillAdapter? = null
    private val filter: FilterBillModel = FilterBillModel()

    override fun initUI() {
        configToolbar()
        configFilter()
        configBillRcv()
        configOtherView()

        updateListBill()
    }

    /**
     * Cài đặt các view khác
     *
     * @author Nguyễn Công Chính
     * @since 3/16/2022
     *
     * @version 1
     * @updated 3/16/2022: Tạo function
     */
    private fun configOtherView() {
        binding.btnBuyMore.setOnClickListener {
            navigation.popBackStack(R.id.fragment_sale, false)
        }
    }

    /**
     * Cài đặt recycler view danh sách hóa đơn
     *
     * @author Nguyễn Công Chính
     * @since 3/16/2022
     *
     * @version 1
     * @updated 3/16/2022: Tạo function
     */
    private fun configBillRcv() {
        billAdapter = BillAdapter(mutableListOf())
        binding.rcvListBill.adapter = billAdapter
    }

    /**
     * Cài đặt các view liên quan đến lọc và tìm kiếm
     *
     * @author Nguyễn Công Chính
     * @since 3/16/2022
     *
     * @version 1
     * @updated 3/16/2022: Tạo function
     */
    private fun configFilter() {
        binding.spnFilterTime.adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_simple_text_spinner,
            resources.getStringArray(R.array.filter_time)
        )
        binding.spnFilterTime.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    when (position) {
                        0 -> filter.time = TimeFilterType.TODAY
                        1 -> filter.time = TimeFilterType.YESTERDAY
                        2 -> filter.time = TimeFilterType.BEFORE_YESTERDAY
                        3 -> filter.time = TimeFilterType.THIS_WEEK
                        else -> filter.time = TimeFilterType.OTHER
                    }
                    updateListBill()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        binding.spnFilterStatue.adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_simple_text_spinner,
            resources.getStringArray(R.array.filter_bill_status)
        )
    }

    /**
     * Hàm cập nhật lại danh sách hóa đơn sau mỗi lần lọc/tìm kiếm
     *
     * @author Nguyễn Công Chính
     * @since 3/16/2022
     *
     * @version 1
     * @updated 3/16/2022: Tạo function
     */
    private fun updateListBill() {
        val filterItems = filter.filter(FakeData.bills)
        billAdapter?.updateData(filterItems.toMutableList())
        binding.tvCount.text = filterItems.size.toString()
        binding.tvTotal.text = filterItems.sumOf { item ->
            item.items.sumOf { it.item.price * it.quantity }
        }.toCurrency()
    }

    /**
     * Cài đặt toolbar
     *
     * @author Nguyễn Công Chính
     * @since 3/16/2022
     *
     * @version 1
     * @updated 3/16/2022: Tạo function
     */
    private fun configToolbar() {
        binding.tbListBill.root.inflateMenu(R.menu.menu_list_bill)
        binding.tbListBill.tvTitle.text = getString(R.string.list_bill)
        binding.tbListBill.btnNav.setImageResource(R.drawable.ic_menu)
        binding.tbListBill.btnNav.setOnClickListener {
            (activity as MainActivity).toggleDrawer()
        }
        binding.tbListBill.root.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.btn_search -> {
                    binding.tbListBill.llSearch.visibility = View.VISIBLE
                    binding.tbListBill.root.menu.clear()
                    binding.tbListBill.root.inflateMenu(R.menu.menu_list_bill_search)
                }
                R.id.btn_close -> {
                    binding.tbListBill.etInput.text.clear()
                    filter.keyword = ""
                    updateListBill()
                    binding.tbListBill.llSearch.visibility = View.GONE
                    binding.tbListBill.root.menu.clear()
                    binding.tbListBill.root.inflateMenu(R.menu.menu_list_bill)
                }
            }
            true
        }
        binding.tbListBill.etInput.setOnEditorActionListener { textView, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                filter.keyword = textView.text.toString()
                updateListBill()
            }
            false
        }
    }
}