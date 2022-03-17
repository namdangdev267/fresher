package com.misa.fresher.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapter.BillProductAdapter
import com.misa.fresher.model.SelectedProduct
import java.text.DecimalFormat

class BillDetailFragment : Fragment() {
    var listBillDetail = mutableListOf<SelectedProduct>()
    val decimal=DecimalFormat("0,000.0")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bill_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listBillDetail = getSelectedProduct()
        setUpRecycleView()
        setUpView()
        onBack()
        openShippingInfor()
        saveBillDetail()
    }
    /**
     *Lấy danh sách sản phẩm đã chọn từ Sale Fragment
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun getSelectedProduct(): MutableList<SelectedProduct> {
        val list = arguments?.get("product").let {
            it as MutableList<SelectedProduct>
        }
        return list
    }
    /**
     *Nạp dữ liệu vào RecycleView hiển thị danh sách sản phẩm đã chọn
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun setUpRecycleView() {
        val rvBillDetails = view?.findViewById<RecyclerView>(R.id.rvSelectedProduct)
        val adapter = BillProductAdapter(listBillDetail as ArrayList<SelectedProduct>,requireContext(),{changeAmountProduct(it)})
        rvBillDetails?.adapter = adapter
        rvBillDetails?.layoutManager = LinearLayoutManager(requireActivity())
    }
    /**
     *Thay đổi UI khi nhận được danh sách sản phẩm đã chọn
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun setUpView()
    {
        val tvTotalSelectedProduct=view?.findViewById<TextView>(R.id.tvTotalSelectedProduct)
        val tvToTalPrice=view?.findViewById<TextView>(R.id.tvTotalBillPrice)
        val tvTotalMoney=view?.findViewById<TextView>(R.id.tvTotalMoney)
        tvTotalSelectedProduct.let {
            it?.text=listBillDetail.sumOf { it.amount }.toString()
            it?.setTextColor(Color.WHITE)
            it?.setBackgroundResource(R.drawable.textview_amount_border)
        }
        tvToTalPrice.let {
            it?.text =context?.resources?.getText(R.string.get_payment)
            it?.setTextColor(Color.WHITE)
            it?.setBackgroundResource(R.drawable.textview_totalprice_border)
        }
        tvTotalMoney?.text=decimal.format(listBillDetail.sumOf { it.amount*it.product.productPrice }).toString()
    }
    /**
     *Quay trở lại SaleFragment
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun onBack()
    {
        val ibBack=view?.findViewById<ImageButton>(R.id.ibBack)
        ibBack?.setOnClickListener { activity?.onBackPressed() }
        val tvBuyMore=view?.findViewById<TextView>(R.id.tvBuyMore)
        tvBuyMore?.setOnClickListener { activity?.onBackPressed() }
    }
    private fun changeAmountProduct(product: SelectedProduct) {
        for (i in listBillDetail) {
            if (i.product.productId == product.product.productId) {
                i.amount = product.amount
            }
        }
        view?.findViewById<TextView>(R.id.tvTotalMoney)?.text =
            decimal.format(listBillDetail.sumOf { it.amount * it.product.productPrice }).toString()
        view?.findViewById<TextView>(R.id.tvTotalSelectedProduct)?.text =
            listBillDetail.sumOf { it.amount }.toString()
    }
    /**
     *Chuyển hướng sang màn hình ShippingInfor
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun openShippingInfor()
    {
        val ivShipping=view?.findViewById<ImageView>(R.id.ivShipping)
        ivShipping?.setOnClickListener {
            findNavController().navigate(
                R.id.action_billDetailFragment_to_shippingInforFragment,
                bundleOf("listproduct" to listBillDetail))
        }
    }
    private fun saveBillDetail()
    {
        val tvTotalBillDetail=view?.findViewById<TextView>(R.id.tvTotalBillPrice)
        tvTotalBillDetail?.setOnClickListener{
            Toast.makeText(requireContext(),"Thêm thành công",Toast.LENGTH_LONG).show()
        }
    }

}