package com.misa.fresher.ui.sale

import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.common.FakeData
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.entity.ProductItem
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.ui.MainActivity
import com.misa.fresher.ui.sale.adapter.ProductAdapter
import com.misa.fresher.util.getColorById
import com.misa.fresher.util.getDrawableById

/**
 * Màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 1
 * @updated 3/9/2022: Tạo class
 */
class SaleFragment : BaseFragment<FragmentSaleBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentSaleBinding
        get() = FragmentSaleBinding::inflate

    private var productAdapter: ProductAdapter? = null

    override fun initUI() {
        configToolbar()
        configFilterDrawer()
        configProductRcv()
        configCustomerSelect()

        initProductList()
    }

    /**
     * Cài đặt chức năng chọn khách hàng, sử dụng dữ liệu giả không đúng với thực tế
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    private fun configCustomerSelect() {
        binding.tvCustomer.setOnClickListener {
            binding.tvCustomer.marqueeRepeatLimit = 1
            binding.tvCustomer.ellipsize = TextUtils.TruncateAt.MARQUEE
            binding.tvCustomer.text = FakeData.customers[FakeData.rd.nextInt(FakeData.customers.size)]
            binding.tvCustomer.setTextColor(resources.getColorById(R.color.primary_text_in_white))
            binding.tvCustomer.isSelected = true
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
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    private fun configProductRcv() {
        productAdapter = ProductAdapter(mutableListOf(), requireContext()) { item, quantity ->
            Log.d("selected", "${item.toString()}, so luong: $quantity")
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