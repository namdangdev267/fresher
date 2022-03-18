package kma.longhoang.beta.fragment.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.SaleViewModel
import kma.longhoang.beta.adapter.CustomerAdapter
import kma.longhoang.beta.model.CustomerModel

class CustomerListFragment : Fragment() {

    private var btnBack: ImageButton? = null
    private var edtSearch: EditText? = null
    private var imgAddCustomer: ImageView? = null
    private var recyclerCustomer: RecyclerView? = null
    private var listCustomer = mutableListOf<CustomerModel>()
    private val saleViewModel: SaleViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        listCustomer.addAll(
            listOf<CustomerModel>(
                CustomerModel("A Nam", "09123456778"),
                CustomerModel("Long", "0967538265"),
                CustomerModel("Long 2", "01234567899"),
                CustomerModel("Kiên", "0987654321"),
                CustomerModel("Bảo", "2351575589659"),
                CustomerModel("Chính", "09123576565"),
            )
        )
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