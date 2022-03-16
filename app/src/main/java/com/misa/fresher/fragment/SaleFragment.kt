package com.misa.fresher.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapter.ProductApdapter
import com.misa.fresher.model.Product
import com.misa.fresher.model.SelectedProduct
import java.text.DecimalFormat

class SaleFragment : Fragment() {
    var products: ArrayList<Product> = Product.fakeData()
    var rcv: RecyclerView? = null
    var productList = mutableListOf<SelectedProduct>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(view)
        setUpNavigationView(view)
        searchProduct(view)
        clearProduct(view)
        openDrawerLayoutMenu(view)
        setUpNavigation()
        setUpFilter(view)
        openBillFragment(view)
        updateView()
        cleanFilter(view)
    }
    /**
     *Set up hiển thị Recycleview
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun setUpRecyclerView(view: View) {
        rcv = view.findViewById(R.id.rcvProduct)
        val adapter = ProductApdapter(products) { showBottomDialog(it) }
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }
    /**
     *Hiển thị BottomDialog để chọn số lượng của sản phẩm
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun showBottomDialog(product: Product) {
        val bottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        val bottomSheetView: View =
            LayoutInflater.from(requireContext())
                .inflate(R.layout.layout_bottom_sheet, view as DrawerLayout, false)
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.findViewById<TextView>(R.id.tvName)!!.text = product.productName
        bottomSheetDialog.findViewById<TextView>(R.id.tvSKU)!!.text = product.productSKU
        bottomSheetDialog.show()
        val tvAmount =
            bottomSheetView.findViewById<TextView>(R.id.tvAmount)
        val btnAdd = bottomSheetDialog.findViewById<ImageButton>(R.id.btnAdd)
        val btnSubtract = bottomSheetView.findViewById<ImageButton>(R.id.btnSubtract)
        var amount = tvAmount.text.toString().toInt()
        btnAdd?.setOnClickListener {
            amount += 1
            tvAmount.text = amount.toString()
        }
        btnSubtract.setOnClickListener {
            if (amount > 1) {
                amount -= 1
                tvAmount.text = amount.toString()
            } else {
                val toast = Toast.makeText(
                    requireContext(),
                    "Số lượng không được ít hơn 0.Hãy kiểm tra lại",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }
        }
        bottomSheetDialog.setOnDismissListener {
            if (checkSelectedProduct(product.productId)) {
                for (i in productList) {
                    if (i.product.productId == product.productId) {
                        i.amount = i.amount + amount
                    }
                }
            } else {
                productList.add(SelectedProduct(product, amount))
            }
            updateView()
        }
    }
   /**
    *Tìm kiếm sản phẩm khi nhập từ khóa ở toolbar
    *@author:NCPhuc
    *@date:3/16/2022
    **/
    private fun searchProduct(view: View) {
        val txtSearch = view.findViewById<EditText>(R.id.txt_search)
        txtSearch?.doAfterTextChanged {
            updateList(txtSearch.text.toString())
        }
    }
    /**
     *Cập nhật lại danh sách sản phẩm khi tìm kiếm
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun updateList(string: String) {
        val productSearch = mutableListOf<Product>()
        for (i in products) {
            if (i.productName.contains(string) || i.productSKU.contains(string)) {
                productSearch.add(i)
            }
        }
        rcv?.adapter = ProductApdapter(productSearch as ArrayList<Product>) { showBottomDialog(it) }
    }
    /**
     *Thiết lập cho màn hình filter xuất hiện
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    @SuppressLint("WrongConstant")
    private fun setUpNavigationView(view: View) {
        val drawerLayout = view.findViewById<DrawerLayout>(R.id.drawer_layout)
        val ibFilter = view.findViewById<ImageButton>(R.id.imb_filter)
        ibFilter?.setOnClickListener {
            drawerLayout?.openDrawer(GravityCompat.END)
            drawerLayout?.setScrimColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.transparent
                )
            )
        }
    }
    /**
     *Xóa hết các sản phẩm có trong danh sách chọn mua
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun clearProduct(view: View) {
        val ivRefresh = view.findViewById<ImageView>(R.id.ivRefresh)
        val tvAmount = view.findViewById<TextView>(R.id.tvProductAmount)
        val tvTotalPrice = view.findViewById<TextView>(R.id.tvTotalPrice)
        val llRefresh = view.findViewById<LinearLayout>(R.id.llRefresh)
        ivRefresh?.setOnClickListener {
            tvAmount.let {
                it?.text = "0"
                it?.setTextColor(Color.BLACK)
                it?.setBackgroundResource(R.drawable.border_left_corner)
            }
            tvTotalPrice.let {
                it?.text = ""
                it?.setTextColor(Color.BLACK)
                it?.setBackgroundResource(R.drawable.border_right_corner)
            }
            llRefresh?.setBackgroundResource(R.drawable.border_button)
            ivRefresh.setBackgroundResource(R.drawable.border_button)
            productList.clear()
        }
    }
    /**
     *Cập nhật lại UI khi chọn sản phẩm ở bottomDialog
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun updateView() {
        val tvAmount = view?.findViewById<TextView>(R.id.tvProductAmount)
        val tvTotalPrice = view?.findViewById<TextView>(R.id.tvTotalPrice)
        val ivRefresh = view?.findViewById<ImageView>(R.id.ivRefresh)
        val llRefresh = view?.findViewById<LinearLayout>(R.id.llRefresh)
        val decimalFormat = DecimalFormat("0,000.0")
        if (productList.size > 0) {
            tvAmount.let {
                it?.text = productList.sumOf { it.amount }.toString()
                it?.setTextColor(Color.WHITE)
                it?.setBackgroundResource(R.drawable.textview_amount_border)
            }
            tvTotalPrice.let {
                it?.text =
                    context?.getText(R.string.all).toString() +" "+ decimalFormat.format(productList.sumOf { it.amount * it.product.productPrice })
                        .toString()
                it?.setTextColor(Color.WHITE)
                it?.setBackgroundResource(R.drawable.textview_totalprice_border)
            }
            ivRefresh.let {
                it?.setBackgroundResource(R.drawable.linearlayout_refresh_border)
            }
            llRefresh?.setBackgroundResource(R.drawable.linearlayout_refresh_border)
        }
    }
    /**
     *Mở navigationView ở phía bên trái của màn hình
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun openDrawerLayoutMenu(view: View) {
        val ibMenu = view.findViewById<ImageButton>(R.id.ibMenu)
        ibMenu?.setOnClickListener {
            (activity as MainActivity).openDrawerLayout()
        }
    }
    /**
     *Thiết lập điều hướng khi chọn các item của NavigationView
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun setUpNavigation() {
        val navigationView = (activity as MainActivity).findViewById<NavigationView>(R.id.nvMenu)
        val drawerLayout = (activity as MainActivity).findViewById<DrawerLayout>(R.id.dlLeft)
        navigationView?.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mnBill -> {
                    findNavController().navigate(R.id.action_saleFragment_to_listBillsFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
    }

    private fun setUpFilter(view: View) {
        val scSortQuantity = view.findViewById<SwitchCompat>(R.id.scSortQuantity)
        scSortQuantity?.setOnClickListener {
            if (scSortQuantity.isChecked) {
                sortByQuantity()
            } else {
                setUpRecyclerView(view)
            }
        }
    }
    /**
     *Chuyển sang BillFragment
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun openBillFragment(view: View) {
        view.findViewById<TextView>(R.id.tvProductAmount)?.setOnClickListener {
            if (productList.size > 0) {
                findNavController().navigate(
                    R.id.action_saleFragment_to_billDetailFragment,
                    bundleOf("product" to productList)
                )
            }
        }
        view.findViewById<TextView>(R.id.tvTotalPrice)?.setOnClickListener {
            if (productList.size > 0) {
                findNavController().navigate(
                    R.id.action_saleFragment_to_billDetailFragment,
                    bundleOf("product" to productList)
                )
            }
        }
    }

    private fun sortByQuantity() {
        val productQuantity = mutableListOf<Product>()
        for (i in products) {
            if (i.quantity > 1) {
                productQuantity.add(i)
            }
        }
        rcv?.adapter =
            ProductApdapter(productQuantity as ArrayList<Product>) { showBottomDialog(it) }
    }

    private fun sortByName() {
        val rbName = view?.findViewById<RadioButton>(R.id.rbName)
        if (rbName?.isChecked==true) {
            products.sortBy { it.productName }

        } else {
            products.sortBy { it.productPrice }
            rcv?.adapter = ProductApdapter(products) { showBottomDialog(it) }
        }
    }

    private fun checkSelectedProduct(id: Int): Boolean {
        var isOK = false
        for (i in productList) {
            if (i.product.productId == id) {
                isOK = true
            }
        }
        return isOK
    }

    private fun cleanFilter(view: View) {
        val drawerLayout = view.findViewById<DrawerLayout>(R.id.drawer_layout)
        val btnClearn = view.findViewById<Button>(R.id.btnClearnFilter)
        val btnDone = view.findViewById<Button>(R.id.btnDone)
        val rbName = view.findViewById<RadioButton>(R.id.rbName)
        val rbPrice = view.findViewById<RadioButton>(R.id.rbPrice)
        btnClearn?.setOnClickListener {
        }
        btnDone?.setOnClickListener {
            drawerLayout?.closeDrawer(GravityCompat.END)
            sortByName()
        }
    }

}