package com.misa.fresher.fragments

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapters.ProductAdapter
import com.misa.fresher.adapters.ProductsAdapter
import com.misa.fresher.data.FakeData
import com.misa.fresher.model.Product
import com.misa.fresher.model.Products
import com.misa.fresher.model.Products.Companion.createProductsList

class SaleFragment : Fragment() {

    private var globleView: View? = null
    var rcv: RecyclerView? = null
    var listItems = mutableListOf<Products>()
    var bottomSheetView: View? = null
    var amount = 1
    private var fakedata = createProductsList(20)

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

    @SuppressLint("RtlHardcoded")
    private fun configFilterDrawer() {
        val mDrawer = globleView?.findViewById<DrawerLayout>(R.id.dlSaleFilter)
        mDrawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        globleView?.findViewById<ImageButton>(R.id.btnFilter)?.setOnClickListener {
            mDrawer?.openDrawer(Gravity.RIGHT)
        }
        val btnSave = globleView?.findViewById<Button>(R.id.btnFilterSave)
        btnSave?.setOnClickListener {
            mDrawer?.closeDrawer(Gravity.RIGHT)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun resetEvent() {
        globleView?.findViewById<ImageButton>(R.id.btnReset)?.setOnClickListener {
            listItems.clear()
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
            if (listItems.size > 0) {
                findNavController().navigate(R.id.action_nav_sale_to_nav_shipInfor)
            }
        }
        globleView?.findViewById<Button>(R.id.btnItemCount)?.setOnClickListener {
            if (listItems.size > 0) {
                findNavController().navigate(R.id.action_nav_sale_to_nav_shipInfor)
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
            for (i in 1..amount) {
                listItems.add(products)
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
        var price = 0.0
        if (listItems.size > 0) {
            btnItemCount?.let {
                it.text = "${listItems.size}"
                it.background = AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.item_amount_bg_selected
                )
            }
            val btnTotalPrice = globleView?.findViewById<Button>(R.id.btnTotalPrice)
            for (i in listItems) {
                price += i.price
            }
            btnTotalPrice?.let {
                it.text = "Tổng $price"
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