package com.misa.fresher.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapter.ProductAdapter
import com.misa.fresher.models.Product
import com.misa.fresher.models.SelectedProduct

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
    var bill = arrayListOf<SelectedProduct>()
   // var bill  = mutableListOf<Product>()

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
        configToolbar()
        configFilter()
        sortFilter()
    }
    fun disData(){
        val rcv = mView?.findViewById<View>(R.id.rcv_product) as RecyclerView
        val adapter = ProductAdapter(Product.getListProduct(),{onItemClickProduct(it)})
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(activity)
    }
    fun configFilter(){
        val mDrawer = mView?.findViewById<DrawerLayout>(R.id.drawerFilter)
        mDrawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        mView?.findViewById<ImageButton>(R.id.img_btn_icon4)?.setOnClickListener{
            configFilter()
            mDrawer?.openDrawer(Gravity.RIGHT)
        }
    }

    fun sortFilter(){
        var listFilter = mutableListOf<Product>()
        val mDrawer = mView?.findViewById<DrawerLayout>(R.id.drawerFilter)
        val radioButtonName = mView?.findViewById<RadioButton>(R.id.rb_name)
        val radioButtonPrice = mView?.findViewById<RadioButton>(R.id.rb_price)
        val spnColor = mView?.findViewById<Spinner>(R.id.spnColor)
        val spnSize = mView?.findViewById<Spinner>(R.id.spnSize)
        val btnXong = mView?.findViewById<Button>(R.id.btn_Xong)
        val btnBoLoc = mView?.findViewById<Button>(R.id.btn_BoLoc)

        val spnColorSelected = spnColor?.selectedItem.toString()
        val spnSizeSelected = spnSize?.selectedItem.toString()

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.colorArray,
            android.R.layout.simple_spinner_item
        ).also {
            arrayAdapter -> arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnColor?.adapter = arrayAdapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sizeArray,
            android.R.layout.simple_spinner_item
        ).also {
                arrayAdapter -> arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnSize?.adapter = arrayAdapter
        }

        btnXong?.setOnClickListener{
            if(radioButtonName?.isChecked == true){
                listFilter = Data.sortedWith(compareBy(Product :: productName)) as MutableList<Product>
            }else if(radioButtonPrice?.isChecked == true) {
                listFilter = Data.sortedWith(compareBy(Product :: price)) as MutableList<Product>
            }
            var adapter = ProductAdapter(listFilter,{onItemClickProduct(it)})
            rcv = mView?.findViewById(R.id.rcv_product)
            rcv?.adapter = adapter
            adapter.notifyDataSetChanged()
            rcv?.layoutManager = LinearLayoutManager(requireContext())
            mDrawer?.closeDrawer(Gravity.RIGHT)
        }

        btnBoLoc?.setOnClickListener{
            radioButtonName?.isChecked = true
            radioButtonPrice?.isChecked = false
            spnColor?.setSelection(0)
            spnSize?.setSelection(0)
        }
    }

    fun configToolbar(){
        val toolbar = mView?.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val drawerLayout = (activity as MainActivity).findViewById<DrawerLayout>(R.id.drawerLayout)
        val toggle = ActionBarDrawerToggle(requireActivity(),drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
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
            findNavController().navigate(R.id.action_homeFragment_to_paymentFragment, bundleOf("product" to bill))
        }

        btnSumMoney?.setOnClickListener{
            if(bill.size > 0)
            findNavController().navigate(R.id.action_homeFragment_to_paymentFragment, bundleOf("product" to bill))
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
        rcv = mView?.findViewById(R.id.rcv_product)
        rcv?.adapter = adapter
        adapter.notifyDataSetChanged()
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }
    // Hiển thị bottom sheet và thêm dữ liệu vào list
    private fun onItemClickProduct(product: Product) {
            //bill.add(product)
            val bottomSheetDiglog = BottomSheetDialog(requireContext(),R.style.BottomSheetDialogTheme)
            val bottomSheetView : View = LayoutInflater.from(requireContext()).inflate(
            R.layout.dialog_bottomsheet,
                mView as DrawerLayout,false
        )
            bottomSheetView.findViewById<TextView>(R.id.tv_diglog_1).text = product.productName
            bottomSheetView.findViewById<TextView>(R.id.tv_diglog_2).text = product.productCode
            bottomSheetDiglog.setContentView(bottomSheetView)
            bottomSheetDiglog.show()

            val btnGiam = bottomSheetView.findViewById<ImageButton>(R.id.imgbtn_giam_sl)
            val tvSoLuong = bottomSheetView.findViewById<TextView>(R.id.tv_sum_sl)
            val btnTang = bottomSheetView.findViewById<ImageButton>(R.id.imgbtn_tang_sl)

            var amount = tvSoLuong.text.toString().toInt()

            btnTang.setOnClickListener(){
                amount += 1
                tvSoLuong.text = amount.toString()
            }

            btnGiam.setOnClickListener(){
                if(amount > 1){
                    amount -= 1
                    tvSoLuong.text = amount.toString()
                }else{
                    Toast.makeText(requireContext(),"Số lượng phải lớn hơn 0",Toast.LENGTH_SHORT).show()
                }
            }

            bottomSheetDiglog.setOnDismissListener(){
                if(checkSeclectedProduct(product.productCode)){
                    for(i in bill)
                        if(i.product.productCode == product.productCode)
                            i.amount = i.amount + amount
                }
                else{
                    bill.add(SelectedProduct(product,amount))
                }
                updateItemSelected()
            }

    }
    private fun updateItemSelected() {
        val btnAmount = mView?.findViewById<Button>(R.id.btn_amount)
        val btnSumMoney = mView?.findViewById<Button>(R.id.btn_sum_money)
        val btnReset = mView?.findViewById<ImageButton>(R.id.btn_reset)

        btnAmount?.let{
            it.text = bill.sumOf { it.amount }.toString()
            it.setTextColor(Color.WHITE)
            it.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_btn_amount_blue
            )
        }
        btnSumMoney?.let{
            it.text = bill.sumOf { it.amount * it.product.price }.toString()
            it.background = AppCompatResources.getDrawable(requireContext(),R.drawable.bg_btn_sum_money_blue)
        }
        if(bill.size != null){
           btnReset?.background = AppCompatResources.getDrawable(requireContext(),R.drawable.bg_btn_reset_blue)
        }

    }

    fun checkSeclectedProduct(id : String) : Boolean{
        var isCheck = false
        for(i in bill ){
            if(i.product.productCode == id){
                isCheck = true
            }
        }
        return isCheck
    }

}