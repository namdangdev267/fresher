package com.misa.fresher.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapters.ProductsAdapter
import com.misa.fresher.model.Product
import com.misa.fresher.model.Product.Companion.createProductsList

class SaleFragment : Fragment() {

    private var globleView: View? = null
    private var fakeData = createProductsList(20)
    var rcv: RecyclerView? = null
    var bill = mutableListOf<Product>()
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
        onClickEvent()
        searchEvent()
        billEvent()
    }

    private fun billEvent() {
        globleView?.findViewById<ImageButton>(R.id.btnReset)?.setOnClickListener {
            bill.clear()
            it.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.oval_button_base)
            val btnItemCount = globleView?.findViewById<Button>(R.id.btnItemCount)
            var price = 0.0
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
                    AppCompatResources.getDrawable(requireContext(), R.drawable.total_price_base)
            }
        }
    }

    private fun searchEvent() {
        val edSearch = globleView?.findViewById<EditText>(R.id.edSearch)
        edSearch?.doAfterTextChanged {
            updateList(edSearch.text.toString())
        }
    }

    private fun onClickEvent() {
        globleView?.findViewById<Button>(R.id.btnTotalPrice)?.setOnClickListener {
            findNavController().navigate(R.id.action_nav_sale_to_nav_shipInfor)
        }

    }

    private fun updateList(string: String) {
        var list = mutableListOf<Product>()
        for (i in fakeData) {
            if (i.name.contains(string)) {
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

    private fun configRecyclerView() {
        var adapter = ProductsAdapter(fakeData, { productItemClick(it) })
        adapter.notifyDataSetChanged()
        rcv = globleView?.findViewById(R.id.rcvListProduct)
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun productItemClick(product: Product) {
        bill.add(product)
//        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
//        val bottomSheetView: View = LayoutInflater.from(requireContext()).inflate(
//            R.layout.bottom_sheet_product,
//            globleView as LinearLayout, false
//        )
//        bottomSheetView.findViewById<TextView>(R.id.bottom_sheet_text).text = product.name
//        bottomSheetDialog.setContentView(bottomSheetView)
//        bottomSheetDialog.show()
        updateItemSelected()
    }

    @SuppressLint("ResourceAsColor")
    private fun updateItemSelected() {
        val btnItemCount = globleView?.findViewById<Button>(R.id.btnItemCount)
        var price = 0.0
        btnItemCount?.let {
            it.text = "${bill.size}"
            it.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.item_amount_bg_selected
            )

        }
        val btnTotalPrice = globleView?.findViewById<Button>(R.id.btnTotalPrice)
        for (i in bill) {
            price += i.price
        }
        btnTotalPrice?.let {
            it.text = "Tổng $price"
            it.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.total_price_selected)
        }
        if (bill.size != null) {
            globleView?.findViewById<ImageButton>(R.id.btnReset)?.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.oval_button)
        }
    }

}