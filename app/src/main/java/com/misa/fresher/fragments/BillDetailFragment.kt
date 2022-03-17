package com.misa.fresher.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapters.ProductSelectedAdapter
import com.misa.fresher.model.Bill
import com.misa.fresher.model.SelectedProducts
import com.misa.fresher.viewModel.BillsViewModel
import com.misa.fresher.viewModel.ShipInforViewModel

class BillDetailFragment : Fragment() {
    private var listFromSale = mutableListOf<SelectedProducts>()
    val rnds = (1000..9999).random()
    var bill = Bill(listFromSale,rnds,null)
    var viewModel: BillsViewModel ?= null
    var shipInforviewModel: ShipInforViewModel?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity()).get(BillsViewModel::class.java)
        shipInforviewModel = ViewModelProvider(requireActivity()).get(ShipInforViewModel::class.java)
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
    }

    private fun getBillId(view : View) {
        view.findViewById<TextView>(R.id.tvBillId).text=rnds.toString()
    }

    private fun updateReceiver(view: View) {
        shipInforviewModel?.receiver?.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.tvContactBillsDetail).text=it.name+"("+it.phoneNumber+")"
            bill.receiver=it
        })
    }

    private fun configRecyclerView(view: View) {
        val rcv = view.findViewById<RecyclerView>(R.id.rcvListItem)
        val adpter = ProductSelectedAdapter(listFromSale, { changeAmountSelectedProduct(it, view) })
        rcv?.adapter = adpter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun changeAmountSelectedProduct(selectedProducts: SelectedProducts, view: View) {
        for (selectedPro in listFromSale) {
            if (selectedPro.product == selectedProducts.product) {
                selectedPro.amonut = selectedProducts.amonut
            }
        }
        configListSelectedItem(view)
    }

    private fun configListSelectedItem(view: View) {
        val amount = listFromSale.sumOf { it.amonut }
        val totalPrice = listFromSale.sumOf { it.amonut * it.product.price }
        view.findViewById<Button>(R.id.btnAmountBills)?.text = amount.toString()
        view.findViewById<TextView>(R.id.tvTotalPrice_bill)?.text = totalPrice.toString()
    }

    private fun getDataFromSaleFragment(): MutableList<SelectedProducts> {
        val list = arguments?.get(SELECTED_ITEMS).let {
            it as MutableList<SelectedProducts>
        }
        return list
    }

    private fun navigaEvent(view: View) {
        view.findViewById<ImageButton>(R.id.ivShipInfor)?.setOnClickListener {
            findNavController().navigate(R.id.action_nav_billDetail_to_nav_shipInfor)
        }
        view.findViewById<ImageButton>(R.id.btnBack)?.setOnClickListener {
            activity?.onBackPressed()
        }
        view.findViewById<TextView>(R.id.tvBuyMore)?.setOnClickListener {
            activity?.onBackPressed()
        }
        view.findViewById<Button>(R.id.btnTotalPriceBill).setOnClickListener {
            bill.listSelectedProduct=listFromSale
            viewModel?.addBill(bill)
            findNavController().navigate(R.id.action_nav_billDetail_to_nav_sale)
            Toast.makeText(requireContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show()
        }
    }
    companion object {
        const val SELECTED_ITEMS = "items"
    }
}