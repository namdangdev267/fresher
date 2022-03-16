package kma.longhoang.beta.fragment.main

import android.annotation.SuppressLint
import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.GravityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import kma.longhoang.beta.MainActivity
import kma.longhoang.beta.R
import kma.longhoang.beta.SharedViewModel
import kma.longhoang.beta.adapter.ProductAdapter
import kma.longhoang.beta.fragment.delivery.OrderDetailFragment
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
    private var btnMenu: ImageButton? = null
    private var tvOrderAmount: TextView? = null
    private var btnTotal: Button? = null
    private var btnReset: ImageButton? = null
    private var orderList = mutableListOf<OrderModel>()
    private var edtSearch: EditText? = null
    private var navFilter: NavigationView? = null
    private var drawerSale: DrawerLayout? = null
    private var btnFilter: ImageButton? = null
    private var conditionStyle: String = ""
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        navMenu()
        navFilter()
        setupRecyclerView()
        searchItem()
        resetOrder()
        moveToOrderDetail()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }


    private fun initView(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_Sale)
        btnTotal = view.findViewById(R.id.button_total)
        btnReset = view.findViewById(R.id.button_reset)
        tvOrderAmount = view.findViewById(R.id.text_amount)
        btnMenu = view.findViewById(R.id.button_menu)
        edtSearch = view.findViewById(R.id.edt_search_sp)
        btnFilter = view.findViewById(R.id.button_filter)
        navFilter = view.findViewById(R.id.nav_filter)
        drawerSale = view.findViewById(R.id.drawer_sale)
    }


    // setup navigation drawer + toolbar
    private fun navMenu() {
        btnMenu?.setOnClickListener {
            (activity as MainActivity).drawerView(R.id.nav_sale)
        }
    }

    private fun navFilter() {
        btnFilter?.setOnClickListener {
            drawerSale?.setScrimColor(TRANSPARENT)
            drawerSale?.openDrawer(GravityCompat.END)
            filterProduct()
        }
    }

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

    // setup recyclerView
    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        listProduct.addAll(
            listOf<ProductModel>(
                ProductModel("Áo thun cotton nam", "AT01", 175000F, FilterProduct.Style.TSHIRT, FilterProduct.Color.BLACK),
                ProductModel("Áo polo", "ACT01", 135000F, FilterProduct.Style.TSHIRT, FilterProduct.Color.WHITE),
                ProductModel("Váy ngắn", "VN01", 120000F, FilterProduct.Style.SHORTDRESS, FilterProduct.Color.RED),
                ProductModel("Váy xòe", "VN02", 146000F, FilterProduct.Style.SHORTDRESS, FilterProduct.Color.BLUE),
                ProductModel("Áo Somi nam", "ACT01", 132000F, FilterProduct.Style.TSHIRT, FilterProduct.Color.WHITE),
                ProductModel("Áo Sơ mi nữ", "ACT02", 120000F, FilterProduct.Style.TSHIRT, FilterProduct.Color.WHITE),
                ProductModel("Áo thun cotton nữ", "AT02", 176000F, FilterProduct.Style.TSHIRT, FilterProduct.Color.BLUE),
                ProductModel("Áo sơ mi cách điệu", "ACT03", 200000F, FilterProduct.Style.TSHIRT, FilterProduct.Color.RED),
                ProductModel("Quần Jean Nam", "QJ01", 230000F, FilterProduct.Style.JEAN, FilterProduct.Color.BLUE),
                ProductModel("Quần Jean Nữ", "QJ02", 270000F, FilterProduct.Style.JEAN, FilterProduct.Color.BLACK),
                ProductModel("Quần Jean nam rách", "QJ03", 340000F, FilterProduct.Style.JEAN, FilterProduct.Color.WHITE),
                ProductModel("Áo thun cotton", "AT04", 200000F, FilterProduct.Style.TSHIRT, FilterProduct.Color.WHITE),
                ProductModel("Áo thun cotton A2", "AT05", 150000F, FilterProduct.Style.TSHIRT, FilterProduct.Color.BLACK),
                ProductModel("Quần nam", "Q01", 220000F, FilterProduct.Style.SHORT, FilterProduct.Color.BLUE),
                ProductModel("Quần short nam", "Q02", 250000F, FilterProduct.Style.SHORT, FilterProduct.Color.BLACK),
                ProductModel("Áo thun", "AT05", 140000F, FilterProduct.Style.TSHIRT, FilterProduct.Color.RED),
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
                Toast.makeText(
                    context,
                    "Số lượng phải lớn hơn 0. Hãy kiểm tra lại",
                    Toast.LENGTH_SHORT
                ).show()
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
            if (product.style == style)
            {
                list.add(product)
            }
        }
        recyclerView?.adapter = ProductAdapter(list) { onItemClick(it) }
        recyclerView?.adapter?.notifyDataSetChanged()
    }


    @SuppressLint("ResourceAsColor")
    private fun resetOrder() {
        btnReset?.setOnClickListener {
            orderList = mutableListOf()
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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        conditionStyle = p0?.getItemAtPosition(p2).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    // lọc product
    private fun filterByProductStyle(style: String) {
        when(style){
            "T-Shirt" -> updateItemList(FilterProduct.Style.TSHIRT)
            "Quần Short" -> updateItemList(FilterProduct.Style.SHORT)
            "Quần Jean" -> updateItemList(FilterProduct.Style.JEAN)
            "Váy ngắn" -> updateItemList(FilterProduct.Style.SHORTDRESS)
            "Váy dài" -> updateItemList(FilterProduct.Style.LONGDRESS)
            else ->
                updateItemList("")
        }
    }

    private fun moveToOrderDetail(){
        sharedViewModel.setListOrder(orderList)
        tvOrderAmount?.setOnClickListener {
            (activity as MainActivity).replaceFragment(OrderDetailFragment())
        }
        btnTotal?.setOnClickListener {
            (activity as MainActivity).replaceFragment(OrderDetailFragment())
        }
    }

}