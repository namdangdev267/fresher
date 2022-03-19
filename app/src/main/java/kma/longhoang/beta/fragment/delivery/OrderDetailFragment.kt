package kma.longhoang.beta.fragment.delivery

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
import kma.longhoang.beta.model.BillModel
import kma.longhoang.beta.model.CustomerModel
import kma.longhoang.beta.model.OrderModel
import java.time.Instant
import java.util.*

class OrderDetailFragment : Fragment() {

    private val saleViewModel: SaleViewModel by activityViewModels()
    private var orderList : MutableList<OrderModel>?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        customerInfo(view)
        backFragment()
        showTotal()
        buyMore()
        deliveryInfo(view)
        cashOrder()
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
        saleViewModel.listOrder.observe(viewLifecycleOwner, Observer { it->
            recyclerView?.adapter = OrderDetailAdapter(it, saleViewModel)
        })
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }

    private fun customerInfo(view: View) {
        saleViewModel.customer.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.text_customer)?.text =
                StringBuilder(it.name).append(" (").append(it.phone).append(")")
        })
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
            Toast.makeText(context, "Thu tiền thành công", Toast.LENGTH_SHORT).show()
            val billModel = BillModel(saleViewModel.listOrder.value, 1, saleViewModel.customer.value)
            saleViewModel.addBill(billModel)
            saleViewModel.setListOrder(mutableListOf())
            activity?.onBackPressed()
        }
        view?.findViewById<Button>(R.id.button_cash)?.setOnClickListener {
            Toast.makeText(context, "Thu tiền thành công", Toast.LENGTH_SHORT).show()
            val billModel = BillModel(saleViewModel.listOrder.value, 1, saleViewModel.customer.value)
            saleViewModel.addBill(billModel)
            saleViewModel.setListOrder(mutableListOf())
            activity?.onBackPressed()
        }
    }

    private fun backFragment() {
        view?.findViewById<ImageButton>(R.id.button_back)?.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}