package kma.longhoang.beta.fragment.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.component1
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
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
import kma.longhoang.beta.MainActivity
import kma.longhoang.beta.R
import kma.longhoang.beta.SaleViewModel
import kma.longhoang.beta.ShowNote
import kma.longhoang.beta.adapter.ProductAdapter
import kma.longhoang.beta.model.FilterProduct
import kma.longhoang.beta.model.OrderModel
import kma.longhoang.beta.model.ProductModel
import kotlin.math.absoluteValue

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        navMenu()
        setupFilterSpinner()
        navFilter()
        customerInfo(view)
        setupRecyclerView()
        searchItem()
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
            updateItemList("")
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
        saleViewModel.customer.observe(viewLifecycleOwner, Observer {
            tvCustomer?.text = StringBuilder(it.name).append(" (").append(it.phone).append(")")
        })
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
        listProduct.addAll(
            listOf<ProductModel>(
                ProductModel(
                    "Áo len nam",
                    "AL01",
                    175000F,
                    FilterProduct.Style.TSHIRT,
                ),
                ProductModel(
                    "Áo thun cotton nam",
                    "AT01",
                    175000F,
                    FilterProduct.Style.TSHIRT,
                    FilterProduct.Color.BLACK
                ),
                ProductModel(
                    "Áo len nữ",
                    "AL02",
                    175000F,
                    FilterProduct.Style.TSHIRT,
                ),
                ProductModel(
                    "Áo polo",
                    "ACT01",
                    135000F,
                    FilterProduct.Style.TSHIRT,
                    FilterProduct.Color.WHITE
                ),
                ProductModel(
                    "Váy ngắn",
                    "VN01",
                    120000F,
                    FilterProduct.Style.SHORTDRESS,
                    FilterProduct.Color.RED
                ),
                ProductModel(
                    "Váy xòe",
                    "VN02",
                    146000F,
                    FilterProduct.Style.SHORTDRESS,
                    FilterProduct.Color.BLUE
                ),
                ProductModel(
                    "Áo Somi nam",
                    "ACT01",
                    132000F,
                    FilterProduct.Style.TSHIRT,
                    FilterProduct.Color.WHITE
                ),
                ProductModel(
                    "Áo Sơ mi nữ",
                    "ACT02",
                    120000F,
                    FilterProduct.Style.TSHIRT,
                    FilterProduct.Color.WHITE
                ),
                ProductModel(
                    "Áo thun cotton nữ",
                    "AT02",
                    176000F,
                    FilterProduct.Style.TSHIRT,
                    FilterProduct.Color.BLUE
                ),
                ProductModel(
                    "Áo sơ mi cách điệu",
                    "ACT03",
                    200000F,
                    FilterProduct.Style.TSHIRT,
                    FilterProduct.Color.RED
                ),
                ProductModel(
                    "Quần Jean Nam",
                    "QJ01",
                    230000F,
                    FilterProduct.Style.JEAN,
                    FilterProduct.Color.BLUE
                ),
                ProductModel(
                    "Quần Jean Nữ",
                    "QJ02",
                    270000F,
                    FilterProduct.Style.JEAN,
                    FilterProduct.Color.BLACK
                ),
                ProductModel(
                    "Quần Jean nam rách",
                    "QJ03",
                    340000F,
                    FilterProduct.Style.JEAN,
                    FilterProduct.Color.WHITE
                ),
                ProductModel(
                    "Áo thun cotton",
                    "AT04",
                    200000F,
                    FilterProduct.Style.TSHIRT,
                    FilterProduct.Color.WHITE
                ),
                ProductModel(
                    "Áo thun cotton A2",
                    "AT05",
                    150000F,
                    FilterProduct.Style.TSHIRT,
                    FilterProduct.Color.BLACK
                ),
                ProductModel(
                    "Quần nam",
                    "Q01",
                    220000F,
                    FilterProduct.Style.SHORT,
                    FilterProduct.Color.BLUE
                ),
                ProductModel(
                    "Quần short nam",
                    "Q02",
                    250000F,
                    FilterProduct.Style.SHORT,
                    FilterProduct.Color.BLACK
                ),
                ProductModel(
                    "Áo thun",
                    "AT05",
                    140000F,
                    FilterProduct.Style.TSHIRT,
                    FilterProduct.Color.RED
                ),
            )
        )
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
            }
            tvProductName?.text = product.name
            tvProductCode?.text = product.code
            var amountProduct: Int = 1
            tvAmount?.text = amountProduct.toString()
            btnMinus?.setOnClickListener {
                if (amountProduct == 1) {
                    ShowNote().toast(it.context, "Số lượng phải lớn hơn 0. Hãy kiểm tra lại")
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
    private fun searchItem() {
        edtSearch?.doAfterTextChanged {
            updateItemList(edtSearch?.text.toString())
        }
    }

    /*
    * hàm tìm kiếm
    */
    @SuppressLint("NotifyDataSetChanged")
    private fun updateItemList(keySearch: String) {
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
    private fun updateItemList(
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
        updateItemList(filterStyle, filterColor)
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
        saleViewModel.listOrder.observe(viewLifecycleOwner, Observer { list ->
            orderList = list
            if (orderList.isNotEmpty()) {
                val amount = orderList.sumOf { it.amount }
                val total = orderList.map { it.price * it.amount }.sum()
                tvOrderAmount?.text = amount.toString()
                btnTotal?.text = total.toString()
                changeButtonState()
            } else {
                tvCustomer?.text = ""
                tvCustomer?.hint = getString(R.string.customer_name)
            }
        })
    }

}