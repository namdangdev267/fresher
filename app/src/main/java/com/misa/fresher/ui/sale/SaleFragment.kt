package com.misa.fresher.ui.sale

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.data.model.Customer
import com.misa.fresher.data.model.FilterProducts
import com.misa.fresher.data.model.Products
import com.misa.fresher.data.model.SelectedProducts
import com.misa.fresher.databinding.BottomSheetProductBinding
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.showToast
import com.misa.fresher.ui.sale.adapter.ProductsAdapter

/**
 * tạo class xử lý các hành động chọn sản phẩm, lọc, tìm sản phẩm, chuyển màn trong màn sale
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class SaleFragment :
        BaseFragment<FragmentSaleBinding, SaleContract.View, SaleContract.Presenter>(),
        SaleContract.View {
    private var mPresenter: SalePresenter? = null
    private var rcvAdapter: ProductsAdapter? = null

    override val getInflater: (LayoutInflater) ->
    FragmentSaleBinding = FragmentSaleBinding::inflate

    /**
     * Hủy presenter
     * @Auther : NTBao
     * @date : 3/22/2022
     **/
    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detach()
    }

    override fun initUI() {
        initPresenter()
        updateViewSelected()
        configRecyclerView()
        configBtnMenu()
        configFilterDrawer()
        configAnotherView()
    }

    /**
     * Tính lại số lượng, tổng tiền khi click sản phẩm
     * @Auther : NTBao
     * @date : 3/22/2022
     **/
    private fun updateViewSelected() {
        mPresenter?.getSelectedProduct()
    }

    /**
     * update data cho adapter khi có thay đổi(lọc, tìm kiếm)
     * @Auther : NTBao
     * @date : 3/22/2022
     **/
    override fun updateRecyclerViewProduct(list: MutableList<Products>) {
        rcvAdapter?.mProducts = list
        rcvAdapter?.notifyDataSetChanged()
    }

    /**
     * update Ui khi có item được click
     * @Auther : NTBao
     * @date : 3/22/2022
     **/
    override fun updateSelectedProduct(amount: Int, price: Double) {
        val btnReset = binding.contextSale.btnReset
        val btnAmount = binding.contextSale.btnItemCount
        val btnTotalPrice = binding.contextSale.btnTotalPrice
        if (amount > 0) {
            btnReset.background = AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.oval_button
            )
            btnAmount.run {
                this.text = amount.toString()
                this.background = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.item_amount_bg_selected
                )
            }
            btnTotalPrice.run {
                this.text = "Tổng $price"
                this.background = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.total_price_selected
                )
            }
        } else {
            btnReset.background = AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.oval_button_base
            )
            btnAmount.run {
                this.text = amount.toString()
                this.background = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.item_amount_bg_base
                )
            }
            btnTotalPrice.run {
                this.text = "Tổng $price"
                this.background = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.total_price_base
                )
            }
        }
    }

    override fun updateReceiver(customer: Customer) {
        val tvContact = binding.contextSale.tvContact
        tvContact.text = "${customer.name} - ${customer.address}"
        tvContact.isSelected = true
        binding.contextSale.ivCancelSale.isVisible = true
    }

    /**
     * lấy giá trị để filter
     * @Auther : NTBao
     * @date : 3/22/2022
     **/
    override fun getFilter(): FilterProducts {
        val radioGroup = binding.contextDrawerFilter.rgSorting
        val selected = radioGroup.checkedRadioButtonId
        val radioButtonText = when (selected) {
            R.id.rb_name -> "Tên"
            else -> "Giá"
        }
        val mColorSpinner = binding.contextDrawerFilter.spnColor
        val mSizeSpinner = binding.contextDrawerFilter.spnSize
        val colorSelected = mColorSpinner.selectedItem.toString()
        val sizeSelected = mSizeSpinner.selectedItem.toString()
        val filterProducts = FilterProducts(radioButtonText, colorSelected, sizeSelected)
        return filterProducts
    }

    /**
     * chuyển sang màn bill detail
     * @Auther : NTBao
     * @date : 3/22/2022
     **/
    override fun navigation(list: MutableList<SelectedProducts>) {
        findNavController().navigate(
                R.id.action_nav_sale_to_nav_billDetail,
                bundleOf(BUNDLE_SELECTEDITEM to list)
        )
    }


    /**
     * khởi tạo presenter
     * @Auther : NTBao
     * @date : 3/22/2022
     **/
    override fun initPresenter() {
        if (mPresenter == null) {
            mPresenter = SalePresenter(requireContext()).also {
                it.attach(this)
            }
        }
    }

    override fun showErrorMessage(msg: String) {
        TODO("Not yet implemented")
    }


    private fun configAnotherView() {
        binding.contextSale.btnReset.setOnClickListener {
            mPresenter?.clearSelected()
        }
        val etSearch = binding.customToolbar.binding.edSearch
        etSearch.addTextChangedListener {
            val text = it.toString()
            mPresenter?.searchEvent(text.toString())
        }
        binding.contextSale.btnTotalPrice.setOnClickListener {
            mPresenter?.getListSelectedProductToBillDetail()
        }
    }

    private fun configFilterDrawer() {
        val mDrawer = binding.dlSaleFilter
        val btnSave = binding.contextDrawerFilter.btnFilterSave
        val btnClear = binding.contextDrawerFilter.btnFilterClear
        val spnColor = binding.contextDrawerFilter.spnColor
        val spnSize = binding.contextDrawerFilter.spnSize

        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        mDrawer.setScrimColor(Color.TRANSPARENT)

        ArrayAdapter.createFromResource(
                requireContext(),
                R.array.colorArray,
                android.R.layout.simple_spinner_item

        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnColor.adapter = arrayAdapter
        }
        ArrayAdapter.createFromResource(
                requireContext(),
                R.array.sizeArray,
                android.R.layout.simple_spinner_item

        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnSize.adapter = arrayAdapter
        }

        binding.customToolbar.binding.btnFilter.setOnClickListener {
            mDrawer.openDrawer(Gravity.RIGHT)
        }

        btnSave.setOnClickListener {
            mPresenter?.filterItems(getFilter())
            mDrawer.closeDrawer(Gravity.RIGHT)
        }
        btnClear.setOnClickListener {
            binding.contextDrawerFilter.spnColor.setSelection(0)
            binding.contextDrawerFilter.spnSize.setSelection(0)
            binding.contextDrawerFilter.rbName.isChecked = true
        }
    }

    private fun configBtnMenu() {
        binding.btnMenuSale.setOnClickListener {
            (activity as MainActivity).toggle()
        }
    }

    private fun configRecyclerView() {
        val rcv = binding.contextSale.rcvListProduct
        rcvAdapter = ProductsAdapter(arrayListOf(), { productClick(it) })
        rcv.adapter = rcvAdapter
        rcv.layoutManager = LinearLayoutManager(requireContext())
        mPresenter?.getListProductFromDb()
    }

    private fun productClick(products: Products) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetDialogBinding = BottomSheetProductBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetDialogBinding.root)
        val ivRemove = bottomSheetDialogBinding.ivRemove
        val ivAdd = bottomSheetDialogBinding.ivAdd
        val tvAmount = bottomSheetDialogBinding.tvProductAmontDialog
        bottomSheetDialogBinding.tvProductNameDialog.text = products.name
        bottomSheetDialogBinding.tvProductIdDialog.text = products.code

        var amount = bottomSheetDialogBinding.tvProductAmontDialog.text.toString().toInt()

        bottomSheetDialog.show()

        ivRemove.setOnClickListener {
            if (tvAmount.text == "1") {
                requireContext().showToast("Số lượng phải lớn hơn 0. Vui lòng kiểm tra lại")
            } else {
                amount--
                tvAmount.text = "$amount"
            }
        }
        ivAdd.setOnClickListener {
            amount++
            tvAmount.text = "$amount"
        }
        bottomSheetDialog.setOnDismissListener {
            mPresenter?.selectProduct(products, amount)
            mPresenter?.getSelectedProduct()
            tvAmount.text = "1"
        }
    }

    companion object {
        const val BUNDLE_SELECTEDITEM = "items"
    }
}