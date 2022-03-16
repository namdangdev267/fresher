package kma.longhoang.beta.fragment.delivery

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.SharedViewModel
import kma.longhoang.beta.adapter.OrderDetailAdapter
import kma.longhoang.beta.adapter.ProductAdapter
import kma.longhoang.beta.model.FilterProduct
import kma.longhoang.beta.model.OrderModel
import kma.longhoang.beta.model.ProductModel

class OrderDetailFragment : Fragment() {

    private val orderViewModel: SharedViewModel by activityViewModels()
    private var listOrder = mutableListOf<OrderModel>()
    private var recyclerView: RecyclerView? = null
    private var tvTotalAmount: TextView?= null
    private var tvTotalPrice: TextView?= null
    private var btnCash: Button?= null
    private var btnBack: ImageButton?= null
    private var btnDeliveryInfo: ImageButton?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupRecyclerView()
        
    }

    private fun initView() {
        recyclerView = view?.findViewById(R.id.recycler_order_detail_list)
        tvTotalAmount = view?.findViewById(R.id.text_total_amount)
        tvTotalPrice = view?.findViewById(R.id.text_total_price)
        btnBack = view?.findViewById(R.id.button_back)
        btnCash = view?.findViewById(R.id.button_cash)
        btnDeliveryInfo = view?.findViewById(R.id.button_delivery_info)
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
        orderViewModel.listOrder.observe(viewLifecycleOwner, Observer { it ->
            recyclerView?.adapter = OrderDetailAdapter(it, orderViewModel)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }
}