package com.misa.fresher.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.R
import com.misa.fresher.adapter.ProductAdapter
import com.misa.fresher.models.Product
/*
*Màn hình sale
*@Author:LTDat
*@Date:3/12/2022
*/
class HomeFragment : Fragment(){
    var toggle : ActionBarDrawerToggle? = null
    var rcv : RecyclerView? = null
    var mView :View? = null
    private var Data = Product.getListProduct()
    var bill  = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        disData()
        configRecycleView()
        onClickPayment()
        searchProduct()
        onClickReset()
        onClickItemNav()
        configToolbar()
    }
    fun disData(){
        val rcv = mView?.findViewById<View>(R.id.rcv_product) as RecyclerView
        val adapter = ProductAdapter(Product.getListProduct(),{onItemClickProduct(it)})
        rcv?.adapter=adapter
        rcv?.layoutManager = LinearLayoutManager(activity)
    }
    fun configToolbar(){
        val drawerLayout  = mView?.findViewById<DrawerLayout>(R.id.drawerLayout)
        val toolbar = mView?.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(requireActivity(),drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout?.addDrawerListener(toggle!!)
        toggle?.syncState()
    }
    fun onClickItemNav(){
        val navView  = mView?.findViewById<NavigationView>(R.id.nav_view)
        navView?.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_sale -> Toast.makeText(context,"SaleFragment", Toast.LENGTH_SHORT).show()
                R.id.nav_pay -> Toast.makeText(context,"PayFragment", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle?.onOptionsItemSelected(item) == true){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun onClickReset(){
        val btnReset = mView?.findViewById<ImageButton>(R.id.btn_reset)
        val btnAmount = mView?.findViewById<Button>(R.id.btn_amount)
        val btnSumMoney = mView?.findViewById<Button>(R.id.btn_sum_money)

        btnReset?.setOnClickListener{
            bill.clear()
            it.background = AppCompatResources.getDrawable(requireContext(),R.drawable.bg_btn_reset_grey)
            btnAmount?.let{
                it.text = "0"
                it.background = AppCompatResources.getDrawable(requireContext(),R.drawable.bg_btn_amount_grey)
            }
            btnSumMoney?.let {
                it.text ="Chưa chọn hàng"
                it.background = AppCompatResources.getDrawable(requireContext(),R.drawable.bg_btn_sum_money_grey)
            }
        }
    }
    fun searchProduct(){
        val etSearch = mView?.findViewById<EditText>(R.id.et_search)
        etSearch?.doAfterTextChanged {
            updateList(etSearch.text.toString())
        }
    }
    //Bắt sự kiện click vào tổng tiền và số lượng
    private fun onClickPayment() {
        val btnAmount = mView?.findViewById<Button>(R.id.btn_amount)
        val btnSumMoney = mView?.findViewById<Button>(R.id.btn_sum_money)

        btnAmount?.setOnClickListener{
            if(bill.size > 0)
            findNavController().navigate(R.id.action_homeFragment_to_paymentFragment)
        }

        btnSumMoney?.setOnClickListener{
            if(bill.size > 0)
            findNavController().navigate(R.id.action_homeFragment_to_paymentFragment)
        }
    }
    //Cập nhật lại danh sách hàng thanh toán
    private fun updateList(toString: String) {
        var list = mutableListOf<Product>()
        for (i in Data){
            if(i.productName.contains(toString)){
                list.add(i)
            }
        }
        rcv?.adapter = ProductAdapter(list,{onItemClickProduct(it)})
        rcv?.adapter?.notifyDataSetChanged()
    }
    // Đổ dữ liệu tìm kiếm lên màn hình
    fun configRecycleView(){
        var adapter = ProductAdapter(Data,{onItemClickProduct(it)})
        adapter.notifyDataSetChanged()
        rcv = mView?.findViewById(R.id.rcv_product)
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }
    // Hiển thị bottom sheet và thêm dữ liệu vào list
    private fun onItemClickProduct(product: Product) {
            bill.add(product)
            val bottomSheetDiglog = BottomSheetDialog(requireContext(),R.style.BottomSheetDialogTheme)
            val bottomSheetView : View = LayoutInflater.from(requireContext()).inflate(
            R.layout.dialog_bottomsheet,
                mView as DrawerLayout,false
        )
            bottomSheetView.findViewById<TextView>(R.id.tv_diglog_1).text = product.productName
            bottomSheetView.findViewById<TextView>(R.id.tv_diglog_2).text = product.productID
            bottomSheetDiglog.setContentView(bottomSheetView)
            bottomSheetDiglog.show()
            updateItemSelected()
    }
    private fun updateItemSelected() {
        val btnAmount = mView?.findViewById<Button>(R.id.btn_amount)
        val btnSumMoney = mView?.findViewById<Button>(R.id.btn_sum_money)
        val btnReset = mView?.findViewById<ImageButton>(R.id.btn_reset)

        var price = 0.0
        btnAmount?.let{
            it.text = "${bill.size}"
            it.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_btn_amount_blue
            )
        }
        for(i in bill){
            price += i.price
        }
        btnSumMoney?.let{
            it.text = "Tổng $price"
            it.background = AppCompatResources.getDrawable(requireContext(),R.drawable.bg_btn_sum_money_blue)
        }
        if(bill.size != null){
           btnReset?.background = AppCompatResources.getDrawable(requireContext(),R.drawable.bg_btn_reset_blue)
        }

    }

}