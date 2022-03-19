package com.misa.fresher.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapters.ProductSelectedAdapter
import com.misa.fresher.data.DataForTest
import com.misa.fresher.model.Bill
import com.misa.fresher.model.Customer
import com.misa.fresher.model.SelectedProducts
import com.misa.fresher.showToast
import com.misa.fresher.viewModel.BillsViewModel
import com.misa.fresher.viewModel.CustomerViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Tạo BillDetail
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class BillDetailFragment : Fragment() {
    private var listFromSale = mutableListOf<SelectedProducts>()
    private val rnds = (2132323..5132323).random()
    private var bill = Bill(listFromSale, rnds, null, SimpleDateFormat("dd/M/yyyy").format(Date()))
    private val billsViewModel: BillsViewModel by activityViewModels()
    private val customerViewModel: CustomerViewModel by activityViewModels()
    private var cus: Customer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBillId(view)
        listFromSale = getDataFromSaleFragment()
        configListSelectedItem(view)
        configRecyclerView(view)
        navigaEvent(view)
        updateReceiver(view)
        setCustomer(view)
        deleteCustomer(view)
    }

    /**
     * Delete Customer
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun deleteCustomer(view: View) {
        val ivCancel = view.findViewById<ImageView>(R.id.ivCancelBillDetail)
        val tvCustomer = view.findViewById<TextView>(R.id.tvContactBillsDetail)
        ivCancel.setOnClickListener {
            customerViewModel.deleteCustomer()
            ivCancel.isVisible = false
            tvCustomer.hint = "Tên khách hàng, số điện thoại"
            tvCustomer.text = ""
            tvCustomer.isSelected = false
        }
    }

    /**
     * tạo fun để lấy random id cho Bill
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun getBillId(view: View) {
        view.findViewById<TextView>(R.id.tvBillId).text = rnds.toString()
    }

    /**
     * Lấy Customer từ màn trước(sale)
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun updateReceiver(view: View) {
        val tvCustomer = view.findViewById<TextView>(R.id.tvContactBillsDetail)
        val ivCancel = view.findViewById<ImageView>(R.id.ivCancelBillDetail)
        tvCustomer.isSelected = true
        customerViewModel.customer.observe(viewLifecycleOwner, Observer { customer ->
            customer?.let {
                tvCustomer.text = it.name + "(" + it.number + ")"
                tvCustomer.isSelected = true
                bill.customer = it
                ivCancel.isVisible = true
            }
        })

    }

    /**
     * config ProductSelected Recycler View
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configRecyclerView(view: View) {
        val rcv = view.findViewById<RecyclerView>(R.id.rcvListItem)
        val adpter = ProductSelectedAdapter(listFromSale) {
            for (selectedPro in listFromSale) {
                if (selectedPro.product == it.product) {
                    selectedPro.amonut = it.amonut
                }
            }
            rcv.adapter?.notifyDataSetChanged()
            configListSelectedItem(view)

        }
        rcv.adapter = adpter
        rcv.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * Thay đổi Text cho Button số lượng + tv tổng giá
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configListSelectedItem(view: View) {
        val amount = listFromSale.sumOf { it.amonut }
        val totalPrice = listFromSale.sumOf { it.amonut * it.product.price }
        view.findViewById<Button>(R.id.btnAmountBills).text = amount.toString()
        view.findViewById<TextView>(R.id.tvTotalPrice_bill).text = totalPrice.toString()
    }

    /**
     * Lấy giá trị bundle từ sale
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun getDataFromSaleFragment(): MutableList<SelectedProducts> {
        val list = arguments?.get(SELECTED_ITEMS).let {
            it as MutableList<SelectedProducts>
        }
        return list
    }

    /**
     * Navigate các màn
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun navigaEvent(view: View) {
        view.findViewById<ImageButton>(R.id.ivShipInfor).setOnClickListener {
            findNavController().navigate(R.id.action_nav_billDetail_to_nav_shipInfor)
        }
        view.findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            activity?.onBackPressed()
        }
        view.findViewById<TextView>(R.id.tvBuyMore).setOnClickListener {
            activity?.onBackPressed()
        }
        view.findViewById<ImageView>(R.id.ivBuyMore).setOnClickListener {
            activity?.onBackPressed()
        }
        view.findViewById<Button>(R.id.btnTotalPriceBill).setOnClickListener {
            bill.listSelectedProduct = listFromSale
            billsViewModel.addBill(bill)
            findNavController().navigate(R.id.action_nav_billDetail_to_nav_sale)
            requireContext().showToast("Thanh toán thành công")
        }
    }

    /**
     * Lấy random 1 khách hàng trong list
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun setCustomer(view: View) {
        val tvCus = view.findViewById<TextView>(R.id.tvContactBillsDetail)
        val ivCus = view.findViewById<ImageView>(R.id.ivContactBillDetail)
        tvCus.setOnClickListener {
            cus = DataForTest.listCus.get((0..DataForTest.listCus.size - 1).random())
            tvCus.text = "${cus?.name} (${cus?.number})"
            tvCus.isSelected = true
            customerViewModel.addCustomer(cus!!)
        }
        ivCus.setOnClickListener {
            cus = DataForTest.listCus.get((0..DataForTest.listCus.size - 1).random())
            tvCus.text = "${cus?.name} (${cus?.number})"
            tvCus.isSelected = true
            customerViewModel.addCustomer(cus!!)
        }
    }

    companion object {
        const val SELECTED_ITEMS = "items"
    }
}