package com.misa.fresher.ui.sale

import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.common.FakeData
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.model.CartItem
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.ui.MainActivity
import com.misa.fresher.ui.sale.adapter.ProductAdapter
import com.misa.fresher.util.getColorById
import com.misa.fresher.util.getDrawableById
import com.misa.fresher.util.toCurrency

/**
 * Màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 2
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Thêm chức năng chọn loại sản phẩm, cập nhật vào giỏ hàng
 */
class SaleFragment : BaseFragment<FragmentSaleBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentSaleBinding
        get() = FragmentSaleBinding::inflate

    private var productAdapter: ProductAdapter? = null
    private var selectedItems = mutableListOf<CartItem>()

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
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    private fun initProductList() {
        productAdapter?.updateProductList(FakeData.products)
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
                selectedItems.add(CartItem(item, quantity))
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
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    private fun configFilterDrawer() {
        binding.root.setScrimColor(Color.TRANSPARENT)
    }

    /**
     * Cài đặt toolbar
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    private fun configToolbar() {
        binding.tbSale.root.menu.clear()
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