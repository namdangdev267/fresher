package com.misa.fresher.ui.sale

import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.ui.sale.adapter.ButtonOvalListAdapter
import com.misa.fresher.ui.sale.adapter.SaleProductAdapter
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.DialogBottomSheetBinding
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.global.FakeData
import com.misa.fresher.models.product.*
import com.misa.fresher.utils.*
import kotlinx.coroutines.*

class SaleFragment : BaseFragment<FragmentSaleBinding>(FragmentSaleBinding::inflate) {

    private var _bottomSheetDialog: BottomSheetDialog? = null
    private var _bottomSheetDialogBinding: DialogBottomSheetBinding? = null

    private val bottomSheetDialog get() = _bottomSheetDialog!!
    private val bottomSheetDialogBinding get() = _bottomSheetDialogBinding!!

    private var totalItems = FakeData.products
    private var filteredItems = totalItems
    private var displayedItems = filteredItems
    private var selectedItems = arrayListOf<Product>()

    object FilterConfig {
        var viewMode = Enums.Product.MODEL
        var textSearch = ""
    }

    override fun initUI() {
        initListProductRecViewUI()
        initBottomSheetDialogUI()
        initSaleFilterDrawerUI()
    }

    override fun updateUI() {
        super.updateUI()
        updateProductSelectedUI()
    }

    override fun initListener() {
        initToolbarListener()
        initBottomButtonListener()
        initBottomSheetDialogListener()
        initSaleFilterDrawerListener()
    }


    private fun initSaleFilterDrawerUI() {
        val drawerLayout = binding.root
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END)
        drawerLayout.setScrimColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))

        val categories = arrayListOf("all")
        val colors = arrayListOf("all")
        val sizes = arrayListOf("all")
        totalItems.forEach { p ->
            categories.add(p.category)
            p.items.forEach { item ->
                colors.add(item.color)
                sizes.add(item.size)
            }
        }

        val categoryAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_item, categories.distinct())
        categoryAdapter.setDropDownViewResource(R.layout.item_spinner_item)

        val colorAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_item, colors.distinct())
        colorAdapter.setDropDownViewResource(R.layout.item_spinner_item)

        val sizeAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_item, sizes.distinct())
        sizeAdapter.setDropDownViewResource(R.layout.item_spinner_item)

        binding.drawerSaleFilter.run {
            spinnerCategory.adapter = categoryAdapter
            spinnerColor.adapter = colorAdapter
            spinnerSize.adapter = sizeAdapter
        }
    }

    private fun initBottomSheetDialogUI() {
        context?.let { ct ->
            _bottomSheetDialog = BottomSheetDialog(ct)
            _bottomSheetDialogBinding = DialogBottomSheetBinding.inflate(layoutInflater)

            bottomSheetDialog.setContentView(bottomSheetDialogBinding.root)

            bottomSheetDialogBinding.run {
                colorRecView.layoutManager = LinearLayoutManager(ct, LinearLayoutManager.HORIZONTAL, false)
                sizeRecView.layoutManager = LinearLayoutManager(ct, LinearLayoutManager.HORIZONTAL, false)
                unitRecView.layoutManager = LinearLayoutManager(ct, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun initListProductRecViewUI() {
        binding.listProductRecView.adapter = SaleProductAdapter(displayedItems, showBottomSheetDialog)
        binding.listProductRecView.layoutManager = LinearLayoutManager(context)
    }

    private fun updateProductSelectedUI() {
        val itemAmount = selectedItems.sumOf { it.amount }
        val totalPrice = "Total ${selectedItems.sumOf { it.price }}"
        binding.apply {
            numberBtn.text = itemAmount.toString()
            if (itemAmount > 0) {
                numberBtn.background = getDrawable(context, R.drawable.bg_btn_round_left_violet)
                numberBtn.setTextColor(getColor(context, R.color.white))

                priceBtn.background = getDrawable(context, R.drawable.bg_btn_round_right_violet)
                priceBtn.text = totalPrice
                priceBtn.setTextColor(getColor(context, R.color.white))

                clearBtn.isActive = true
            } else {
                numberBtn.background = getDrawable(context, R.drawable.bg_btn_round_left)
                numberBtn.setTextColor(getColor(context, R.color.black))

                priceBtn.background = getDrawable(context, R.drawable.bg_btn_round_right)
                priceBtn.text = getString(R.string.btn_product_selected)
                priceBtn.setTextColor(getColor(context, R.color.black))

                clearBtn.isActive = false
            }
        }
    }

    private fun updateProductListUI() {
        (binding.listProductRecView.adapter as SaleProductAdapter).run {
            items = displayedItems
            notifyDataSetChanged()
        }
    }

    private fun initToolbarListener() {
        binding.toggleDrawerBtn.setOnClickListener { (activity as MainActivity).toggleDrawer() }
        binding.filterBtn.setOnClickListener { binding.root.openDrawer(GravityCompat.END) }

        var job: Job? = null
        binding.textSearch.doAfterTextChanged {
            val txtSearch = binding.textSearch.text.toString().lowercase()
            if (txtSearch != FilterConfig.textSearch) {
                FilterConfig.textSearch = txtSearch
                job?.cancel()
                job = CoroutineScope(Dispatchers.Main).launch {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.listProductRecView.visibility = View.GONE
                    delay(300)
                    productSaleSearch()
                    updateProductListUI()
                    binding.progressBar.visibility = View.GONE
                    binding.listProductRecView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initBottomButtonListener() {
        binding.clearBtn.setOnClickListener {
            selectedItems = arrayListOf()
            updateProductSelectedUI()
        }
        val navToBillFragment: (View) -> Unit = {
            if (selectedItems.size > 0) {
                findNavController().navigate(
                    R.id.action_fragment_sale_to_fragment_bill, bundleOf(BUNDLE_SELECTED_ITEMS to selectedItems)
                )
            }
        }
        binding.numberBtn.setOnClickListener(navToBillFragment)
        binding.priceBtn.setOnClickListener(navToBillFragment)
    }

    private fun initBottomSheetDialogListener() {
        bottomSheetDialogBinding.itemSaleProductView.run {
            binding.btnAdd.setOnClickListener { product.amount += 1 }
            binding.btnMinus.setOnClickListener {
                if (product.amount == 1) {
                    val toast = Toast.makeText(
                        context, "Quantity must be more than 0. Please check again.", Toast.LENGTH_LONG
                    )
                    toast.setGravity(Gravity.TOP, 0, 120)
                    toast.show()
                } else product.amount -= 1
            }
        }
    }

    private fun initSaleFilterDrawerListener() {
        binding.drawerSaleFilter.run {
            btnModel.setOnClickListener {
                FilterConfig.viewMode = Enums.Product.MODEL
                btnModel.background = getDrawable(context, R.drawable.bg_btn_round_violet)
                btnModel.setTextColor(getColor(context, R.color.white))
                btnItem.background = getDrawable(context, R.drawable.bg_btn_round_border_violet)
                btnItem.setTextColor(getColor(context, R.color.purpleDark))
            }
            btnItem.setOnClickListener {
                FilterConfig.viewMode = Enums.Product.ITEM
                btnItem.background = getDrawable(context, R.drawable.bg_btn_round_violet)
                btnItem.setTextColor(getColor(context, R.color.white))
                btnModel.background = getDrawable(context, R.drawable.bg_btn_round_border_violet)
                btnModel.setTextColor(getColor(context, R.color.purpleDark))
            }
            btnClear.setOnClickListener {
                spinnerCategory.setSelection(0)
                radioSortBy.clearCheck()
                spinnerColor.setSelection(0)
                spinnerSize.setSelection(0)
            }

            btnDone.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.listProductRecView.visibility = View.GONE
                    delay(300)
                    productSaleFilter()
                    productSaleSearch()
                    updateProductListUI()
                    binding.progressBar.visibility = View.GONE
                    binding.listProductRecView.visibility = View.VISIBLE
                }
                binding.root.closeDrawer(GravityCompat.END)
            }
        }
    }

    private suspend fun productSaleSearch() {
        withContext(Dispatchers.Default) {
            displayedItems = if (FilterConfig.textSearch == "") filteredItems
            else filteredItems.filter {
                it.name.lowercase().contains(FilterConfig.textSearch)
            }.toArrayList()
        }
    }

    private suspend fun productSaleFilter() {
        withContext(Dispatchers.Default) {
            binding.drawerSaleFilter.run {
                val isCheckAvailableQTY = swAvailableQuantity.isChecked
                val category = spinnerCategory.selectedItem.toString()
                val viewMode = FilterConfig.viewMode
                val sortBy = radioSortBy.checkedRadioButtonId
                val color = spinnerColor.selectedItem.toString()
                val size = spinnerSize.selectedItem.toString()

                if (viewMode == Enums.Product.MODEL) {
                    filteredItems = totalItems
                    if (category != "all") filteredItems = filteredItems.filter {
                        it.category == category
                    }.toArrayList()
                    if (color != "all") filteredItems = filteredItems.filter { prod ->
                        prod.items.map { it.color }.contains(color)
                    }.toArrayList()
                    if (size != "all") filteredItems = filteredItems.filter { prod ->
                        prod.items.map { it.size }.contains(size)
                    }.toArrayList()
                    if (isCheckAvailableQTY) filteredItems = filteredItems.filter {
                        it.quantity > 0
                    }.toArrayList()

                } else if (viewMode == Enums.Product.ITEM) {
                    filteredItems = arrayListOf()
                    for (product in totalItems) {
                        if (category != "all" && product.category != category) continue
                        for (item in product.items) {
                            if ((color == "all" || item.color == color) && (size == "all" || item.size == size) && (!isCheckAvailableQTY || item.quantity > 0)) {
                                filteredItems.add(product.copy(items = arrayListOf(item)))
                            }
                        }
                    }
                }

                if (sortBy == R.id.sort_by_name) filteredItems.sortBy { it.name }
                else if (sortBy == R.id.sort_by_date) filteredItems.sortBy { it.date }
            }
        }
    }

    private val showBottomSheetDialog: (prod: Product, pos: Int) -> Unit = { prod, _ ->
        val copyProd = prod.copy(amount = 1, image = 0)
        val checkedIndex = if (prod.items.size == 1) 0 else -1

        bottomSheetDialogBinding.run {2
            itemSaleProductView.run {
                product = copyProd
                binding.btnAdd.setOnClickListener { amount += 1 }
                binding.btnMinus.setOnClickListener {
                    if (amount == 1) {
                        val toast = Toast.makeText(
                            context, "Quantity must be more than 0. Please check again.", Toast.LENGTH_LONG
                        )
                        toast.setGravity(Gravity.TOP, 0, 120)
                        toast.show()
                    } else amount -= 1
                }
            }


            val colors = itemSaleProductView.colors.toArrayList()
            colorRecView.adapter = ButtonOvalListAdapter(colors, checkedIndex) { color, pos ->
                (colorRecView.adapter as ButtonOvalListAdapter).run {
                    if (checked != pos) {
                        itemSaleProductView.color = color
                        checked = pos
                        notifyDataSetChanged()
                        (sizeRecView.adapter as ButtonOvalListAdapter).run {
                            if (checked == -1) {
                                itemSaleProductView.size = null
                                items = itemSaleProductView.sizes.toArrayList()
                            } else {
                                val preSize = items[checked]
                                itemSaleProductView.size = null
                                items = itemSaleProductView.sizes.toArrayList()
                                checked = items.indexOf(preSize)
                                if (checked != -1) itemSaleProductView.size = items[checked]
                            }
                            notifyDataSetChanged()
                        }
                    }
                }
            }
            sizeRecView.adapter =
                ButtonOvalListAdapter(itemSaleProductView.sizes.toArrayList(), checkedIndex) { size, pos ->
                    (sizeRecView.adapter as ButtonOvalListAdapter).apply {
                        if (checked != pos) {
                            itemSaleProductView.size = size
                            checked = pos
                            notifyDataSetChanged()
                        }
                    }
                }

            val unitNames = bottomSheetDialogBinding.itemSaleProductView.unitNames.toArrayList()
            val unitPos = copyProd.units.indexOf(copyProd.unit)

            unitRecView.adapter = ButtonOvalListAdapter(unitNames, unitPos) { unitName, pos ->
                (unitRecView.adapter as ButtonOvalListAdapter).apply {
                    if (checked != pos) {
                        itemSaleProductView.unit = itemSaleProductView.product.units[pos]
                        checked = pos
                        notifyDataSetChanged()
                    }
                }
            }
        }
        bottomSheetDialog.setOnDismissListener {
            bottomSheetDialogBinding.run {
                val curColor = itemSaleProductView.color
                val curSize = itemSaleProductView.size

                if (curColor != null && curSize != null) {
                    val curProd = itemSaleProductView.product.copy(items = itemSaleProductView.items.toArrayList())
                    try {
                        var selectedItem: Product? = null
                        val isSplitRow = binding.splitRowSwitch.isChecked
                        if (!isSplitRow) {
                            selectedItem = selectedItems.find {
                                it.name == curProd.name && it.code == curProd.code && it.items == curProd.items
                            }
                        }

                        if (selectedItem != null) selectedItem.amount += curProd.amount
                        else selectedItems.add(curProd)

                        Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
                        updateProductSelectedUI()
                    } catch (e: Exception) {
                        Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        bottomSheetDialog.show()
    }

    companion object {
        const val  BUNDLE_SELECTED_ITEMS = "selected_items"
    }
}