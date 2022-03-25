package kma.longhoang.beta.fragment.delivery

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.BillState
import kma.longhoang.beta.R
import kma.longhoang.beta.SaleViewModel
import kma.longhoang.beta.adapter.OrderDetailAdapter
import kma.longhoang.beta.dao.BillDAO
import kma.longhoang.beta.dao.CustomerDAO
import kma.longhoang.beta.dao.OrderDAO
import kma.longhoang.beta.dao.ProductDAO
import kma.longhoang.beta.database.AppDatabase
import kma.longhoang.beta.model.BillModel
import kma.longhoang.beta.model.CustomerModel
import kma.longhoang.beta.model.OrderModel
import kma.longhoang.beta.showNote
import org.w3c.dom.Text
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class OrderDetailFragment : Fragment() {
    private val saleViewModel: SaleViewModel by activityViewModels()
    private var billId: Int = 0

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billId(view)
        setupRecyclerView()
        customerInfo(view)
        backFragment()
        showTotal()
        buyMore()
        deliveryInfo(view)
        cashOrder()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun billId(view: View) {
        val tvBillId = view.findViewById<TextView>(R.id.text_bill_id)
        val billDAO = BillDAO.getInstance(AppDatabase.getInstance(requireContext()))
        billId = billDAO?.getBillId()!!
        tvBillId.text = billId.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_order_detail_list)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        val listOrder = saleViewModel.listOrder.value
        recyclerView?.adapter = listOrder?.let { OrderDetailAdapter(it, saleViewModel) }
        saleViewModel.listOrder.observe(viewLifecycleOwner, Observer {
            recyclerView?.adapter?.notifyDataSetChanged()
        })
    }


    private fun customerInfo(view: View) {
        val imgClearCustomer = view.findViewById<ImageView>(R.id.image_clear)
        val tvCustomer = view.findViewById<TextView>(R.id.text_customer)
        val customer = saleViewModel.customer.value
        if (customer != null) {
            tvCustomer?.text =
                StringBuilder(customer.name).append(" (").append(customer.phone).append(")")
            imgClearCustomer.isVisible = true
            imgClearCustomer.setOnClickListener {
                imgClearCustomer.isVisible = false
                tvCustomer?.text = ""
                saleViewModel.setCustomer(null)
            }
        } else {
            imgClearCustomer.isVisible = false
            tvCustomer?.text = ""
            tvCustomer?.hint = getString(R.string.customer_name)
        }
        view.findViewById<TextView>(R.id.text_customer)?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_orderDetailFragment_to_customerListFragment)
        }
        view.findViewById<ImageView>(R.id.image_customer)?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_orderDetailFragment_to_customerListFragment)
        }
    }

    private fun showTotal() {
        saleViewModel.listOrder.observe(viewLifecycleOwner, Observer { list ->
            val totalAmount = list.sumOf { it.amount }
            val totalPrice = list.map { it.amount * it.price }.sum()
            view?.findViewById<TextView>(R.id.text_total_price)?.text = totalPrice.toString()
            view?.findViewById<TextView>(R.id.text_total_amount)?.text = totalAmount.toString()
        })
    }

    private fun buyMore() {
        view?.findViewById<View>(R.id.view_add_order)?.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun deliveryInfo(view: View) {
        view.findViewById<ImageButton>(R.id.button_delivery_info).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_orderDetailFragment_to_deliveryInfoFragment)
        }
    }


    @SuppressLint("NewApi")
    private fun cashOrder() {
        view?.findViewById<TextView>(R.id.text_total_amount)?.setOnClickListener {
            saveBill()
            showNote(requireContext(), "Thu tiền thành công")
            saleViewModel.setListOrder(mutableListOf())
            saleViewModel.setCustomer(null)
            activity?.onBackPressed()
        }
        view?.findViewById<Button>(R.id.button_cash)?.setOnClickListener {
            saveBill()
            showNote(requireContext(), "Thu tiền thành công")
            saleViewModel.setListOrder(mutableListOf())
            saleViewModel.setCustomer(null)
            activity?.onBackPressed()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun saveBill() {
        val billDAO = BillDAO.getInstance(AppDatabase.getInstance(requireContext()))
        val orderDAO = OrderDAO.getInstance(AppDatabase.getInstance(requireContext()))
        val customerDAO = CustomerDAO.getInstance(AppDatabase.getInstance(requireContext()))
        val customerId = saleViewModel.customer.value?.phone?.let { customerDAO?.getCustomerId(it) }
        for (order in saleViewModel.listOrder.value!!) {
            orderDAO?.addOrder(order, billId)
        }
        val date = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        ).format(Calendar.getInstance().time)
        val billModel = BillModel(billId, customerId, date, BillState.PAYED)
        billDAO?.addBill(billModel)
    }

    private fun backFragment() {
        view?.findViewById<ImageButton>(R.id.button_back)?.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}