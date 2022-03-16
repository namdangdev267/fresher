package com.misa.fresher.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapter.ButtonListAdapter
import com.misa.fresher.adapter.SaleProductAdapter
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.DialogBottomSheetBinding
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.models.product.*
import com.misa.fresher.utils.Enums
import com.misa.fresher.utils.Utils
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class SaleFragment : BaseFragment<FragmentSaleBinding>(FragmentSaleBinding::inflate) {

    private var _bottomSheetDialog: BottomSheetDialog? = null
    private var _bottomSheetDialogBinding: DialogBottomSheetBinding? = null

    private val bottomSheetDialog get() = _bottomSheetDialog!!
    private val bottomSheetDialogBinding get() = _bottomSheetDialogBinding!!

    private var totalItems = fakeProductList()
    private var filteredItems = totalItems
    private var displayedItems = filteredItems
    private var selectedItems = arrayListOf<Product>()

    var timer: Timer? = null
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
        val textColorWhite = ContextCompat.getColor(requireContext(), R.color.white)
        val textColorBlack = ContextCompat.getColor(requireContext(), R.color.black)
        val bgBtnRoundLeftActive = AppCompatResources.getDrawable(requireContext(), R.drawable.bg_btn_round_left_violet)
        val bgBtnRoundLeft = AppCompatResources.getDrawable(requireContext(), R.drawable.bg_btn_round_left)
        val bgBtnRoundRightActive =
            AppCompatResources.getDrawable(requireContext(), R.drawable.bg_btn_round_right_violet)
        val bgBtnRoundRight = AppCompatResources.getDrawable(requireContext(), R.drawable.bg_btn_round_right)

        val itemAmount = selectedItems.sumOf { it.getAmount() }
        val totalPrice = "Total ${selectedItems.sumOf { it.getTotalPrice() }}"
        binding.apply {
            numberBtn.text = itemAmount.toString()
            if (itemAmount > 0) {
                numberBtn.background = bgBtnRoundLeftActive
                numberBtn.setTextColor(textColorWhite)

                priceBtn.background = bgBtnRoundRightActive
                priceBtn.text = totalPrice
                priceBtn.setTextColor(textColorWhite)

                clearBtn.isActive = true
            } else {
                numberBtn.background = bgBtnRoundLeft
                numberBtn.setTextColor(textColorBlack)

                priceBtn.background = bgBtnRoundRight
                priceBtn.text = getString(R.string.btn_product_selected)
                priceBtn.setTextColor(textColorBlack)

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
            if(txtSearch != FilterConfig.textSearch) {
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
            if(selectedItems.size > 0) {
                findNavController().navigate(R.id.action_fragment_sale_to_fragment_bill, bundleOf("selected_items" to selectedItems))
            }
        }
        binding.numberBtn.setOnClickListener(navToBillFragment)
        binding.priceBtn.setOnClickListener(navToBillFragment)
    }
    private fun initBottomSheetDialogListener() {
        bottomSheetDialogBinding.itemSaleProductView.run {
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
    }
    private fun initSaleFilterDrawerListener() {
        val textColorWhite = ContextCompat.getColor(requireContext(), R.color.white)
        val textColorPurple = ContextCompat.getColor(requireContext(), R.color.purpleDark)
        val bgBtnActive = AppCompatResources.getDrawable(requireContext(), R.drawable.bg_btn_round_violet)
        val bgBtn = AppCompatResources.getDrawable(requireContext(), R.drawable.bg_btn_round_border_violet)

        binding.drawerSaleFilter.run {
            btnModel.setOnClickListener {
                FilterConfig.viewMode = Enums.Product.MODEL
                btnModel.background = bgBtnActive
                btnModel.setTextColor(textColorWhite)
                btnItem.background = bgBtn
                btnItem.setTextColor(textColorPurple)
            }
            btnItem.setOnClickListener {
                FilterConfig.viewMode = Enums.Product.ITEM
                btnItem.background = bgBtnActive
                btnItem.setTextColor(textColorWhite)
                btnModel.background = bgBtn
                btnModel.setTextColor(textColorPurple)
            }
            btnClear.setOnClickListener {
                FilterConfig.viewMode = Enums.Product.MODEL
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
            displayedItems =
                if(FilterConfig.textSearch == "") filteredItems
                else Utils.listToArrayList(filteredItems.filter { it.name.lowercase().contains(FilterConfig.textSearch) })
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
                    if(category != "all") filteredItems = Utils.listToArrayList(filteredItems.filter { it.category == category })
                    if(color != "all") filteredItems = Utils.listToArrayList(filteredItems.filter { it.getColors().contains(color)})
                    if(size != "all") filteredItems = Utils.listToArrayList(filteredItems.filter { it.getSizes(null).contains(size)})
                    if(isCheckAvailableQTY) filteredItems = Utils.listToArrayList(filteredItems.filter { it.quantity() > 0})

                } else if(viewMode == Enums.Product.ITEM) {
                    filteredItems = arrayListOf()
                    for (product in totalItems) {
                        if(category != "all" && product.category != category) continue
                        for (item in product.items) {
                            if(
                                (color == "all" || item.color == color)
                                && (size == "all" || item.size == size)
                                && (!isCheckAvailableQTY || item.quantity > 0)
                            ) {
                                filteredItems.add(product.copy(items = arrayListOf(item)))
                            }
                        }
                    }
                }

                if(sortBy == R.id.sort_by_name) filteredItems.sortBy { it.name }
                else if(sortBy == R.id.sort_by_date) filteredItems.sortBy { it.date }
            }
        }
    }

    private val showBottomSheetDialog: (product: Product, pos: Int) -> Unit = { product, _ ->
        bottomSheetDialogBinding.apply {
            itemSaleProductView.apply {
                name = product.name
                code = product.code
                price = product.getPrice()
                unit = product.unit
                amount = 1
            }

            val colors = product.getColors()
            val sizes = product.getSizes(colors[0])
            val unitNames = product.getUnitNames()
            val unitPos = product.units.indexOf(product.unit)

            colorRecView.adapter = ButtonListAdapter(colors, 0) { color, pos ->
                (colorRecView.adapter as ButtonListAdapter).apply {
                    if (checked != pos) {
                        itemSaleProductView.price = product.getPrice(color)
                        checked = pos

                        notifyDataSetChanged()
                        (sizeRecView.adapter as ButtonListAdapter).apply {
                            items = product.getSizes(color)
                            checked = -1
                            notifyDataSetChanged()
                        }
                    }
                }
            }
            sizeRecView.adapter = ButtonListAdapter(sizes, -1) { size, pos ->
                (sizeRecView.adapter as ButtonListAdapter).apply {
                    if (checked != pos) {
                        var color: String?
                        (colorRecView.adapter as ButtonListAdapter).apply { color = items[checked] }
                        itemSaleProductView.price = product.getPrice(color, size)
                        checked = pos
                        notifyDataSetChanged()
                    }
                }
            }
            unitRecView.adapter = ButtonListAdapter(unitNames, unitPos) { _, pos ->
                (unitRecView.adapter as ButtonListAdapter).apply {
                    if (checked != pos) {
                        itemSaleProductView.unit = product.units[pos]
                        checked = pos
                        notifyDataSetChanged()
                    }
                }
            }
        }
        bottomSheetDialog.setOnDismissListener {
            bottomSheetDialogBinding.apply {
                try {
                    var size: String?
                    var color: String?
                    var unit: ProductUnit
                    (sizeRecView.adapter as ButtonListAdapter).apply { size = items[checked] }
                    (colorRecView.adapter as ButtonListAdapter).apply { color = items[checked] }
                    (unitRecView.adapter as ButtonListAdapter).apply {
                        unit = product.units.find { it.name == items[checked] }!!
                    }

                    val selectedItem = selectedItems.find { it.code == product.code && it.items[0].size == size && it.items[0].color == color && it.unit == unit }

                    if (selectedItem != null) selectedItem.items[0].amount += itemSaleProductView.amount
                    else product.items.find { it.color == color && it.size == size }?.let { item ->
                        selectedItems.add(
                            Product(
                                name = product.name, code = product.code, items = arrayListOf(
                                    ProductItem(
                                        size = item.size,
                                        color = item.color,
                                        price = item.price,
                                        quantity = item.quantity,
                                        amount = itemSaleProductView.amount
                                    )
                                ), units = arrayListOf(itemSaleProductView.unit), unit = unit
                            )
                        )
                    }
                    Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
                    updateProductSelectedUI()
                } catch (e: Exception) {
                    Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show()
                }
            }
        }
        bottomSheetDialog.show()
    }

    private fun fakeProductList(): ArrayList<Product> {
        val items1: ArrayList<ProductItem> = arrayListOf(
            ProductItem("A", "red", 1000.0, 10),
            ProductItem("L", "red", 3000.0, 10),
            ProductItem("M", "red", 3000.0, 10),
        )
        val items2: ArrayList<ProductItem> = arrayListOf(
            ProductItem("B", "black", 1600.0, 0),
            ProductItem("I", "black", 2600.0, 0),
            ProductItem("H", "black", 3600.0, 0),
        )
        val items3: ArrayList<ProductItem> = arrayListOf(
            ProductItem("S", "blue", 1000.0, 0),
            ProductItem("L", "blue", 3200.0, 12),
            ProductItem("K", "blue", 1600.0, 0),
            ProductItem("I", "blue", 2600.0, 3),
        )

        val units: ArrayList<ProductUnit> = arrayListOf(ProductUnit("Piece", 1), ProductUnit("Pair", 2), ProductUnit("Set", 3), ProductUnit("Dozen", 12))

        val products: ArrayList<Product> = arrayListOf()
        for (i in 5 downTo 1) products.add(
            Product("name $i", "code$i", "type${i / 2}", i * 1000L, R.drawable.ic_product, items1, units, units[0])
        )

        for (i in 15 downTo 10) products.add(
            Product("name $i", "code$i", "type${i / 4}", i * 1000L, R.drawable.ic_product, items3, units, units[0])
        )
        for (i in 10 downTo 5) products.add(
            Product("name $i", "code$i", "type${i / 3}", i * 1000L, R.drawable.ic_product, items2, units, units[0])
        )
        return products
    }
}