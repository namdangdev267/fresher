package kma.longhoang.beta.fragment.delivery

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.MainActivity
import kma.longhoang.beta.R
import kma.longhoang.beta.SharedViewModel
import kma.longhoang.beta.adapter.OrderDetailAdapter
import kma.longhoang.beta.adapter.ProductAdapter
import kma.longhoang.beta.fragment.customer.CustomerListFragment
import kma.longhoang.beta.fragment.main.DeliveryListFragment
import kma.longhoang.beta.fragment.main.SaleFragment
import kma.longhoang.beta.model.CustomerModel
import kma.longhoang.beta.model.FilterProduct
import kma.longhoang.beta.model.OrderModel
import kma.longhoang.beta.model.ProductModel

class OrderDetailFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var recyclerView: RecyclerView? = null
    private var tvTotalAmount: TextView? = null
    private var tvTotalPrice: TextView? = null
    private var btnCash: Button? = null
    private var btnBack: ImageButton? = null
    private var btnDeliveryInfo: ImageButton? = null
    private var addOrder: View? = null
    private var tvCustomer: TextView? = null
    private var imgCustomer: ImageView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        setupRecyclerView()
        customerInfo()
        backFragment()
        showTotal()
        buyMore()
        deliveryInfo()
        cashOrder()
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_order_detail_list)
        tvTotalAmount = view.findViewById(R.id.text_total_amount)
        tvTotalPrice = view.findViewById(R.id.text_total_price)
        btnBack = view.findViewById(R.id.button_back)
        btnCash = view.findViewById(R.id.button_cash)
        btnDeliveryInfo = view.findViewById(R.id.button_delivery_info)
        addOrder = view.findViewById(R.id.view_add_order)
        tvCustomer = view.findViewById(R.id.text_customer)
        imgCustomer = view.findViewById(R.id.image_customer)
    }

    private fun setupRecyclerView() {
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        sharedViewModel.listOrder.observe(viewLifecycleOwner, Observer { it ->
            recyclerView?.adapter = OrderDetailAdapter(it, sharedViewModel)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }

    private fun customerInfo() {
        sharedViewModel.customer.observe(viewLifecycleOwner, Observer {
            tvCustomer?.text = StringBuilder(it.name).append(" (").append(it.phone).append(")")
        })
        tvCustomer?.setOnClickListener {
            (activity as MainActivity).backStackReplaceFragment(CustomerListFragment())
        }
        imgCustomer?.setOnClickListener {
            (activity as MainActivity).backStackReplaceFragment(CustomerListFragment())
        }
    }

    private fun showTotal() {
        sharedViewModel.listOrder.observe(viewLifecycleOwner, Observer {
            var totalPrice = 0.0
            var totalAmount = 0
            for (i in 0 until it.size) {
                totalPrice += (it[i].amount * it[i].price)
                totalAmount += it[i].amount
            }
            tvTotalPrice?.text = totalPrice.toString()
            tvTotalAmount?.text = totalAmount.toString()
        })
    }

    private fun buyMore() {
        addOrder?.setOnClickListener {
            (activity as MainActivity).replaceFragment(SaleFragment())
        }
    }

    private fun deliveryInfo() {
        btnDeliveryInfo?.setOnClickListener {
            (activity as MainActivity).backStackReplaceFragment(DeliveryInfoFragment())
        }
    }

    private fun cashOrder() {
        tvTotalAmount?.setOnClickListener {
            Toast.makeText(context, "Thu tiền thành công", Toast.LENGTH_SHORT).show()
            sharedViewModel.setListOrder(mutableListOf())
            (activity as MainActivity).replaceFragment(SaleFragment())
        }
        btnCash?.setOnClickListener {
            Toast.makeText(context, "Thu tiền thành công", Toast.LENGTH_SHORT).show()
            sharedViewModel.setListOrder(mutableListOf())
            (activity as MainActivity).replaceFragment(SaleFragment())
        }
    }

    private fun backFragment() {
        btnBack?.setOnClickListener {
            (activity as MainActivity).replaceFragment(SaleFragment())
        }
    }
}