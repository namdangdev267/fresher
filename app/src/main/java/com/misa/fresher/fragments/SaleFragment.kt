package com.misa.fresher.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapters.ProductsAdapter
import com.misa.fresher.customViews.CustomToast
import com.misa.fresher.data.DataForTest
import com.misa.fresher.model.SelectedProducts
import com.misa.fresher.model.FilterProducts
import com.misa.fresher.model.Products

class SaleFragment : Fragment() {

    private var globleView: View? = null
    private var rcv: RecyclerView? = null
    private var bottomSheetView: View? = null
    private var amount = 1
    private var fakedata = DataForTest.listProduct
    private var productsSelected = mutableListOf<SelectedProducts>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        globleView = view
        configToolBar()
        configRecyclerView()
        updateItemSelected()
        searchEvent()
        configFilterDrawer()
        resetEvent()
        navigateEvent()

    }

    private fun configFilterDrawer() {
        configFilterSpinner()
        val mDrawer = globleView?.findViewById<DrawerLayout>(R.id.dlSaleFilter)
        mDrawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        globleView?.findViewById<ImageButton>(R.id.btnFilter)?.setOnClickListener {
            mDrawer?.openDrawer(Gravity.RIGHT)
        }
        val btnSave = globleView?.findViewById<Button>(R.id.btnFilterSave)
        val btnClear = globleView?.findViewById<Button>(R.id.btnFilterClear)
        btnSave?.setOnClickListener {
            filterItems(getFilter())
            mDrawer?.closeDrawer(Gravity.RIGHT)
        }
        btnClear?.setOnClickListener {
            globleView?.findViewById<Spinner>(R.id.spnColor)?.setSelection(0)
            globleView?.findViewById<Spinner>(R.id.spnSize)?.setSelection(0)
            globleView?.findViewById<RadioButton>(R.id.rb_name)?.isChecked = true
        }
    }

    private fun getFilter(): FilterProducts {
        val radioGroup = globleView?.findViewById<RadioGroup>(R.id.rg_sorting)
        val selected = radioGroup?.checkedRadioButtonId
        val radioButtonText = selected?.let { globleView?.findViewById<RadioButton>(it)?.text }
        val mColorSpinner = globleView?.findViewById<Spinner>(R.id.spnColor)
        val mSizeSpinner = globleView?.findViewById<Spinner>(R.id.spnSize)
        val colorSelected = mColorSpinner?.selectedItem.toString()
        val sizeSelected = mSizeSpinner?.selectedItem.toString()
        val filterProducts = FilterProducts(radioButtonText.toString(), colorSelected, sizeSelected)
        return filterProducts
    }

    private fun configFilterSpinner() {
        val spnColor = globleView?.findViewById<Spinner>(R.id.spnColor)
        val spnSize = globleView?.findViewById<Spinner>(R.id.spnSize)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.colorArray,
            android.R.layout.simple_spinner_item

        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnColor?.adapter = arrayAdapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sizeArray,
            android.R.layout.simple_spinner_item

        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnSize?.adapter = arrayAdapter
        }
    }

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
            for (i in sortList) {
                if (i.color == filter.coler) {
                    sortListWithColor.add(i)
                }
            }
            if (filter.size != "cham de chon") {
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
                for (i in sortListWithColor) {
                    if (i.size == filter.size) {
                        sortListWithSize.add(i)
                    }
                }
            } else {
                sortListWithSize = sortListWithColor
            }
        }
        rcv?.adapter = ProductsAdapter(sortListWithSize, { productItemClick(it) })
        (rcv?.adapter)?.notifyDataSetChanged()
    }

    private fun resetEvent() {
        globleView?.findViewById<ImageButton>(R.id.btnReset)?.setOnClickListener {
            productsSelected.clear()
            it.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.oval_button_base)
            val btnItemCount = globleView?.findViewById<Button>(R.id.btnItemCount)
            btnItemCount?.let {
                it.text = "0"
                it.background = AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.item_amount_bg_base
                )
            }
            val btnTotalPrice = globleView?.findViewById<Button>(R.id.btnTotalPrice)
            btnTotalPrice?.let {
                it.text = "Chưa nhập hàng"
                it.background =
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.total_price_base
                    )
            }
        }
    }

    private fun searchEvent() {
        val edSearch = globleView?.findViewById<EditText>(R.id.edSearch)
        edSearch?.doAfterTextChanged {
            updateList(edSearch.text.toString())
        }
    }

    private fun navigateEvent() {
        globleView?.findViewById<Button>(R.id.btnTotalPrice)?.setOnClickListener {
            if (productsSelected.size > 0) {
                findNavController().navigate(
                    R.id.action_nav_sale_to_nav_billDetail,
                    bundleOf("items" to productsSelected)
                )
            }
        }
        globleView?.findViewById<Button>(R.id.btnItemCount)?.setOnClickListener {
            if (productsSelected.size > 0) {
                findNavController().navigate(
                    R.id.action_nav_sale_to_nav_billDetail,
                    bundleOf("items" to productsSelected)
                )
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateList(string: String) {
        val list = mutableListOf<Products>()
        for (i in fakedata) {
            if (i.name.uppercase().contains(string.uppercase()) ||
                i.id.uppercase().contains(string.uppercase())
            ) {
                list.add(i)
            }
        }
        rcv?.adapter = ProductsAdapter(list, { productItemClick(it) })
        (rcv?.adapter)?.notifyDataSetChanged()
    }

    private fun configToolBar() {
        val toolbar = globleView?.findViewById<Toolbar>(R.id.tbSale)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val dlSale = (activity as MainActivity).findViewById<DrawerLayout>(R.id.drawerLayout)
        val toggle = ActionBarDrawerToggle(
            requireActivity(),
            dlSale, toolbar, 0, 0
        )
        dlSale.addDrawerListener(toggle)
        toggle.syncState()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun configRecyclerView() {
        val adapter = ProductsAdapter(fakedata, { productItemClick(it) })
        adapter.notifyDataSetChanged()
        rcv = globleView?.findViewById(R.id.rcvListProduct)
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun productItemClick(products: Products) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
        bottomSheetView = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottom_sheet_product,
            globleView as DrawerLayout, false
        )
        bottomSheetView?.findViewById<TextView>(R.id.tvProductNameDialog)?.text = products.name
        bottomSheetView?.findViewById<TextView>(R.id.tvProductIdDialog)?.text = products.id
        bottomSheetDialog.setContentView(bottomSheetView!!)
        bottomSheetDialog.show()
        changeItemAmount()
        bottomSheetDialog.setOnDismissListener {
            productsSelected.find { it.product == products }?.let {
                it.amonut += amount
            } ?: run {
                productsSelected.add(SelectedProducts(amount, products))
            }
            updateItemSelected()
        }

    }

    private fun changeItemAmount() {
        val btnRemove = bottomSheetView?.findViewById<ImageView>(R.id.ivRemove)
        val btnAdd = bottomSheetView?.findViewById<ImageView>(R.id.ivAdd)
        val tvAmount = bottomSheetView?.findViewById<TextView>(R.id.tvProductAmontDialog)
        amount = tvAmount?.text.toString().toInt()
        btnRemove?.setOnClickListener {
            if (tvAmount?.text == "1") {
                CustomToast.makeText(
                    requireContext(),
                    "Số lượng phải lớn hơn 0. Vui Lòng kiểm tra lại",
                    Toast.LENGTH_SHORT
                ).show()
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

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    private fun updateItemSelected() {
        val btnItemCount = globleView?.findViewById<Button>(R.id.btnItemCount)
        val amount = productsSelected.sumOf { it.amonut }
        val totalPrice = productsSelected.sumOf { it.amonut * it.product.price }

        if (productsSelected.size > 0) {
            btnItemCount?.let {
                it.text = "$amount"
                it.background = AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.item_amount_bg_selected
                )
            }
            Log.d("test", productsSelected[0].toString())
            val btnTotalPrice = globleView?.findViewById<Button>(R.id.btnTotalPrice)
            btnTotalPrice?.let {
                it.text = "Tổng $totalPrice"
                it.background =
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.total_price_selected
                    )
            }
            globleView?.findViewById<ImageButton>(R.id.btnReset)?.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.oval_button)
        }
    }
}