package kma.longhoang.beta.fragment

import android.annotation.SuppressLint
import android.graphics.Color.TRANSPARENT
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.GravityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import kma.longhoang.beta.login.MainActivity
import kma.longhoang.beta.R
import kma.longhoang.beta.SaleViewModel
import kma.longhoang.beta.adapter.ProductAdapter
import kma.longhoang.beta.dao.ProductDAO
import kma.longhoang.beta.database.AppDatabase
import kma.longhoang.beta.model.FilterProduct
import kma.longhoang.beta.model.OrderModel
import kma.longhoang.beta.model.ProductModel
import kma.longhoang.beta.showNote

/*
* @author: Hoàng Gia Long
* Date: 3/12/2022
*/
class SaleFragment : Fragment() {
    private var listProduct = mutableListOf<ProductModel>()
    private var recyclerView: RecyclerView? = null
    private var tvOrderAmount: TextView? = null
    private var btnTotal: Button? = null
    private var btnReset: ImageButton? = null
    private var orderList = mutableListOf<OrderModel>()
    private var edtSearch: EditText? = null
    private var navFilter: NavigationView? = null
    private var drawerSale: DrawerLayout? = null
    private var tvCustomer: TextView? = null
    private var selectedStyle: String = ""
    private var selectedColor: String = ""
    private val saleViewModel: SaleViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productDAO = ProductDAO.getInstance(AppDatabase.getInstance(requireContext()))
        if (productDAO != null) {
            listProduct = productDAO.getAllProduct()
        }
        saleViewModel.listOrder.observe(viewLifecycleOwner, Observer {
            orderList = it
        })
        initView(view)
        navMenu()
        setupFilterSpinner()
        navFilter()
        customerInfo(view)
        setupRecyclerView()
        searchProduct()
        resetOrder()
        moveToOrderDetail(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }


    private fun initView(view: View) {
        btnTotal = view.findViewById(R.id.button_total)
        btnReset = view.findViewById(R.id.button_reset)
        tvOrderAmount = view.findViewById(R.id.text_amount)
        edtSearch = view.findViewById(R.id.edt_search_sp)
        navFilter = view.findViewById(R.id.nav_filter)
        drawerSale = view.findViewById(R.id.drawer_sale)
        tvCustomer = view.findViewById(R.id.text_customer)
        recyclerView = view.findViewById(R.id.recyclerView_Sale)
    }


    // setup navigation drawer + toolbar
    private fun navMenu() {
        view?.findViewById<ImageButton>(R.id.button_menu)?.setOnClickListener {
            (activity as MainActivity).drawerView()
        }

    }

    // Filter
    private fun navFilter() {
        view?.findViewById<ImageButton>(R.id.button_filter)?.setOnClickListener {
            drawerSale?.setScrimColor(TRANSPARENT)
            drawerSale?.openDrawer(GravityCompat.END)
            filterProduct()
        }
    }

    private fun setupFilterSpinner() {
        val spinnerStyle = navFilter?.findViewById<Spinner>(R.id.filter_spinner_style)
        val spinnerColor = navFilter?.findViewById<Spinner>(R.id.filter_spinner_color)
        val adapterStyle = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filter_style,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )
        val adapterColor = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filter_color,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )
        spinnerStyle?.adapter = adapterStyle
        spinnerColor?.adapter = adapterColor
        spinnerStyle?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedStyle = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedStyle = ""
            }
        }
        spinnerColor?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedColor = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedColor = ""
            }
        }
    }

    //lọc sp
    private fun filterProduct() {
        val btnDone = navFilter?.findViewById<Button>(R.id.button_done)
        val btnCancelFilter = navFilter?.findViewById<Button>(R.id.button_cancel_filter)
        btnCancelFilter?.setOnClickListener {
            updateProductList("")
            selectedColor = ""
            selectedStyle = ""
            setupFilterSpinner()
            drawerSale?.closeDrawer(GravityCompat.END)
        }
        btnDone?.setOnClickListener {
            drawerSale?.closeDrawer(GravityCompat.END)
            filterByProductStyle(selectedStyle, selectedColor)
        }
    }

    private fun customerInfo(view: View) {
        val customer = saleViewModel.customer.value
        if (customer != null) {
            tvCustomer?.text =
                StringBuilder(customer.name).append(" (").append(customer.phone).append(")")
        } else {
            tvCustomer?.text = ""
        }
        tvCustomer?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_saleFragment_to_customerListFragment)
        }
        view.findViewById<ImageView>(R.id.image_customer)?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_saleFragment_to_customerListFragment)
        }
    }


    // setup recyclerView
    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView_Sale)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView?.adapter = ProductAdapter(listProduct) { onItemClick(it) }
        recyclerView?.adapter?.notifyDataSetChanged()
    }


    //set sự kiện khi click item trong recyclerView
    private fun onItemClick(product: ProductModel) {
        showOptionDialog(product)
    }


    // hiển thị bottomSheet Dialog chọn số lượng
    private fun showOptionDialog(product: ProductModel) {
        if (product.color != null) {
            val selectAmountDialog = BottomSheetDialog(requireContext())
            selectAmountDialog.setContentView(
                LayoutInflater.from(context).inflate(R.layout.dialog_select_sale_item, null)
            )
            val tvProductName = selectAmountDialog.findViewById<TextView>(R.id.tv_product_name)
            val tvProductCode = selectAmountDialog.findViewById<TextView>(R.id.tv_product_code)
            val tvAmount = selectAmountDialog.findViewById<TextView>(R.id.text_amount)
            val btnPlus = selectAmountDialog.findViewById<ImageButton>(R.id.button_plus)
            val btnMinus = selectAmountDialog.findViewById<ImageButton>(R.id.button_minus)
            val btnColor = selectAmountDialog.findViewById<TextView>(R.id.select_color)
//        val btnColorBlue = selectAmountDialog.findViewById<Button>(R.id.select_color_blue)
//        val btnColorBlack = selectAmountDialog.findViewById<Button>(R.id.select_color_black)
//        val btnColorWhite = selectAmountDialog.findViewById<Button>(R.id.select_color_white)
            when (product.color) {
                FilterProduct.Color.RED -> btnColor?.text = "Đỏ"
                FilterProduct.Color.BLUE -> btnColor?.text = "Xanh"
                FilterProduct.Color.BLACK -> btnColor?.text = "Đen"
                FilterProduct.Color.WHITE -> btnColor?.text = "Trắng"
                else -> {}
            }
            tvProductName?.text = product.name
            tvProductCode?.text = product.code
            var amountProduct: Int = 1
            tvAmount?.text = amountProduct.toString()
            btnMinus?.setOnClickListener {
                if (amountProduct == 1) {
                    showNote(it.context, "Số lượng phải lớn hơn 0. Hãy kiểm tra lại")
                } else {
                    amountProduct -= 1
                    tvAmount?.text = amountProduct.toString()
                }
            }
            btnPlus?.setOnClickListener {
                amountProduct += 1
                tvAmount?.text = amountProduct.toString()
            }
            btnColor?.setOnClickListener {
                val orderModel = OrderModel(
                    product.name,
                    product.code,
                    product.color,
                    product.price,
                    amountProduct,
                )
                orderList.add(orderModel)
                saleViewModel.setListOrder(orderList)
                val totalPrice = orderList.map { it.amount * it.price }.sum()
                val totalAmount = orderList.sumOf { it.amount }
                tvOrderAmount?.text = totalAmount.toString()
                btnTotal?.text = StringBuilder("Tổng ").append(totalPrice.toString())
                changeButtonState()
                selectAmountDialog.dismiss()
            }
            selectAmountDialog.setCanceledOnTouchOutside(true)
            selectAmountDialog.setOnCancelListener {
                if (tvOrderAmount?.text == "0") {
                    resetOrder()
                } else {
                    selectAmountDialog.dismiss()
                }
            }
            selectAmountDialog.show()
        } else {
            val orderModel = OrderModel(
                product.name,
                product.code,
                null,
                product.price,
                1,
            )
            orderList.add(orderModel)
            orderList.let { saleViewModel.setListOrder(it) }
            val totalPrice = orderList.map { it.amount * it.price }.sum()
            val totalAmount = orderList.sumOf { it.amount }
            tvOrderAmount?.text = totalAmount.toString()
            btnTotal?.text = StringBuilder("Tổng ").append(totalPrice.toString())
            changeButtonState()
        }
    }

    // đổi trạng thái thanh tổng giá
    @SuppressLint("ResourceAsColor")
    private fun changeButtonState() {
        btnReset?.background =
            AppCompatResources.getDrawable(requireContext(), R.drawable.oval_button_violet)
        btnTotal?.background =
            AppCompatResources.getDrawable(requireContext(), R.drawable.rectangle_button_violet)
        btnTotal?.setTextColor(
            AppCompatResources.getColorStateList(
                requireContext(),
                R.color.white
            )
        )
        tvOrderAmount?.background =
            AppCompatResources.getDrawable(requireContext(), R.drawable.rectangle_button_violet)
        tvOrderAmount?.setTextColor(
            AppCompatResources.getColorStateList(
                requireContext(),
                R.color.white
            )
        )
    }


    /*
    * tìm kiếm item trong recyclerView/ SaleFragment
    */
    private fun searchProduct() {
        edtSearch?.doAfterTextChanged {
            updateProductList(edtSearch?.text.toString())
        }
    }

    /*
    * hàm tìm kiếm
    */
    @SuppressLint("NotifyDataSetChanged")
    private fun updateProductList(keySearch: String) {
        val listProductSearch = listProduct
        val list = mutableListOf<ProductModel>()
        for (product in listProductSearch) {
            if (product.name.lowercase().contains(keySearch.lowercase()) || product.code
                    .lowercase().contains(keySearch.lowercase())
            ) {
                list.add(product)
            }
        }
        recyclerView?.adapter = ProductAdapter(list) { onItemClick(it) }
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateProductList(
        style: FilterProduct.Style? = null,
        color: FilterProduct.Color? = null
    ) {
        val listProductSearch = listProduct
        val list = mutableListOf<ProductModel>()
        for (product in listProductSearch) {
            if (style != null && color != null) {
                if (product.style == style && product.color == color) {
                    list.add(product)
                }
            } else if (style != null) {
                if (product.style == style) {
                    list.add(product)
                }
            } else if (color != null) {
                if (product.color == color) {
                    list.add(product)
                }
            } else {
                list.addAll(listProductSearch)
            }
        }
        recyclerView?.adapter = ProductAdapter(list) { onItemClick(it) }
        recyclerView?.adapter?.notifyDataSetChanged()
    }


    @SuppressLint("ResourceAsColor")
    private fun resetOrder() {
        btnReset?.setOnClickListener {
            orderList = mutableListOf<OrderModel>()
            saleViewModel.setListOrder(orderList)
            btnReset?.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.oval_button_gray)
            btnTotal?.background =
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.rectangle_button_gray
                )
            tvOrderAmount?.background =
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.rectangle_button_gray
                )
            btnTotal?.text = getString(R.string.not_selected_yet)
            btnTotal?.setTextColor(
                AppCompatResources.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            )
            tvOrderAmount?.text = orderList.size.toString()
            tvOrderAmount?.setTextColor(
                AppCompatResources.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            )

        }
    }

    // lọc product
    private fun filterByProductStyle(style: String, color: String) {
        var filterStyle: FilterProduct.Style? = null
        var filterColor: FilterProduct.Color? = null
        filterStyle = when (style) {
            "T-Shirt" -> FilterProduct.Style.TSHIRT
            "Quần Short" -> FilterProduct.Style.SHORT
            "Quần Jean" -> FilterProduct.Style.JEAN
            "Váy ngắn" -> FilterProduct.Style.SHORTDRESS
            "Váy dài" -> FilterProduct.Style.LONGDRESS
            else ->
                null
        }
        filterColor = when (color) {
            "Đỏ" -> FilterProduct.Color.RED
            "Xanh dương" -> FilterProduct.Color.BLUE
            "Đen" -> FilterProduct.Color.BLACK
            "Trắng" -> FilterProduct.Color.WHITE
            else ->
                null
        }
        updateProductList(filterStyle, filterColor)
    }

    private fun moveToOrderDetail(view: View) {
        tvOrderAmount?.setOnClickListener {
            if (orderList.isNotEmpty()) {
                saleViewModel.setListOrder(orderList)
                Navigation.findNavController(view)
                    .navigate(R.id.action_saleFragment_to_orderDetailFragment)
            }
        }
        btnTotal?.setOnClickListener {
            if (orderList.isNotEmpty()) {
                saleViewModel.setListOrder(orderList)
                Navigation.findNavController(view)
                    .navigate(R.id.action_saleFragment_to_orderDetailFragment)
            }
        }
    }

    //show lại product đang chọn
    override fun onResume() {
        super.onResume()
        val orderList = saleViewModel.listOrder.value
        if (orderList != null) {
            if (orderList.isNotEmpty()) {
                val amount = orderList.sumOf { it.amount }
                val total = orderList.map { it.price * it.amount }.sum()
                tvOrderAmount?.text = amount.toString()
                btnTotal?.text = total.toString()
                changeButtonState()
            } else {
                this.orderList = orderList
            }
        }
//        saleViewModel.customer.observe(viewLifecycleOwner, Observer { customer->
//            val imgClearCustomer = view?.findViewById<ImageView>(R.id.image_clear)
//            if (customer != null) {
//                tvCustomer?.text =
//                    StringBuilder(customer.name).append(" (").append(customer.phone).append(")")
//                imgClearCustomer?.isVisible = true
//                imgClearCustomer?.setOnClickListener {
//                    imgClearCustomer.isVisible = false
//                    saleViewModel.setCustomer(null)
//                    tvCustomer?.text = ""
//                }
//            } else {
//                tvCustomer?.text = ""
//                imgClearCustomer?.isVisible = false
//            }
//        })
    }

}