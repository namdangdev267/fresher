package com.misa.fresher.ui.login.sale

import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.BottomSheetProductBinding
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.model.Customer
import com.misa.fresher.model.FilterProducts
import com.misa.fresher.model.Products
import com.misa.fresher.showToast
import com.misa.fresher.ui.login.sale.adapter.ProductsAdapter

/**
 * tạo class xử lý các hành động chọn sản phẩm, lọc, tìm sản phẩm, chuyển màn trong màn sale
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class SaleFragment :
    BaseFragment<FragmentSaleBinding, SaleContract.View, SaleContract.Presenter>(),
    SaleContract.View {
    private var mPresenter: SalePresenter? = null
    private var rcv: RecyclerView? = null
    private lateinit var rcvAdapter: ProductsAdapter

    override fun updateRecyclerViewProduct(list: MutableList<Products>) {
        rcvAdapter.mProducts = list
        rcvAdapter.notifyDataSetChanged()
    }

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
        binding.contextSale.ivCancelSale.isVisible=true
    }

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

    override fun initPresenter() {
        mPresenter = SalePresenter().also {
            it.attach(this)
        }
    }

    override fun showErrorMessage(msg: String) {
        TODO("Not yet implemented")
    }

    override fun initUI() {
        initPresenter()
        configRecyclerView()
        configBtnMenu()
        configFilterDrawer()
        configAnotherView()
    }

    private fun configAnotherView() {
        binding.contextSale.btnReset.setOnClickListener {
            mPresenter?.clearSelected()
        }
        binding.contextSale.tvContact.setOnClickListener {
            mPresenter?.setCustomer()
        }
        binding.contextSale.ivContact.setOnClickListener {
            mPresenter?.setCustomer()
        }
        val etSearch = binding.customToolbar.binding.edSearch
        etSearch.doAfterTextChanged {
            val text = etSearch.text
            mPresenter?.searchEvent(text.toString())
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
        rcv = binding.contextSale.rcvListProduct
        rcvAdapter = ProductsAdapter(arrayListOf(), { productClick(it) })
        rcv?.adapter = rcvAdapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
        mPresenter?.getDisplayRecyclerView()
    }

    private fun productClick(products: Products) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetDialogBinding = BottomSheetProductBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetDialogBinding.root)
        val ivRemove = bottomSheetDialogBinding.ivRemove
        val ivAdd = bottomSheetDialogBinding.ivAdd
        val tvAmount = bottomSheetDialogBinding.tvProductAmontDialog
        bottomSheetDialogBinding.tvProductNameDialog.text = products.name
        bottomSheetDialogBinding.tvProductIdDialog.text = products.id

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

    override val getInflater: (LayoutInflater) -> FragmentSaleBinding = FragmentSaleBinding::inflate

//    private fun deleteCustomer(view: View) {
//        val ivCancel = view.findViewById<ImageView>(R.id.ivCancelSale)
//        val tvCustomer = view.findViewById<TextView>(R.id.tvContact)
//        ivCancel.setOnClickListener {
//            customerViewModel.deleteCustomer()
//            ivCancel.isVisible=false
//            tvCustomer.hint = "Tên khách hàng, số điện thoại"
//            tvCustomer.text = ""
//            tvCustomer.isSelected=false
//        }
//    }
//
//    /**
//    * Cập nhật khách hàng
//    * @Auther : NTBao
//    * @date : 3/18/2022
//    **/
//    private fun updateReceiver(view: View) {
//        val ivCancel = view.findViewById<ImageView>(R.id.ivCancelSale)
//        val tvCustomer = view.findViewById<TextView>(R.id.tvContact)
//        customerViewModel.customer.observe(viewLifecycleOwner, Observer {
//            if(it==null){
//                ivCancel.isVisible=false
//            }else{
//                ivCancel.isVisible=true
//                tvCustomer.text = it?.name + "(" + it?.number + ")"
//                tvCustomer.isSelected = true
//            }
//        })
//    }


}