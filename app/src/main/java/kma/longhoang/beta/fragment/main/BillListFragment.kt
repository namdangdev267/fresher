package kma.longhoang.beta.fragment.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.MainActivity
import kma.longhoang.beta.R
import kma.longhoang.beta.SaleViewModel
import kma.longhoang.beta.adapter.ListBillAdapter
import kma.longhoang.beta.adapter.ProductAdapter
import kma.longhoang.beta.model.BillModel
import kma.longhoang.beta.model.FilterProduct
import kma.longhoang.beta.model.ProductModel

class BillListFragment : Fragment() {

    private val saleViewModel: SaleViewModel by activityViewModels()
    private var listBill : MutableList<BillModel> ?= null
    private var recyclerView: RecyclerView?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listBill = saleViewModel.listBill.value
        recyclerView = view.findViewById(R.id.recycler_bill_list)
        navMenu(view)
        searchBill(view)
        filterBill(view)
        setupRecyclerBill(view)
        listBill?.let { showTotal(view, it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_list, container, false)
    }


    private fun navMenu(view: View) {
        view.findViewById<ImageButton>(R.id.button_menu).setOnClickListener {
            (activity as MainActivity).drawerView()
        }
    }

    private fun searchBill(view: View) {
        val btnSearch = view.findViewById<ImageButton>(R.id.button_search)
        val tvClose = view.findViewById<TextView>(R.id.text_close)
        val tvListBill = view.findViewById<TextView>(R.id.text_list_bill)
        val searchView = view.findViewById<LinearLayout>(R.id.layout_search)
        val etSearch = searchView.findViewById<EditText>(R.id.edt_search_bill)
        btnSearch.setOnClickListener {
            setVisible(tvListBill)
            setVisible(btnSearch)
            setVisible(tvClose)
            setVisible(searchView)
        }
        tvClose.setOnClickListener {
            setVisible(searchView)
            setVisible(tvClose)
            setVisible(btnSearch)
            setVisible(tvListBill)
            etSearch.setText("")
        }
        etSearch.doAfterTextChanged {
            updateListBill(etSearch.text.toString())
        }
    }

    private fun setVisible(view: View){
        if (view.isVisible){
            view.isGone = true
        }else{
            view.isVisible = true
        }
    }

    private fun filterBill(view: View) {
        val spinnerDay = view.findViewById<Spinner>(R.id.filter_by_day)
        val spinnerState = view.findViewById<Spinner>(R.id.filter_by_state)
        val adapterDay: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filter_bill_day,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )
        val adapterState: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filter_bill_state,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )
        spinnerDay.adapter = adapterDay
        spinnerState.adapter = adapterState

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerBill(view: View) {
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView?.adapter = listBill?.let { ListBillAdapter(it) }
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun showTotal(view: View, listBill: MutableList<BillModel>) {
        view.findViewById<TextView>(R.id.text_total_amount_bill).text = listBill.size.toString()
        val totalPrice = listBill.let { list ->
            list.sumOf { bill ->
                bill.orderList?.let { order ->
                    order.map {
                        it.price * it.amount
                    }.sum()
                }?.toDouble() ?: 0.0
            }
        }
        view.findViewById<TextView>(R.id.text_total_price).text = totalPrice.toString()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun updateListBill(keySearch: String) {
        val listBillSearch = listBill
        val list = mutableListOf<BillModel>()
        for (bill in listBillSearch!!) {
            if (bill.customer?.name?.lowercase()?.contains(keySearch.lowercase()) == true||
                bill.id.toString().lowercase().contains(keySearch.lowercase())||
                        bill.customer?.phone?.lowercase()?.contains(keySearch.lowercase())== true){
                list.add(bill)
            }
        }
        recyclerView?.adapter = ListBillAdapter(list)
        recyclerView?.adapter?.notifyDataSetChanged()
        showTotal(requireView(), list)
    }
}
