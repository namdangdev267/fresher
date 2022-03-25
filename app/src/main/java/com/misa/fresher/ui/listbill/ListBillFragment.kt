package com.misa.fresher.ui.listbill

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.misa.fresher.R
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.databinding.FragmentListBillBinding
import com.misa.fresher.ui.main.MainActivity
import com.misa.fresher.ui.listbill.adapter.BillAdapter
import com.misa.fresher.util.enum.TimeFilterType
import com.misa.fresher.util.toCurrency

/**
 * Màn hình Danh sách hóa đơn
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 3
 * @updated 3/9/2022: Tạo class
 * @updated 3/16/2022: Thêm nội dung cho màn hình
 * @updated 3/23/2022: Chuyển từ mvc -> mvp
 */
class ListBillFragment :
    BaseFragment<FragmentListBillBinding, ListBillContract.Presenter>(),
    ListBillContract.View {

    override val getInflater: (LayoutInflater) -> FragmentListBillBinding
        get() = FragmentListBillBinding::inflate
    override val initPresenter: (Context) -> ListBillContract.Presenter
        get() = { ListBillPresenter(this, it) }

    private var billAdapter: BillAdapter? = null

    override fun initUI() {
        configToolbar()
        configFilter()
        configBillRcv()
        configOtherView()

        initListBill()
    }

    /**
     * Khởi tạo danh sách hóa đơn
     *
     * @author Nguyễn Công Chính
     * @since 3/23/2022
     *
     * @version 1
     * @updated 3/23/2022: Tạo function
     */
    private fun initListBill() {
        presenter?.filterByKeyword("")
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
                    val time: TimeFilterType = when (position) {
                        0 -> TimeFilterType.TODAY
                        1 -> TimeFilterType.YESTERDAY
                        2 -> TimeFilterType.BEFORE_YESTERDAY
                        3 -> TimeFilterType.THIS_WEEK
                        else -> TimeFilterType.OTHER
                    }
                    presenter?.filterByTime(time)
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
                    presenter?.filterByKeyword("")
                    binding.tbListBill.llSearch.visibility = View.GONE
                    binding.tbListBill.root.menu.clear()
                    binding.tbListBill.root.inflateMenu(R.menu.menu_list_bill)
                }
            }
            true
        }
        binding.tbListBill.etInput.setOnEditorActionListener { textView, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                presenter?.filterByKeyword(textView.text.toString())
            }
            false
        }
    }

    /**
     * @version 1
     * @updated 3/23/2022: Override lần đầu
     */
    override fun updateBillList(list: MutableList<Bill>) {
        billAdapter?.updateData(list)
        binding.tvCount.text = list.size.toString()
        binding.tvTotal.text = list.sumOf { item ->
            item.items.sumOf { it.item.price * it.quantity }
        }.toCurrency()
    }
}