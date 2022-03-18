package kma.longhoang.beta.fragment.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
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
import kma.longhoang.beta.MainActivity
import kma.longhoang.beta.R
import kma.longhoang.beta.SaleViewModel
import kma.longhoang.beta.ShowNote
import kma.longhoang.beta.adapter.ProductAdapter
import kma.longhoang.beta.model.FilterProduct
import kma.longhoang.beta.model.OrderModel
import kma.longhoang.beta.model.ProductModel

/*
* @author: Hoàng Gia Long
* Date: 3/12/2022
*/
class SaleFragment : Fragment(), AdapterView.OnItemSelectedListener {
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
    private var conditionStyle: String = ""
    private val saleViewModel: SaleViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        navMenu()
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

    private fun navFilter() {
        view?.findViewById<ImageButton>(R.id.button_filter)?.setOnClickListener {
            drawerSale?.setScrimColor(TRANSPARENT)
            drawerSale?.openDrawer(GravityCompat.END)
            filterProduct()
        }
    }

    //lọc sp
    private fun filterProduct() {
        val spinnerStyle = navFilter?.findViewById<Spinner>(R.id.filter_spinner_style)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filter_style,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )
        spinnerStyle?.adapter = adapter
        spinnerStyle?.onItemSelectedListener = this
        val radioGroup = navFilter?.findViewById<RadioGroup>(R.id.filter_radio_group)
        val radioButton = radioGroup?.checkedRadioButtonId?.let {
            navFilter?.findViewById<RadioButton>(
                it
            )
        }
        val btnDone = navFilter?.findViewById<Button>(R.id.button_done)
        val btnCancelFilter = navFilter?.findViewById<Button>(R.id.button_cancel_filter)
        btnCancelFilter?.setOnClickListener {
            updateItemList("")
            conditionStyle = ""
            drawerSale?.closeDrawer(GravityCompat.END)
        }
        btnDone?.setOnClickListener {
            drawerSale?.closeDrawer(GravityCompat.END)
            filterByProductStyle(conditionStyle)
        }
    }

    //itemSelect của spinner filter
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        conditionStyle = p0?.getItemAtPosition(p2).toString()
    }

    //itemClick của spinner filter
    override fun onNothingSelected(p0: AdapterView<*>?) {
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
                    "Áo thun cotton nam",
                    "AT01",
                    175000F,
                    FilterProduct.Style.TSHIRT,
                    FilterProduct.Color.BLACK
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
        val selectAmountDialog = BottomSheetDialog(requireContext())
        selectAmountDialog.setContentView(
            LayoutInflater.from(context).inflate(R.layout.dialog_select_sale_item, null)
        )
        val tvProductName = selectAmountDialog.findViewById<TextView>(R.id.tv_product_name)
        val tvProductCode = selectAmountDialog.findViewById<TextView>(R.id.tv_product_code)
        val tvAmount = selectAmountDialog.findViewById<TextView>(R.id.text_amount)
        val btnPlus = selectAmountDialog.findViewById<ImageButton>(R.id.button_plus)
        val btnMinus = selectAmountDialog.findViewById<ImageButton>(R.id.button_minus)
        val btnSave = selectAmountDialog.findViewById<Button>(R.id.button_save)
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
        btnSave?.setOnClickListener {
            val orderModel = OrderModel(
                product.name,
                product.code,
                product.price,
                amountProduct,
            )
            orderList.add(orderModel)
            var totalPrice = 0.0
            var totalAmount = 0
            for (i in 0 until orderList.size) {
                totalAmount += orderList[i].amount
                totalPrice += (orderList[i].amount * orderList[i].price)
            }
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
            if (product.name.lowercase().contains(keySearch) || product.code
                    .lowercase().contains(keySearch)
            ) {
                list.add(product)
            }
        }
        recyclerView?.adapter = ProductAdapter(list) { onItemClick(it) }
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateItemList(style: FilterProduct.Style) {
        val listProductSearch = listProduct
        val list = mutableListOf<ProductModel>()
        for (product in listProductSearch) {
            if (product.style == style) {
                list.add(product)
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
                AppCompatResources.getDrawable(requireContext(), R.drawable.rectangle_button_gray)
            tvOrderAmount?.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.rectangle_button_gray)
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
    private fun filterByProductStyle(style: String) {
        when (style) {
            "T-Shirt" -> updateItemList(FilterProduct.Style.TSHIRT)
            "Quần Short" -> updateItemList(FilterProduct.Style.SHORT)
            "Quần Jean" -> updateItemList(FilterProduct.Style.JEAN)
            "Váy ngắn" -> updateItemList(FilterProduct.Style.SHORTDRESS)
            "Váy dài" -> updateItemList(FilterProduct.Style.LONGDRESS)
            else ->
                updateItemList("")
        }
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