package com.misa.fresher.ui.sale

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import com.misa.fresher.R
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.entity.Category
import com.misa.fresher.data.entity.Customer
import com.misa.fresher.data.entity.Product
import com.misa.fresher.data.entity.ProductItemBill
import com.misa.fresher.databinding.DialogProductTypeSelectorBinding
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.ui.main.MainActivity
import com.misa.fresher.ui.sale.adapter.ProductAdapter
import com.misa.fresher.ui.sale.dialog.ProductTypeSelectorDialog
import com.misa.fresher.util.enum.ProductSortType
import com.misa.fresher.util.toCurrency
import com.misa.fresher.util.toast

/**
 * Màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 8
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Thêm chức năng chọn loại sản phẩm, cập nhật vào giỏ hàng
 * @updated 3/12/2022: Thêm chức năng lọc sản phẩm, tìm kiếm sản phẩm theo tên, mã
 * @updated 3/15/2022: Chuyển nhà các hàm trong configTypeSelectorDialog từ com.misa.fresher.ui.sale.adapter.viewholder.ProductViewHolder sang đây
 * @updated 3/15/2022: Cập nhật customer mỗi lần màn hình hiện ra
 * @updated 3/23/2022: Chuyển từ mvc -> mvp
 * @updated 3/31/2022: Cập nhật swipe to refresh
 * @updated 3/31/2022: Di chuyển dialog chọn loại hàng/số lượng sang class [com.misa.fresher.ui.login.dialog.ShopSelectorDialog]
 */
class SaleFragment : BaseFragment<FragmentSaleBinding, SaleContract.Presenter>(),
    SaleContract.View {

    override val getInflater: (LayoutInflater) -> FragmentSaleBinding
        get() = FragmentSaleBinding::inflate
    override val initPresenter: (Context) -> SaleContract.Presenter
        get() = { SalePresenter(this, it) }

    private var productAdapter: ProductAdapter? = null

    private val dialog by lazy {
        ProductTypeSelectorDialog(
            requireContext(),
            R.style.Theme_FreshersApplication_BottomDialog,
            DialogProductTypeSelectorBinding.inflate(layoutInflater)
        ) { product, quantity, color, size, unit ->
            presenter?.addSelectedItem(product, quantity, color, size, unit)
        }
    }

    override fun initUI() {
        configToolbar()
        configFilterDrawer()
        configProductRcv()
        configOtherView()

        initProductList()
    }

    override fun updateUI() {
        updateCustomer()
        presenter?.updateSelectedItems()
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
     * Cài đặt các nút và view phụ khác
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 3
     * @updated 3/10/2022: Tạo function
     * @updated 3/12/2022: Đổi từ chỉ "config nút chọn khách hàng" thành "config các view phụ khác"
     * @updated 3/15/2022: Liên kết màn [SaleFragment] với màn [com.misa.fresher.ui.sale.bill.BillFragment]
     */
    private fun configOtherView() {
        binding.tvCustomer.setOnClickListener {
            presenter?.randomCustomer()
        }
        binding.btnRefresh.setOnClickListener {
            presenter?.clearSelectedItem()
        }
        binding.btnCart.setOnClickListener {
            presenter?.let {
                navigation.navigate(
                    R.id.action_fragment_sale_to_fragment_bill, bundleOf(
                        ARGUMENT_SELECTED_ITEMS to it.getSelectedItem()
                    )
                )
            }
        }
        binding.swpProduct.setOnRefreshListener {
            presenter?.filterByKeyword()
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
        binding.swpProduct.isRefreshing = true
        presenter?.filterByKeyword("")
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
        productAdapter = ProductAdapter(mutableListOf(), dialog::show)
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

        presenter?.getAllCategory()

        var sortBy = ProductSortType.NAME
        binding.llFilter.rbName.setOnCheckedChangeListener { _, b ->
            if (b) sortBy = ProductSortType.NAME
        }
        binding.llFilter.rbNew.setOnCheckedChangeListener { _, b ->
            if (b) sortBy = ProductSortType.NEW_PRODUCT
        }
        binding.llFilter.rbQuantity.setOnCheckedChangeListener { _, b ->
            if (b) sortBy = ProductSortType.QUANTITY
        }
        binding.llFilter.rbName.isChecked = true

        binding.llFilter.btnDone.setOnClickListener {
            binding.swpProduct.isRefreshing = true
            presenter?.filterByAttr(
                binding.llFilter.swQuantity.isChecked,
                binding.llFilter.spnGrouping.selectedItemPosition - 1,
                sortBy
            )
            toggleDrawer(binding.nvFilter)
        }

        binding.llFilter.btnReset.setOnClickListener {
            binding.tbSale.etInput.text.clear()
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
        binding.tbSale.btnNav.setImageResource(R.drawable.ic_menu)
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
                binding.swpProduct.isRefreshing = true
                presenter?.filterByKeyword(textView.text.toString())
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

    /**
     * @version 1
     * @updated 3/23/2022: Override lần đầu
     */
    override fun updateProductList(list: MutableList<Product>) {
        binding.swpProduct.isRefreshing = false
        productAdapter?.updateData(list)
    }

    /**
     * @version 1
     * @updated 3/23/2022: Override lần đầu
     */
    override fun updateSelectedItems(list: MutableList<ProductItemBill>) {
        if (list.isEmpty()) {
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

            val total = list.sumOf { it.item.price * it.quantity }
            binding.tvCount.text = list.sumOf { it.quantity }.toString()
            binding.tvInfo.text = total.toCurrency()
        }
    }

    override fun getAllCategorySuccess(list: List<Category>) {
        val groupItem = mutableListOf(getString(R.string.all))
        groupItem.addAll(list.map { it.name })
        val groupAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            groupItem
        )
        binding.llFilter.spnGrouping.adapter = groupAdapter
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

    companion object {
        const val ARGUMENT_SELECTED_ITEMS = "items"
    }
}