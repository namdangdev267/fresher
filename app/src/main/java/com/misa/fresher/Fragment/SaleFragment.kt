package com.misa.fresher.Fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Adapter.ProductAdapter
import com.misa.fresher.MainActivity
import com.misa.fresher.Product
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentSaleBinding

class SaleFragment : Fragment() {
    lateinit var binding: FragmentSaleBinding
    private var mView: View? = null
    var createProductData = Product.createProductList(20)
    var rcvProduct: RecyclerView? = null
    var billList = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mView = view

        configureToolbar()
        configureDrawerFilter()
        configureRecyclerView()
        onClickEvent()
        searchEvent()
        billListEvent()
    }

    private fun configureToolbar() {
//        binding?.toolbarSale?.inflateMenu(R.menu.menu_main)
//        binding?.toolbarSale?.btn_nav?.setImageDrawable(resources.getDrawableById(R.drawable.ic_menu))
        binding.toolbarSale.root.inflateMenu(R.menu.menu_main)
//        binding.toolbarSale.btnNav.setImageDrawable(resources.get)
        binding.toolbarSale.btnNav.setOnClickListener {
            (activity as MainActivity).toggleDrawer()
        }
        binding.toolbarSale.root.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.btnFilter -> {
                    toggleDrawer(binding.navigationView)
                }
            }
            true
        }

    }

    private fun toggleDrawer(view: View) {
        if (binding!!.drawerLayout.isDrawerOpen(view)) {
            binding!!.drawerLayout.closeDrawer(view)
        } else {
            binding!!.drawerLayout.openDrawer(view)
        }
    }

    private fun configureDrawerFilter() {
        binding.root.setScrimColor(Color.TRANSPARENT)
        binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.navigationView)
    }

    private fun billListEvent() {
        mView?.findViewById<RelativeLayout>(R.id.btnRefresh)?.setOnClickListener {
            billList.clear()

            it.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.custom_button_none)

            mView?.findViewById<TextView>(R.id.tvCountProduct)?.text = "0"
            mView?.findViewById<TextView>(R.id.tvBillProduct)?.text = "Not yet selected item"

            mView?.findViewById<LinearLayout>(R.id.linearQuantity)!!.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.custom_background_none)
        }
    }

    private fun searchEvent() {
        val edtSearch = mView?.findViewById<EditText>(R.id.edtSearch)
        edtSearch?.doAfterTextChanged {
            updateItemList(edtSearch.text.toString())
        }
    }

    private fun updateItemList(string: String) {
        var listProduct = mutableListOf<Product>()

        for (i in createProductData) {
            if (i.nameProduct.contains(string) || i.codeProduct.contains(string)) {
                listProduct.add(i)
            }
        }

        rcvProduct?.adapter = ProductAdapter(listProduct, { clickItemProduct(it) })
        (rcvProduct?.adapter)?.notifyDataSetChanged()
    }

    private fun onClickEvent() {
        mView?.findViewById<LinearLayout>(R.id.linearQuantity)?.setOnClickListener {
            if (billList.size != 0) {
                findNavController().navigate(R.id.action_fragment_sale_to_fragment_package)
            }
        }
    }

    private fun configureRecyclerView() {
        val setAdapter = ProductAdapter(createProductData, { clickItemProduct(it) })
        setAdapter.notifyDataSetChanged()
        rcvProduct = mView?.findViewById(R.id.rcvProduct)
        rcvProduct?.adapter = setAdapter
        rcvProduct?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun clickItemProduct(product: Product) {
        billList.add(product)

//        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
//        val bottomSheetView: View = LayoutInflater.from(requireContext()).inflate(
//            R.layout.bottom_sheet_product,
//            mView as LinearLayout, false
//        )
//        bottomSheetView.findViewById<TextView>(R.id.bottom_sheet_text).text = product.nameProduct
//        bottomSheetDialog.setContentView(bottomSheetView)
//        bottomSheetDialog.show()

        updateItemSeleted()
    }

    private fun updateItemSeleted() {
        var totalPrice = 0.0

        mView?.findViewById<TextView>(R.id.tvCountProduct)?.text = "${billList.size}"
        mView?.findViewById<TextView>(R.id.tvCountProduct)?.setTextColor(Color.WHITE)

        for (i in billList) {
            totalPrice += i.priceProduct
        }

        mView?.findViewById<TextView>(R.id.tvBillProduct)?.text = "Total $totalPrice"
        mView?.findViewById<TextView>(R.id.tvBillProduct)?.setTextColor(Color.WHITE)

        mView?.findViewById<LinearLayout>(R.id.linearQuantity)!!.background =
            AppCompatResources.getDrawable(requireContext(), R.drawable.custom_background)

        if (billList.size != null) {
            mView?.findViewById<RelativeLayout>(R.id.btnRefresh)?.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.custom_button)
        }
    }

}