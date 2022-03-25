package kma.longhoang.beta.fragment.customer

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.SaleViewModel
import kma.longhoang.beta.adapter.CustomerAdapter
import kma.longhoang.beta.dao.CustomerDAO
import kma.longhoang.beta.database.AppDatabase
import kma.longhoang.beta.model.CustomerModel

class CustomerListFragment : Fragment() {

    private var btnBack: ImageButton? = null
    private var edtSearch: EditText? = null
    private var imgAddCustomer: ImageView? = null
    private var recyclerCustomer: RecyclerView? = null
    private var listCustomer = mutableListOf<CustomerModel>()
    private val saleViewModel: SaleViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val customerDAO = CustomerDAO.getInstance(AppDatabase.getInstance(requireContext()))
        listCustomer = customerDAO?.getAllCustomer()!!
        initView(view)
        setupRecyclerView()
        backFragment()
    }

    private fun initView(view: View) {
        btnBack = view.findViewById(R.id.button_back)
        edtSearch = view.findViewById(R.id.edt_search_customer)
        imgAddCustomer = view.findViewById(R.id.img_add_customer)
        recyclerCustomer = view.findViewById(R.id.recycler_customer_list)
    }

    private fun setupRecyclerView() {
        recyclerCustomer?.layoutManager = LinearLayoutManager(context)
        recyclerCustomer?.setHasFixedSize(true)
        recyclerCustomer?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerCustomer?.adapter = CustomerAdapter(listCustomer) { customerItemClick(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_list, container, false)
    }

    private fun customerItemClick(customerModel: CustomerModel) {
        saleViewModel.setCustomer(customerModel)
        activity?.onBackPressed()
    }

    private fun backFragment() {
        btnBack?.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}