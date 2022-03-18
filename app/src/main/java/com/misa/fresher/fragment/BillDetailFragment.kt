package com.misa.fresher.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.BillViewModel
import com.misa.fresher.R
import com.misa.fresher.adapter.BillProductAdapter
import com.misa.fresher.model.BillInfor
import com.misa.fresher.model.SelectedProduct
import com.misa.fresher.model.ShipInfor
import java.text.DecimalFormat
import kotlin.random.Random

class BillDetailFragment : Fragment() {
    var listBillDetail = mutableListOf<SelectedProduct>()
    val decimal = DecimalFormat("0,000.0")
    var viewModel: BillViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(BillViewModel::class.java)
        return inflater.inflate(R.layout.fragment_bill_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listBillDetail = getSelectedProduct()
        setUpRecycleView(view)
        setUpView(view)
        onBack(view)
        openShippingInfor(view)
        saveBillDetail(view)
        randomBillNum(view)
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
    private fun setUpRecycleView(view: View) {
        val rvBillDetails = view.findViewById<RecyclerView>(R.id.rvSelectedProduct)
        val adapter = BillProductAdapter(
            listBillDetail as ArrayList<SelectedProduct>,
            requireContext(),
            { changeAmountProduct(it) })
        rvBillDetails?.adapter = adapter
        rvBillDetails?.layoutManager = LinearLayoutManager(requireActivity())
    }

    /**
     *Thay đổi UI khi nhận được danh sách sản phẩm đã chọn
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun setUpView(view: View) {
        val tvTotalSelectedProduct = view.findViewById<TextView>(R.id.tvTotalSelectedProduct)
        val tvToTalPrice = view.findViewById<TextView>(R.id.tvTotalBillPrice)
        val tvTotalMoney = view.findViewById<TextView>(R.id.tvTotalMoney)
        tvTotalSelectedProduct.let {
            it?.text = listBillDetail.sumOf { it.amount }.toString()
            it?.setTextColor(Color.WHITE)
            it?.setBackgroundResource(R.drawable.textview_amount_border)
        }
        tvToTalPrice.let {
            it?.text = context?.resources?.getText(R.string.get_payment)
            it?.setTextColor(Color.WHITE)
            it?.setBackgroundResource(R.drawable.textview_totalprice_border)
        }
        tvTotalMoney?.text =
            decimal.format(listBillDetail.sumOf { it.amount * it.product.productPrice }).toString()
    }

    /**
     *Quay trở lại SaleFragment
     *@author:NCPhuc
     *@date:3/16/2022
     **/
    private fun onBack(view: View) {
        val ibBack = view.findViewById<ImageButton>(R.id.ibBack)
        ibBack?.setOnClickListener { activity?.onBackPressed() }
        val tvBuyMore = view.findViewById<TextView>(R.id.tvBuyMore)
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
    private fun openShippingInfor(view: View) {
        val ivShipping = view.findViewById<ImageView>(R.id.ivShipping)
        ivShipping?.setOnClickListener {
            findNavController().navigate(
                R.id.action_billDetailFragment_to_shippingInforFragment,
                bundleOf("listproduct" to listBillDetail)
            )
        }
    }
    /**
     *Lưu hóa đơn vào danh sách hóa đơn
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun saveBillDetail(view: View) {
        val tvBillCode = view.findViewById<TextView>(R.id.tvBillCode)
        val tvTotalBillDetail = view.findViewById<TextView>(R.id.tvTotalBillPrice)
        tvTotalBillDetail?.setOnClickListener {
            val billInfor = BillInfor(
                tvBillCode.text.toString().toInt(),
                listBillDetail,ShipInfor("1", "2", "3", "4", "5"))
            Log.e("aaaaaaâ",listBillDetail.get(0).amount.toString())
            viewModel?.add(billInfor)
            viewModel?.listItemBill?.observe(viewLifecycleOwner, Observer {
            })
            Toast.makeText(requireContext(), "Thêm hóa đơn thành công", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_billDetailFragment_to_saleFragment)
        }
    }
    /**
     *Thiết lập mã số hóa đơn ngẫu nhiên
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun randomBillNum(view: View) {
        val billCode = Random.nextInt(100000, 300000)
        val tvBillCode = view.findViewById<TextView>(R.id.tvBillCode)
        tvBillCode.text = billCode.toString()
    }

}