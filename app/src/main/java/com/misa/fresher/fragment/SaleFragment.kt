package com.misa.fresher.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.R
import com.misa.fresher.adapter.SaleProductAdapter
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.DialogBottomSheetBinding
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.models.Product

class SaleFragment : BaseFragment<FragmentSaleBinding>(
    FragmentSaleBinding::inflate
) {
    private var _toolbar: Toolbar? = null
    private val toolbar get() = _toolbar!!

    private var _drawerLayout: DrawerLayout? = null
    private val drawerLayout get() = _drawerLayout!!

    private fun initToolbar() {
        _toolbar = binding.toolbar
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        Log.i("--SaleFragment--","---DONE--- initToolbar()")
    }

    private fun initDrawer() {
        _drawerLayout = binding.root
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END)
        Log.i("--SaleFragment--","---DONE--- initDrawer()")
    }

    private fun initClickListener() {
        binding.toggleDrawerBtn.setOnClickListener {
            drawerLayout.setScrimColor(ContextCompat.getColor(requireContext(), R.color.scrim_default))
            drawerLayout.openDrawer(GravityCompat.START)
            Log.i("--SaleFragment--","---OPEN--- drawer left")
        }

        binding.filterBtn.setOnClickListener {
            drawerLayout.setScrimColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
            drawerLayout.openDrawer(GravityCompat.END)
            Log.i("--SaleFragment--","---OPEN--- drawer right")
        }

        binding.priceBtn.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_sale_to_fragment_ship_info)
            Log.i("--SaleFragment--","---Navigate--- to ship info Fragment")
        }

        binding.numberBtn.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_sale_to_fragment_calculator)
            Log.i("--SaleFragment--","---Navigate--- to calculator Fragment")
        }
    }

    private fun initListProductRecView() {
        val products = fakeData()
        val listProductRecView = binding.listProductRecView
        listProductRecView.adapter = SaleProductAdapter(products) { productItemClick(it) }
        listProductRecView.layoutManager = LinearLayoutManager(activity)

        Log.i("--SaleFragment--","---DONE--- initListProductRecView()")
    }

    private fun fakeData(): ArrayList<Product> {
        val products: ArrayList<Product> = arrayListOf();
        for (i in 1..15) products.add(
            Product(
                "name $i",
                "code$i",
                1000.0 * i
            )
        )
        return products
    }

    private fun productItemClick(product: Product) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)

        val bottomSheetView: View = LayoutInflater.from(requireContext()).inflate(
            R.layout.dialog_bottom_sheet,
            DialogBottomSheetBinding.inflate(layoutInflater).bottomSheetDialog
        )

        bottomSheetView.findViewById<TextView>(R.id.bottom_sheet_text).text = product.name
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()

        Log.i("--SaleFragment--","---OPEN--- show bottom sheet")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initDrawer()
        initListProductRecView()
        initClickListener()

        Log.i("--SaleFragment--","---DONE--- onViewCreated()")
    }

//    override fun onBackPressed(): Boolean {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//            return true
//        }
//        return super.onBackPressed()
//    }
}