package com.misa.fresher.ui.sale

import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.R
import com.misa.fresher.common.FakeData
import com.misa.fresher.common.Rand
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.entity.*
import com.misa.fresher.data.entity.ProductItemBill
import com.misa.fresher.data.model.FilterProductModel
import com.misa.fresher.databinding.DialogProductTypeSelectorBinding
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.ui.MainActivity
import com.misa.fresher.ui.sale.adapter.ProductAdapter
import com.misa.fresher.ui.sale.adapter.TypeSelectorAdapter
import com.misa.fresher.util.enum.ProductSortType
import com.misa.fresher.util.guard
import com.misa.fresher.util.toCurrency
import com.misa.fresher.util.toast

/**
 * Màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 5
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Thêm chức năng chọn loại sản phẩm, cập nhật vào giỏ hàng
 * @updated 3/12/2022: Thêm chức năng lọc sản phẩm, tìm kiếm sản phẩm theo tên, mã
 * @updated 3/15/2022: Chuyển nhà các hàm trong [configTypeSelectorDialog] từ [com.misa.fresher.ui.sale.adapter.viewholder.ProductViewHolder] sang đây
 * @updated 3/15/2022: Cập nhật customer mỗi lần màn hình hiện ra
 */
class SaleFragment : BaseFragment<FragmentSaleBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentSaleBinding
        get() = FragmentSaleBinding::inflate

    private var productAdapter: ProductAdapter? = null
    private val typeSelectorDialog by lazy {
        BottomSheetDialog(requireContext(), R.style.Theme_FreshersApplication_BottomDialog)
    }
    private val dialogBinding by lazy { DialogProductTypeSelectorBinding.inflate(layoutInflater) }

    private var selectedItems = mutableListOf<ProductItemBill>()
    private val filter = FilterProductModel()
    private var quantity = 1
    private var color: ProductColor? = null
    private var size: ProductSize? = null
    private var unit: ProductUnit? = null

    override fun initUI() {
        configToolbar()
        configFilterDrawer()
        configProductRcv()
        configTypeSelectorDialog()
        configOtherView()

        initProductList()
    }

    override fun updateUI() {
        updateCustomer()
        updateSelectedItem()
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
     * Cài đặt dialog chọn loại hàng
     *
     * @author Nguyễn Công Chính
     * @since 3/14/2022
     *
     * @version 1
     * @updated 3/14/2022: Tạo function
     */
    private fun configTypeSelectorDialog() {
        typeSelectorDialog.setContentView(dialogBinding.root)
    }

    /**
     * Hàm chuẩn bị các view bên trong dialog và hiển thị luôn
     *
     * @author Nguyễn Công Chính
     * @since 3/14/2022
     *
     * @version 1
     * @updated 3/14/2022: Tạo function
     */
    private fun showTypeSelectorDialog(product: Product) {
        typeSelectorDialog.setOnDismissListener {
            guard(color, size, unit) { cl, sz, un ->
                val item = product.items.find { it.color == cl && it.size == sz && it.unit == un }
                item?.let {
                    selectedItems.find { it.item == item }?.let {
                        it.quantity += quantity
                    } ?: run {
                        selectedItems.add(ProductItemBill(item, quantity))
                    }
                    updateSelectedItem()
                }
            }
        }
        initDialogUI(product)

        typeSelectorDialog.show()
    }

    /**
     * Cài đặt giao diện cho hộp thoại chọn loại sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/11/2022
     *
     * @version 2
     * @updated 3/11/2022: Tạo function
     * @updated 3/15/2022: Chuyển nhà từ [com.misa.fresher.ui.sale.adapter.viewholder.ProductViewHolder] sang đây
     */
    private fun initDialogUI(product: Product) {
        quantity = 1
        color = null
        size = null
        unit = null

        dialogBinding.tvName.text = product.name
        dialogBinding.tvCode.text = product.code
        dialogBinding.tvQuantity.text = quantity.toString()

        dialogBinding.btnPlus.setOnClickListener {
            quantity++
            dialogBinding.tvQuantity.text = quantity.toString()
        }
        dialogBinding.btnMinus.setOnClickListener {
            if (quantity == 1) {
                toast(requireContext(), R.string.quantity_must_be_bigger_than_0)
            } else {
                quantity--
                dialogBinding.tvQuantity.text = quantity.toString()
            }
        }

        config3RcvInDialog(product)
    }

    /**
     * Cài đặt 3 recyclerview trong hộp thoại chọn sản phẩm trong 1 hàm
     *
     * @author Nguyễn Công Chính
     * @since 3/11/2022
     *
     * @version 2
     * @updated 3/11/2022: Tạo function
     * @updated 3/15/2022: Chuyển nhà từ [com.misa.fresher.ui.sale.adapter.viewholder.ProductViewHolder] sang đây
     */
    private fun config3RcvInDialog(product: Product) {
        dialogBinding.rcvColor.layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
        dialogBinding.rcvColor.adapter = TypeSelectorAdapter(product.items
            .map { it.color }
            .let { item -> item.distinctBy { it.id } }
            .toMutableList()
        ) { data ->
            color = data
            guard(color, size, unit) { _, _, _ -> typeSelectorDialog.dismiss() }
        }

        dialogBinding.rcvSize.layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
        dialogBinding.rcvSize.adapter = TypeSelectorAdapter(product.items
            .map { it.size }
            .let { item -> item.distinctBy { it.id } }
            .toMutableList()
        ) { data ->
            size = data
            guard(color, size, unit) { _, _, _ -> typeSelectorDialog.dismiss() }
        }

        dialogBinding.rcvUnit.layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
        dialogBinding.rcvUnit.adapter = TypeSelectorAdapter(product.items
            .map { it.unit }
            .let { item -> item.distinctBy { it.id } }
            .toMutableList()
        ) { data ->
            unit = data
            guard(color, size, unit) { _, _, _ -> typeSelectorDialog.dismiss() }
        }
    }

    /**
     * Cập nhật các sản phẩm đã chọn lên giao diện
     *
     * @author Nguyễn Công Chính
     * @since 3/11/2022
     *
     * @version 2
     * @updated 3/11/2022: Tạo function
     * @updated 3/15/2022: Đổi từ đếm số loại hàng trong giỏ thành đếm tổng số lượng hàng trong giỏ
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
            binding.tvCount.text = selectedItems.sumOf { it.quantity }.toString()
            binding.tvInfo.text = total.toCurrency()
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
            binding.tvCustomer.marqueeRepeatLimit = 1
            binding.tvCustomer.ellipsize = TextUtils.TruncateAt.MARQUEE
            (activity as MainActivity).tempCustomer =
                FakeData.customers[Rand.instance.nextInt(FakeData.customers.size)]
            binding.tvCustomer.text = (activity as MainActivity).tempCustomer.toString()
            binding.tvCustomer.isSelected = true
        }
        binding.btnRefresh.setOnClickListener {
            selectedItems.clear()
            updateSelectedItem()
        }
        binding.btnCart.setOnClickListener {
            navigation.navigate(
                R.id.action_fragment_sale_to_fragment_bill, bundleOf(
                    ARGUMENT_SELECTED_ITEMS to selectedItems
                )
            )
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
        productAdapter?.updateData(filterItems.toMutableList())
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
        productAdapter = ProductAdapter(mutableListOf(), this::showTypeSelectorDialog)
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
        binding.llFilter.spnGrouping.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
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
            productAdapter?.updateData(filterItems.toMutableList())
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
                filter.keyword = textView.text.toString()
                val filterItems = filter.filter(FakeData.products)
                productAdapter?.updateData(filterItems.toMutableList())
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

    companion object {
        const val ARGUMENT_SELECTED_ITEMS = "items"
    }
}