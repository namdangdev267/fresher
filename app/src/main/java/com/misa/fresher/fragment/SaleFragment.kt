package com.misa.fresher.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapter.ProductApdapter
import com.misa.fresher.model.FilterProduct
import com.misa.fresher.model.Product
import com.misa.fresher.model.SelectedProduct
import com.misa.fresher.data.product.ImplProductDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.Collator
import java.text.DecimalFormat
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class SaleFragment : Fragment() {
    var products:ArrayList<Product>?=null
    var rcv: RecyclerView? = null
    var productList = arrayListOf<SelectedProduct>()
    var rcvAdapter: ProductApdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
        searchProduct(view)
        clearProduct(view)
        showBillFragment(view)
        updateView()
        configFilter(view)
    }

    /**
     *Lấy danh sách sản phẩm phẩm để hiện thị lên RecycleView
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun setUpView(view: View) {
        rcv = view.findViewById(R.id.rcvProduct)
        val iDAO = ImplProductDAO(requireContext())
        CoroutineScope(IO).launch {
            products = iDAO.selectAllProduct()
            withContext(Main)
            {
                rcvAdapter = ProductApdapter(products!!) { showBottomDialog(it) }
                rcv?.adapter = rcvAdapter
                rcv?.layoutManager = LinearLayoutManager(requireContext())
                setUpNavigation()
                openDrawerLayoutMenu(view)
            }
        }

    }

    /**
     *Thiết lập dữ liệu cho spinner
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun setUpSpinner(view: View) {
        val spnColor = view.findViewById<Spinner>(R.id.spnColor)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.color,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnColor?.adapter = adapter
        }
        val spnSize = view.findViewById<Spinner>(R.id.spnSize)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.size,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnSize?.adapter = adapter
        }
    }

    /**
     *Hiển thị BottomDialog để chọn số lượng của sản phẩm -> dismiss->thêm sản phẩm chọn vào danh sách
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
        val txtSearch = view.findViewById<EditText>(R.id.etSearch)
        txtSearch?.doAfterTextChanged {
            updateList(txtSearch.text.toString())
        }
    }

    /**
     *Cập nhật lại danh sách sản phẩm khi tìm kiếm
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun updateList(strSearch: String) {
        val productSearch = mutableListOf<Product>()
        for (i in products!!) {
            if (i.productName.lowercase().contains(strSearch.lowercase()) || i.productSKU.lowercase().contains(strSearch.lowercase())) {
                productSearch.add(i)
            }
        }
        rcvAdapter = ProductApdapter(productSearch as ArrayList<Product>) { showBottomDialog(it) }
        rcv?.adapter = rcvAdapter
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
    @SuppressLint("SetTextI18n")
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
                    context?.getText(R.string.all).toString() + " " + decimalFormat.format(
                        productList.sumOf { it.amount * it.product.productPrice })
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
                    findNavController().navigate(
                        R.id.action_saleFragment_to_listBillsFragment
                    )
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
    }

    /**
     *Chuyển sang BillFragment
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun showBillFragment(view: View) {
        val drawerLayout = (activity as MainActivity).findViewById<DrawerLayout>(R.id.dlLeft)
        view.findViewById<TextView>(R.id.tvProductAmount)?.setOnClickListener {
            drawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
            if (productList.size > 0) {
                findNavController().navigate(
                    R.id.action_saleFragment_to_billDetailFragment,
                    bundleOf("product" to productList)
                )
            }
        }
        view.findViewById<TextView>(R.id.tvTotalPrice)?.setOnClickListener {
            drawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
            if (productList.size > 0) {
                findNavController().navigate(
                    R.id.action_saleFragment_to_billDetailFragment,
                    bundleOf("product" to productList)
                )
            }
        }
    }

    /**
     *Kiểm tra xem sản phẩm chọn mới đã có sản phẩm tương tự trong danh sách hay chưa
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun checkSelectedProduct(id: Int): Boolean {
        var isOK = false
        for (i in productList) {
            if (i.product.productId == id) {
                isOK = true
            }
        }
        return isOK
    }

    /**
     *Thiết lập filter
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    @SuppressLint("RtlHardcoded")
    private fun configFilter(view: View) {
        setUpSpinner(view)
        val mDrawer = view.findViewById<DrawerLayout>(R.id.dlFilter)
        mDrawer.setScrimColor(Color.TRANSPARENT)
        mDrawer.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
        view.findViewById<ImageButton>(R.id.imbFilter)?.setOnClickListener {
            mDrawer.openDrawer(Gravity.RIGHT)
        }
        val btnSave = view.findViewById<Button>(R.id.btnDone)
        val btnClear = view.findViewById<Button>(R.id.btnClearnFilter)
        btnSave.setOnClickListener {
            filterProduct(getFilter(view))
            mDrawer.closeDrawer(Gravity.RIGHT)
        }
        btnClear.setOnClickListener {
            view.findViewById<Spinner>(R.id.spnColor)?.setSelection(0)
            view.findViewById<Spinner>(R.id.spnSize)?.setSelection(0)
            view.findViewById<RadioButton>(R.id.rbName)?.isChecked = true
        }
    }

    /**
     *Nhận các giá trị để thực hiện filter
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun getFilter(view: View): FilterProduct {
        val selectRadioButon = view.findViewById<RadioGroup>(R.id.rgSortby).checkedRadioButtonId
        val radioButtonText = selectRadioButon.let { view.findViewById<RadioButton>(it)?.text }
        val spColor = view.findViewById<Spinner>(R.id.spnColor).selectedItem.toString()
        val spSize = view.findViewById<Spinner>(R.id.spnSize).selectedItem.toString()
        return FilterProduct(radioButtonText.toString(), spColor, spSize)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterProduct(filter: FilterProduct) {
        var sortList = products
        if (filter.sortBy == "Tên") {
            if (filter.color == "All" && filter.size == "All") sortList?.sortWith { t, t2 ->
                Collator.getInstance(Locale("vi", "VN")).compare(t.productName, t2.productName)
            }
            else if (filter.color != "All" && filter.size == "All") sortList =
                sortList?.filter { it.color == filter.color } as ArrayList<Product>
            else if (filter.color == "All" && filter.size != "All") sortList =
                sortList?.filter { it.size == filter.size } as ArrayList<Product>
            else {
                sortList = sortList?.filter { it.color == filter.color } as ArrayList<Product>
                sortList = sortList.filter { it.size == filter.size } as ArrayList<Product>
            }
        } else {
            sortList?.sortWith(compareBy(Product::productPrice))
            if (filter.color == "All" && filter.size == "All") sortList?.sortWith(compareBy(Product::productPrice))
            else if (filter.color != "All" && filter.size == "All") sortList =
                sortList?.filter { it.color == filter.color } as ArrayList<Product>
            else if (filter.color == "All" && filter.size != "All") sortList =
                sortList?.filter { it.size == filter.size } as ArrayList<Product>
            else {
                sortList = sortList?.filter { it.color == filter.color } as ArrayList<Product>
                sortList = sortList.filter { it.size == filter.size } as ArrayList<Product>
            }
        }
        rcvAdapter?.items = sortList!!
        rcvAdapter?.notifyDataSetChanged()
    }

}