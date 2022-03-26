package com.misa.fresher.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapter.BillAdapter
import com.misa.fresher.models.BillViewModel
import com.misa.fresher.models.Bill
import com.misa.fresher.models.SelectedProduct
import com.misa.fresher.models.Customer

class PaymentFragment : Fragment() {

    var listBillDetail = arrayListOf<SelectedProduct>()
    val viewModel : BillViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listBillDetail = getSelectedProduct()
        setUpRecycleView(view)
        onClickBack(view)
        setUpView(view)
        onChageDataToListOfBill(view)
    }
    //Chuyển từ màn hình thanh toán về màn hình mua hàng
    fun onClickBack(view : View){
        val btnBack = view.findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener(){
            activity?.onBackPressed()
        }
    }
    fun changeAmountProduct(product: SelectedProduct){
        for(i in listBillDetail){
            if(i.product.productCode == product.product.productCode){
                i.amount = product.amount
            }
        }
        view?.findViewById<TextView>(R.id.tv_TongTien)?.text = listBillDetail.sumOf{
            it.amount * it.product.price
        }.toString()
        view?.findViewById<TextView>(R.id.tv_dem_tong)?.text = listBillDetail.sumOf{
            it.amount
        }.toString()
    }
    //Lấy sản phẩm từ màn Sale
    fun getSelectedProduct() : ArrayList<SelectedProduct>{
        return arguments?.get(PRODUCTs) as ArrayList<SelectedProduct>
    }
    //Nạp dữ liệu vào Recycleview hiển thị danh sách đã chọn
    fun setUpRecycleView(view : View){
        val rvBillDetails = view.findViewById<RecyclerView>(R.id.rv_SeclectedItem)
        val adapter = BillAdapter(
            listBillDetail,requireContext(),{
                changeAmountProduct(it)
            }
        )
        rvBillDetails.adapter = adapter
        rvBillDetails.layoutManager = LinearLayoutManager(requireContext())
    }

    fun setUpView (view : View){
        val tvtotalSelected = view.findViewById<TextView>(R.id.tv_dem_tong)
        val tvTotalPrice = view.findViewById<TextView>(R.id.tv_thuTien)
        val tvTotalPMoney = view.findViewById<TextView>(R.id.tv_TongTien)

        tvtotalSelected.let {
            it.text = listBillDetail.sumOf { it.amount }.toString()
            it.setTextColor(Color.WHITE)
            it.setBackgroundResource(R.drawable.bg_left_blue_radius_5dp)
        }
        tvTotalPrice.let {
            it.text = "Thu Tiền"
            it.setTextColor(Color.WHITE)
            it.setBackgroundResource(R.drawable.bg_right_blue_radius_5dp)
        }
        tvTotalPMoney.text =listBillDetail.sumOf { it.amount * it.product.price }.toString()
    }

    fun onChageDataToListOfBill(view : View) {
        val tvidBill = view.findViewById<TextView>(R.id.tv_id_bill)
        val tvThuTien = view.findViewById<Button>(R.id.tv_thuTien)
        val tvDemTong = view.findViewById<Button>(R.id.tv_dem_tong)
        val btnShipping = view.findViewById<ImageButton>(R.id.img_btn_shipping)

        tvThuTien?.setOnClickListener {
            val billInfor = Bill(
                tvidBill.text.toString().toInt(),
                listBillDetail, Customer(1, "2", "3", "4"), 5)
            viewModel.add(billInfor)
            Toast.makeText(requireContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_paymentFragment_to_listOfBillFragment)
        }
        tvDemTong.setOnClickListener(){
            val billInfor = Bill(
                    tvidBill.text.toString().toInt(),
            listBillDetail, Customer(1, "2", "3", "4"), 5)
            viewModel.add(billInfor)
            Toast.makeText(requireContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_paymentFragment_to_listOfBillFragment)
        }
        btnShipping.setOnClickListener(){
            findNavController().navigate(R.id.action_paymentFragment_to_shippingInformationFragment)
        }
    }
    companion object {
        const val PRODUCTs = "product"
    }

}