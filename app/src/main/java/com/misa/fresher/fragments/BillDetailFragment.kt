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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapters.ProductSelectedAdapter
import com.misa.fresher.model.Products
import com.misa.fresher.model.SelectedProducts

class BillDetailFragment : Fragment() {
    private var globalView : View ?=null
    private var listFromSale = mutableListOf<SelectedProducts>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        globalView = view
        listFromSale=getDataFromSaleFragment()
        configListSelectedItem(listFromSale)

        configRecyclerView()
        navigaEvent()

    }

    private fun configRecyclerView() {
        val rcv = globalView?.findViewById<RecyclerView>(R.id.rcvListItem)
        val adpter = ProductSelectedAdapter(listFromSale,{changeAmountSelectedProduct(it)})
        rcv?.adapter=adpter
        rcv?.layoutManager=LinearLayoutManager(requireContext())
    }

    private fun changeAmountSelectedProduct(selectedProducts: SelectedProducts)
    {
        Log.e("amount selected",selectedProducts.amonut.toString())
        for(i in listFromSale){
            if (i.product==selectedProducts.product){
                i.amonut=selectedProducts.amonut
            }
        }
        configListSelectedItem(listFromSale)

    }

    private fun configListSelectedItem(list : MutableList<SelectedProducts>) {
        val amount = list.sumOf { it.amonut }
        val totalPrice = list.sumOf { it.amonut*it.product.price }
        globalView?.findViewById<Button>(R.id.btnAmountBills)?.text= amount.toString()
        globalView?.findViewById<TextView>(R.id.tvTotalPrice_bill)?.text= totalPrice.toString()
    }

    private fun getDataFromSaleFragment(): MutableList<SelectedProducts> {
        val list = arguments?.get("items").let {
            it as MutableList<SelectedProducts>
        }
        Log.d("test1",list[0].toString())
        return list
    }

    private fun navigaEvent() {
        globalView?.findViewById<ImageButton>(R.id.ivShipInfor)?.setOnClickListener {
            findNavController().navigate(R.id.action_nav_billDetail_to_nav_shipInfor)
        }
        globalView?.findViewById<ImageButton>(R.id.btnBack)?.setOnClickListener {
            activity?.onBackPressed()
        }
        globalView?.findViewById<TextView>(R.id.tvBuyMore)?.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}