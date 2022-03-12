package com.misa.fresher.ui.sale

import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.common.FakeData
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.model.CartItemModel
import com.misa.fresher.data.model.FilterProductModel
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.ui.MainActivity
import com.misa.fresher.ui.sale.adapter.ProductAdapter
import com.misa.fresher.util.enum.ProductSortType
import com.misa.fresher.util.getColorById
import com.misa.fresher.util.getDrawableById
import com.misa.fresher.util.toCurrency

/**
 * Màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 3
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Thêm chức năng chọn loại sản phẩm, cập nhật vào giỏ hàng
 * @updated 3/12/2022: Thêm chức năng lọc sản phẩm, tìm kiếm sản phẩm theo tên, mã
 */
class SaleFragment : BaseFragment<FragmentSaleBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentSaleBinding
        get() = FragmentSaleBinding::inflate

    private var productAdapter: ProductAdapter? = null
    private var selectedItems = mutableListOf<CartItemModel>()
    private val filter = FilterProductModel()

    override fun initUI() {
        configToolbar()
        configFilterDrawer()
        configProductRcv()
        configOtherView()

        initProductList()
        updateSelectedItem()
    }

    /**
     * Cập nhật các sản phẩm đã chọn lên giao diện
     *
     * @author Nguyễn Công Chính
     * @since 3/11/2022
     *
     * @version 1
     * @updated 3/11/2022: Tạo function
     */
    private fun updateSelectedItem() {
        if (selectedItems.isEmpty()) {
            binding.btnRefresh.isEnabled = false
            binding.btnCart.isEnabled = false
            binding.tvCount.isEnabled = false
            binding.tvInfo.isEnabled = false
            binding.tvCount.text = "0"
            binding.tvInfo.text = getString(R.string.no_item_selected)
        } else {
            binding.btnRefresh.isEnabled = true
            binding.btnCart.isEnabled = true
            binding.tvCount.isEnabled = true
            binding.tvInfo.isEnabled = true

            val total = selectedItems.sumOf { it.item.price * it.quantity }
            binding.tvCount.text = "${selectedItems.size}"
            binding.tvInfo.text = total.toCurrency()
        }

    }

    /**
     * Cài đặt các nút và view phụ khác
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 2
     * @updated 3/10/2022: Tạo function
     * @updated 3/12/2022: Đổi từ chỉ "config nút chọn khách hàng" thành "config các view phụ khác"
     */
    private fun configOtherView() {
        binding.tvCustomer.setOnClickListener {
            binding.tvCustomer.marqueeRepeatLimit = 1
            binding.tvCustomer.ellipsize = TextUtils.TruncateAt.MARQUEE
            binding.tvCustomer.text = FakeData.customers[FakeData.rd.nextInt(FakeData.customers.size)]
            binding.tvCustomer.setTextColor(resources.getColorById(R.color.primary_text_in_white))
            binding.tvCustomer.isSelected = true
        }
        binding.btnRefresh.setOnClickListener {
            selectedItems.clear()
            updateSelectedItem()
        }
    }

    /**
     * Khởi tạo danh sách sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 2
     * @updated 3/10/2022: Tạo function
     * @updated 3/12/2022: Ngay khi khởi tạo danh sách mặc định sẽ lọc theo tên sản phẩm
     */
    private fun initProductList() {
        val filterItems = filter.filter(FakeData.products)
        productAdapter?.updateProductList(filterItems)
    }

    /**
     * Cài đặt recycler view sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 2
     * @updated 3/10/2022: Tạo function
     * @updated 3/12/2022: Cập nhật vào giỏ hàng khi lựa chọn xong hàng muốn mua
     */
    private fun configProductRcv() {
        productAdapter = ProductAdapter(mutableListOf(), requireContext()) { item, quantity ->
            selectedItems.find { it.item == item }?.let {
                it.quantity += quantity
            } ?: run {
                selectedItems.add(CartItemModel(item, quantity))
            }
            updateSelectedItem()
        }
        binding.rcvProduct.layoutManager = LinearLayoutManager(context)
        binding.rcvProduct.adapter = productAdapter
        binding.rcvProduct.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    /**
     * Cài đặt drawer bộ lọc
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 2
     * @updated 3/10/2022: Tạo function
     * @updated 3/12/2022: Bổ sung chức năng lọc sản phẩm
     */
    private fun configFilterDrawer() {
        binding.root.setScrimColor(Color.TRANSPARENT)
        binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.nvFilter)

        binding.llFilter.swQuantity.setOnCheckedChangeListener { _, b ->
            filter.isQuantityMoreThanZero = b
        }

        val groupItem = mutableListOf(getString(R.string.all))
        groupItem.addAll(FakeData.category.map { it.name })
        val groupAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            groupItem
        )
        binding.llFilter.spnGrouping.adapter = groupAdapter
        binding.llFilter.spnGrouping.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                if (position == 0) {
                    filter.selectedCategory = null
                } else {
                    filter.selectedCategory = FakeData.category[position - 1]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.llFilter.rbName.setOnCheckedChangeListener { _, b ->
            if (b) filter.sortBy = ProductSortType.NAME
        }
        binding.llFilter.rbNew.setOnCheckedChangeListener { _, b ->
            if (b) filter.sortBy = ProductSortType.NEW_PRODUCT
        }
        binding.llFilter.rbQuantity.setOnCheckedChangeListener { _, b ->
            if (b) filter.sortBy = ProductSortType.QUANTITY
        }
        binding.llFilter.rbName.isChecked = true

        binding.llFilter.btnDone.setOnClickListener {
            val filterItems = filter.filter(FakeData.products)
            productAdapter?.updateProductList(filterItems)
            toggleDrawer(binding.nvFilter)
        }

        binding.llFilter.btnReset.setOnClickListener {
            binding.tbSale.etInput.text.clear()
            filter.keyword = ""
            binding.llFilter.swQuantity.isChecked = false
            binding.llFilter.spnGrouping.setSelection(0)
            binding.llFilter.rbName.isChecked = true
        }
    }

    /**
     * Cài đặt toolbar
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 2
     * @updated 3/10/2022: Tạo function
     * @updated 3/12/2022: Bổ sung chức năng tìm kiếm sản phẩm bằng tên hoặc mã
     */
    private fun configToolbar() {
        binding.tbSale.root.inflateMenu(R.menu.menu_sale)
        binding.tbSale.btnNav.setImageDrawable(resources.getDrawableById(R.drawable.ic_menu))
        binding.tbSale.btnNav.setOnClickListener {
            (activity as MainActivity).toggleDrawer()
        }
        binding.tbSale.root.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.btn_filter -> {
                    toggleDrawer(binding.nvFilter)
                }
            }
            true
        }
        binding.tbSale.etInput.setOnEditorActionListener { textView, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                filter.keyword = textView.text.toString()
                val filterItems = filter.filter(FakeData.products)
                productAdapter?.updateProductList(filterItems)
            }
            false
        }
    }

    /**
     * Đóng mở menu
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    private fun toggleDrawer(menu: View) {
        if (binding.root.isDrawerOpen(menu)) {
            binding.root.closeDrawer(menu)
        } else {
            binding.root.openDrawer(menu)
        }
    }
}