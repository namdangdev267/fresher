package com.misa.fresher.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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

    private var _actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private val actionBarDrawerToggle get() = _actionBarDrawerToggle!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _toolbar = binding.toolbar
        _drawerLayout = binding.drawerLayout
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        _actionBarDrawerToggle = ActionBarDrawerToggle(
            activity,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        actionBarDrawerToggle.syncState()
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        binding.priceBtn.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_sale_to_fragment_ship_info)
        }
        binding.numberBtn.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_sale_to_fragment_calculator)
        }
        fakeProductData()
    }

    override fun onBackPressed(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        return super.onBackPressed()
    }

    private fun fakeProductData() {
        val listProductRecView = binding.listProductRecView

        val products: ArrayList<Product> = arrayListOf();
        for (i in 1..15) products.add(Product("name $i", "code$i", 1000.0 * i))

        listProductRecView.adapter = SaleProductAdapter(products) { productItemClick(it) }
        listProductRecView.layoutManager = LinearLayoutManager(activity)
    }

    private fun productItemClick(product: Product) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
        val bottomSheetView: View = LayoutInflater.from(requireContext()).inflate(
            R.layout.dialog_bottom_sheet,
            DialogBottomSheetBinding.inflate(layoutInflater).bottomSheetDialog
        )

        bottomSheetView.findViewById<TextView>(R.id.bottom_sheet_text).text = product.name
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }
}