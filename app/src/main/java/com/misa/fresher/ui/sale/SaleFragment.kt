package com.misa.fresher.ui.sale

import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.ui.main.MainActivity
import com.misa.fresher.R
import com.misa.fresher.ui.sale.adapter.ButtonOvalListAdapter
import com.misa.fresher.ui.sale.adapter.SaleProductAdapter
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.DialogBottomSheetBinding
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.data.model.product.*
import com.misa.fresher.utils.*
import kotlinx.coroutines.*

class SaleFragment : BaseFragment<FragmentSaleBinding>(FragmentSaleBinding::inflate), SaleContract.View {

    private val bottomSheetDialog by lazy { BottomSheetDialog(requireContext()) }
    private val bottomSheetDialogBinding by lazy { DialogBottomSheetBinding.inflate(layoutInflater) }

    private var presenter: SalePresenter? = null

    object FilterConfig {
        var viewMode = Enums.Product.MODEL
        var textSearch = ""
    }

    override fun initUI() {
        initPresenter()
        initToolbarUI()
        initProductRecViewUI()
        initBottomSheetDialogUI()
        initSaleFilterDrawerUI()

        binding.clearBtn.setOnClickListener { presenter?.clearSelectedProducts() }
        binding.numberBtn.setOnClickListener { presenter?.checkSelectedProducts() }
        binding.priceBtn.setOnClickListener { presenter?.checkSelectedProducts() }
    }

    override fun updateUI() {
        super.updateUI()
        presenter?.getSelectedProductStatistic()
    }

    override fun initPresenter() {
        presenter = SalePresenter().also { it.attach(this) }
    }

    private fun initToolbarUI() {
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
                    presenter?.searchProducts(txtSearch)
                    binding.progressBar.visibility = View.GONE
                    binding.listProductRecView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initProductRecViewUI() {
        binding.listProductRecView.adapter = SaleProductAdapter(arrayListOf(), showBottomSheetDialog)
        presenter?.getDisplayProducts()
    }

    private fun initBottomSheetDialogUI() {
        bottomSheetDialog.setContentView(bottomSheetDialogBinding.root)
        bottomSheetDialogBinding.itemSaleProductView.run {
            binding.btnAdd.setOnClickListener { product.amount += 1 }
            binding.btnMinus.setOnClickListener {
                if (product.amount == 1) context.showToast("Quantity must be more than 0. Please check again.")
                else product.amount -= 1
            }
        }
    }

    private fun initSaleFilterDrawerUI() {
        presenter?.getFilterOptions()
    }

    /**
     * - method purpose: update list product of recycle view after search or filter
     *
     * @author HTLong
     * @edit_at 2022-03-18
     */
    override fun updateProductRecViewUI(products: ArrayList<Product>) {
        (binding.listProductRecView.adapter as SaleProductAdapter).run {
            items = products
            notifyDataSetChanged()
        }
    }

    override fun navToBillFragment(products: ArrayList<Product>) {
        findNavController().navigate(
            R.id.action_fragment_sale_to_fragment_bill, bundleOf(BUNDLE_SELECTED_ITEMS to products)
        )
    }

    private val showBottomSheetDialog: (prod: Product, pos: Int) -> Unit = { prod, _ ->
        val copyProd = prod.copy(amount = 1, image = 0)
        val checkedIndex = if (prod.items.size == 1) 0 else -1

        bottomSheetDialogBinding.run {
            itemSaleProductView.run {
                product = copyProd
                binding.btnAdd.setOnClickListener { amount += 1 }
                binding.btnMinus.setOnClickListener {
                    if (amount == 1) context?.showToast("Quantity must be more than 0. Please check again.")
                    else amount -= 1
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
            val isSplit = binding.splitRowSwitch.isChecked
            bottomSheetDialogBinding.itemSaleProductView.run {
                if (color != null && size != null) {
                    presenter?.selectProduct(isSplit, product.copy(items = items.toArrayList()))
                }
            }
        }
        bottomSheetDialog.show()
    }

    /**
     * - method's purpose: update total quantity, price ui of selected products
     *
     * @author HTLong
     * @edit_at 2022-03-18
     */
    override fun updateProductSelectedUI(totalAmount: Int, totalPrice: Double) {
        binding.apply {
            numberBtn.text = totalAmount.toString()
            if (totalAmount > 0) {
                numberBtn.background = getDrawable(context, R.drawable.bg_btn_round_left_violet)
                numberBtn.setTextColor(getColor(context, R.color.white))

                priceBtn.background = getDrawable(context, R.drawable.bg_btn_round_right_violet)
                priceBtn.text = "Total $totalPrice"
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

    override fun updateSaleFilterDrawerUI(colors: List<String>, sizes: List<String>, categories: List<String>) {
        val drawerLayout = binding.root
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END)
        drawerLayout.setScrimColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))

        val categoryAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(R.layout.item_spinner_item)

        val colorAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_item, colors)
        colorAdapter.setDropDownViewResource(R.layout.item_spinner_item)

        val sizeAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_item, sizes)
        sizeAdapter.setDropDownViewResource(R.layout.item_spinner_item)

        binding.drawerSaleFilter.run {
            spinnerCategory.adapter = categoryAdapter
            spinnerColor.adapter = colorAdapter
            spinnerSize.adapter = sizeAdapter

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
                    val isCheckQTY = swAvailableQuantity.isChecked
                    val category = spinnerCategory.selectedItem.toString()
                    val viewMode = FilterConfig.viewMode
                    val sortBy = when (radioSortBy.checkedRadioButtonId) {
                        R.id.sort_by_name -> "name"
                        else -> "date"
                    }
                    val color = spinnerColor.selectedItem.toString()
                    val size = spinnerSize.selectedItem.toString()
                    val txtSearch = FilterConfig.textSearch

                    binding.progressBar.visibility = View.VISIBLE
                    binding.listProductRecView.visibility = View.GONE
                    delay(300)
                    presenter?.filterProducts(isCheckQTY, category, viewMode, sortBy, color, size, txtSearch)
                    binding.progressBar.visibility = View.GONE
                    binding.listProductRecView.visibility = View.VISIBLE
                }
                binding.root.closeDrawer(GravityCompat.END)
            }
        }
    }

    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }

    companion object {
        const val BUNDLE_SELECTED_ITEMS = "selected_items"
    }
}