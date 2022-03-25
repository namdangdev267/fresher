package kma.longhoang.beta.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.BillState
import kma.longhoang.beta.login.MainActivity
import kma.longhoang.beta.R
import kma.longhoang.beta.adapter.ListBillAdapter
import kma.longhoang.beta.dao.BillDAO
import kma.longhoang.beta.dao.CustomerDAO
import kma.longhoang.beta.dao.OrderDAO
import kma.longhoang.beta.database.AppDatabase
import kma.longhoang.beta.model.BillModel
import kma.longhoang.beta.showNote
import java.text.SimpleDateFormat
import java.util.*

class BillListFragment : Fragment() {
    private var listBill: MutableList<BillModel>? = null
    private var recyclerView: RecyclerView? = null
    private var selectedDate: String? = null
    private var selectedState: BillState? = null

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listBill = getListBill()
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

    @RequiresApi(Build.VERSION_CODES.P)
    private fun getListBill(): MutableList<BillModel> {
        val customerDAO = CustomerDAO.getInstance(AppDatabase.getInstance(requireContext()))
        val billDAO = BillDAO.getInstance(AppDatabase.getInstance(requireContext()))
        var list = mutableListOf<BillModel>()
        list = billDAO?.getAllBill()!!
        val orderDAO = OrderDAO.getInstance(AppDatabase.getInstance(requireContext()))

        for (bill in list) {
            val listOrder = bill.id?.let { orderDAO?.getOrderById(it) }
            bill.listOrder = listOrder
            val customer = bill.customerId?.let { customerDAO?.getCustomerById(it) }
            if (bill.customerId == customer?.id) {
                bill.customer = customer
            }
        }
        return list
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

    private fun setVisible(view: View) {
        if (view.isVisible) {
            view.isGone = true
        } else {
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
        spinnerDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p0?.getItemAtPosition(p2).toString()) {
                    "Hôm nay" -> {
                        selectedDate = SimpleDateFormat(
                            "dd/MM/yyyy",
                            Locale.getDefault()
                        ).format(Calendar.getInstance().time)
                        updateBillList(selectedDate, selectedState)
                    }
                    "Hôm qua" -> {
                        var date = Date()
                        val c = Calendar.getInstance()
                        c.time = date
                        c.add(Calendar.DATE, -1)
                        date = c.time
                        selectedDate =
                            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
                        updateBillList(selectedDate, selectedState)
                    }
                    "Hôm kia" -> {
                        var date = Date()
                        val c = Calendar.getInstance()
                        c.time = date
                        c.add(Calendar.DATE, -2)
                        date = c.time
                        selectedDate =
                            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
                        updateBillList(selectedDate, selectedState)
                    }
                    "Tuần này" -> {
                        selectedDate = "week"
                        updateBillList(selectedDate, selectedState)
                    }
                    else
                    -> selectedDate = null
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedDate = null
            }

        }
        spinnerState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedState = when (p0?.getItemAtPosition(p2).toString()) {
                    "Đã thu tiền" -> BillState.PAYED
                    "Ghi nợ" -> BillState.DEBIT
                    "Đã hủy" -> BillState.CANCEL
                    else
                    -> null
                }
                updateBillList(selectedDate, selectedState)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedState = null
            }
        }
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
                bill.let {
                    it.listOrder?.map { order ->
                        order.price * order.amount
                    }?.sum()
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
            if (bill.customer?.name?.lowercase()?.contains(keySearch.lowercase()) == true ||
                bill.id.toString().lowercase().contains(keySearch.lowercase()) ||
                bill.customer?.phone?.lowercase()?.contains(keySearch.lowercase()) == true
            ) {
                list.add(bill)
            }
        }
        recyclerView?.adapter = ListBillAdapter(list)
        recyclerView?.adapter?.notifyDataSetChanged()
        showTotal(requireView(), list)
    }

    @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
    private fun updateBillList(
        date: String? = null,
        state: BillState? = null
    ) {
        val c = Calendar.getInstance()
        val listBillSearch = listBill
        val list = mutableListOf<BillModel>()
        if (listBillSearch != null) {
            for (bill in listBillSearch) {
                if (date == "week" && state != null) {
                    val currentDay = SimpleDateFormat("dd/MM/yyyy").parse(bill.date)
                    Log.d("Test", "" + currentDay)
                    val currentWeek = Calendar.getInstance()
                    currentWeek.time = currentDay
                    Log.d("Test", "Time: $currentWeek")
                    if (currentWeek.get(Calendar.WEEK_OF_YEAR) == c.get(Calendar.WEEK_OF_YEAR)
                        && currentWeek.get(Calendar.YEAR) == c.get(Calendar.YEAR)
                        && bill.state == state
                    ) {
                        list.add(bill)
                    }
                } else if (date == "week" && state == null) {
                    val currentDay = SimpleDateFormat("dd/MM/yyyy").parse(bill.date)
                    val currentWeek = Calendar.getInstance()
                    currentWeek.time = currentDay
                    Log.d("Test", "" + currentDay)
                    Log.d("Test", "Time: $currentWeek")
                    if (currentWeek.get(Calendar.WEEK_OF_YEAR) == c.get(Calendar.WEEK_OF_YEAR)
                        && currentWeek.get(Calendar.YEAR) == c.get(Calendar.YEAR)
                    ) {
                        list.add(bill)
                    }
                } else if (date != null && state != null) {
                    if (bill.date == date && bill.state == state) {
                        list.add(bill)
                    }
                } else if (date != null) {
                    if (bill.date == date) {
                        list.add(bill)
                    }
                } else if (state != null) {
                    if (bill.state == state) {
                        list.add(bill)
                    }
                } else {
                    list.add(bill)
                }
            }
        }
        recyclerView?.adapter = ListBillAdapter(list)
        recyclerView?.adapter?.notifyDataSetChanged()
        showTotal(requireView(), list)
    }
}
