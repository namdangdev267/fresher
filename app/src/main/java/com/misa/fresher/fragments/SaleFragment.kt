package com.misa.fresher.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapters.ProductsAdapter
import com.misa.fresher.data.DataForTest
import com.misa.fresher.model.Customer
import com.misa.fresher.model.SelectedProducts
import com.misa.fresher.model.FilterProducts
import com.misa.fresher.model.Products
import com.misa.fresher.showToast
import com.misa.fresher.viewModel.CustomerViewModel

/**
 * tạo class xử lý các hành động chọn sản phẩm, lọc, tìm sản phẩm, chuyển màn trong màn sale
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class SaleFragment : Fragment() {
    private var rcv: RecyclerView? = null
    private var amount = 1
    private var fakedata = DataForTest.listProduct
    private var productsSelected = mutableListOf<SelectedProducts>()
    private lateinit var rcvAdapter: ProductsAdapter
    private val customerViewModel: CustomerViewModel by activityViewModels()
    private var cus: Customer? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configBtnMenu(view)
        configRecyclerView(view)
        updateProductSelected(view)
        searchEvent(view)
        configFilterDrawer(view)
        resetEvent(view)
        navigateEvent(view)
        setCustomer(view)
    }

    /**
     * Khi click vào tv tên khách hàng -> lấy ngấu nhiên 1 khách hàng trong list -> add vào CusViewModel
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun setCustomer(view: View) {
        val tvCus = view.findViewById<TextView>(R.id.tvContact)
        tvCus.setOnClickListener {
            cus = DataForTest.listCus.get((0..DataForTest.listCus.size - 1).random())
            tvCus.text = "${cus?.name} (${cus?.number})"
            tvCus.isSelected = true
            customerViewModel.addCustomer(cus!!)
        }
    }

    /**
     * config drawer cho filter
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configFilterDrawer(view: View) {
        configFilterSpinner(view)
        val mDrawer = view.findViewById<DrawerLayout>(R.id.dlSaleFilter)
        mDrawer.setScrimColor(Color.TRANSPARENT)
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        view.findViewById<ImageButton>(R.id.btnFilter)?.setOnClickListener {
            mDrawer.openDrawer(Gravity.RIGHT)
        }
        val btnSave = view.findViewById<Button>(R.id.btnFilterSave)
        val btnClear = view.findViewById<Button>(R.id.btnFilterClear)
        btnSave.setOnClickListener {
            filterItems(getFilter(view))
            mDrawer?.closeDrawer(Gravity.RIGHT)
        }
        btnClear.setOnClickListener {
            view.findViewById<Spinner>(R.id.spnColor)?.setSelection(0)
            view.findViewById<Spinner>(R.id.spnSize)?.setSelection(0)
            view.findViewById<RadioButton>(R.id.rb_name)?.isChecked = true
        }
    }

    /**
     * Lấy filter để lọc cho list sản phẩm
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun getFilter(view: View): FilterProducts {
        val radioGroup = view.findViewById<RadioGroup>(R.id.rg_sorting)
        val selected = radioGroup.checkedRadioButtonId
        val radioButtonText = selected.let { view.findViewById<RadioButton>(it)?.text }
        val mColorSpinner = view.findViewById<Spinner>(R.id.spnColor)
        val mSizeSpinner = view.findViewById<Spinner>(R.id.spnSize)
        val colorSelected = mColorSpinner.selectedItem.toString()
        val sizeSelected = mSizeSpinner.selectedItem.toString()
        val filterProducts = FilterProducts(radioButtonText.toString(), colorSelected, sizeSelected)
        return filterProducts
    }

    /**
     * Set giá trị cho spinner
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configFilterSpinner(view: View) {
        val spnColor = view.findViewById<Spinner>(R.id.spnColor)
        val spnSize = view.findViewById<Spinner>(R.id.spnSize)
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
    }

    /**
     * Lọc sản phẩm
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    @SuppressLint("NotifyDataSetChanged")
    private fun filterItems(filter: FilterProducts) {
        var sortList = mutableListOf<Products>()
        if (filter.sortBy == "Tên") {
            sortList = fakedata.sortedWith(compareBy(Products::name)) as MutableList<Products>
        } else if (filter.sortBy == "Giá") {
            sortList = fakedata.sortedWith(compareBy(Products::price)) as MutableList<Products>
        }
        var sortListWithColor = mutableListOf<Products>()
        var sortListWithSize = mutableListOf<Products>()
        if (filter.coler != "cham de chon") {
            // nếu trùng với phần tử đầu tiên của arrayColor thì không xử lý
            for (i in sortList) {
                if (i.color == filter.coler) {
                    sortListWithColor.add(i)
                }
            }
            if (filter.size != "cham de chon") {
                // nếu trùng với phần tử đầu tiên của arraySize thì không xử lý
                for (i in sortListWithColor) {
                    if (i.size == filter.size) {
                        sortListWithSize.add(i)
                    }
                }
            } else {
                sortListWithSize = sortListWithColor
            }
        } else {
            sortListWithColor = sortList
            if (filter.size != "cham de chon") {
                // nếu trùng với phần tử đầu tiên của arraySize thì không xử lý
                for (i in sortListWithColor) {
                    if (i.size == filter.size) {
                        sortListWithSize.add(i)
                    }
                }
            } else {
                sortListWithSize = sortListWithColor
            }
        }
        rcvAdapter.mProducts = sortListWithSize
        rcvAdapter.notifyDataSetChanged()
    }

    /**
     * Set sự kiện cho nút reset sản phẩm
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun resetEvent(view: View) {
        val btnItemCount = view.findViewById<Button>(R.id.btnItemCount)
        val btnTotalPrice = view.findViewById<Button>(R.id.btnTotalPrice)
        view.findViewById<ImageButton>(R.id.btnReset)?.setOnClickListener {
            productsSelected.clear()
            it.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.oval_button_base)
            btnItemCount.run {
                this.text = "0"
                this.background = AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.item_amount_bg_base
                )
            }
            btnTotalPrice.run {
                this.text = "Chưa nhập hàng"
                this.background =
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.total_price_base
                    )
            }
        }
    }

    /**
     * Lấy giá trị text cần tìm kiếm
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun searchEvent(view: View) {
        val edSearch = view.findViewById<EditText>(R.id.edSearch)
        edSearch?.doAfterTextChanged {
            updateList(edSearch.text.toString())
        }
    }

    /**
     * Chuyển màn
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun navigateEvent(view: View) {
        view.findViewById<Button>(R.id.btnTotalPrice)?.setOnClickListener {
            if (productsSelected.size > 0) {
                findNavController().navigate(
                    R.id.action_nav_sale_to_nav_billDetail,
                    bundleOf(SELECTED_ITEMS to productsSelected)
                )
                Log.d("test", cus.toString())
            }
        }
        view.findViewById<Button>(R.id.btnItemCount)?.setOnClickListener {
            if (productsSelected.size > 0) {
                findNavController().navigate(
                    R.id.action_nav_sale_to_nav_billDetail,
                    bundleOf(SELECTED_ITEMS to productsSelected)
                )
            }
        }
    }

    /**
     * Tìm kiếm sản phẩm theo tên, mã
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    @SuppressLint("NotifyDataSetChanged")
    private fun updateList(string: String) {
        val list = mutableListOf<Products>()
        for (data in fakedata) {
            if (data.name.uppercase().contains(string.uppercase()) ||
                data.id.uppercase().contains(string.uppercase())
            ) {
                list.add(data)
            }
        }
        rcvAdapter.mProducts = list
        rcvAdapter.notifyDataSetChanged()
    }

    private fun configBtnMenu(view: View) {
        view.findViewById<ImageButton>(R.id.btnMenuSale).setOnClickListener {
            (activity as MainActivity).findViewById<DrawerLayout>(R.id.drawerLayout)
                .openDrawer(Gravity.LEFT)
        }
    }

    /**
     * config recycler view list product
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configRecyclerView(view: View) {
        rcvAdapter = ProductsAdapter(fakedata, { productItemClick(it, view) })
        rcv = view.findViewById(R.id.rcvListProduct)
        rcv?.adapter = rcvAdapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * click sản phẩm -> show dialog -> dismiss -> add sản phẩm vào list -> update
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun productItemClick(products: Products, view: View) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
        val bottomSheetView = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottom_sheet_product,
            view as DrawerLayout, false
        )
        bottomSheetView?.findViewById<TextView>(R.id.tvProductNameDialog)?.text = products.name
        bottomSheetView?.findViewById<TextView>(R.id.tvProductIdDialog)?.text = products.id
        bottomSheetDialog.setContentView(bottomSheetView!!)
        bottomSheetDialog.show()
        changeItemAmount(bottomSheetView)
        bottomSheetDialog.setOnDismissListener {
            productsSelected.find { it.product == products }?.let {
                it.amonut += amount
            } ?: run {
                productsSelected.add(SelectedProducts(amount, products))
            }
            updateProductSelected(view)
        }

    }

    /**
     * Thay đổi số lượng sản phẩm
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun changeItemAmount(bottomSheetView: View) {
        val btnRemove = bottomSheetView.findViewById<ImageView>(R.id.ivRemove)
        val btnAdd = bottomSheetView.findViewById<ImageView>(R.id.ivAdd)
        val tvAmount = bottomSheetView.findViewById<TextView>(R.id.tvProductAmontDialog)
        amount = tvAmount?.text.toString().toInt()
        btnRemove?.setOnClickListener {
            if (tvAmount?.text == "1") {
                requireContext().showToast("Số lượng phải lớn hơn 0. Vui lòng kiểm tra lại")
            } else {
                amount--
                tvAmount?.text = "$amount"
            }
        }
        btnAdd?.setOnClickListener {
            amount++
            tvAmount?.text = "$amount"
        }
    }

    /**
     * update list product selected
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    @SuppressLint("ResourceAsColor", "SetTextI18n")
    private fun updateProductSelected(view: View) {
        val btnItemCount = view.findViewById<Button>(R.id.btnItemCount)
        val amount = productsSelected.sumOf { it.amonut }
        val totalPrice = productsSelected.sumOf { it.amonut * it.product.price }

        if (productsSelected.size > 0) {
            btnItemCount.let {
                it.text = "$amount"
                it.background = AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.item_amount_bg_selected
                )
            }
            val btnTotalPrice = view.findViewById<Button>(R.id.btnTotalPrice)
            btnTotalPrice.let {
                it.text = "Tổng $totalPrice"
                it.background =
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.total_price_selected
                    )
            }
            view.findViewById<ImageButton>(R.id.btnReset)?.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.oval_button)
        }
    }

    companion object {
        const val SELECTED_ITEMS = "items"
    }

}