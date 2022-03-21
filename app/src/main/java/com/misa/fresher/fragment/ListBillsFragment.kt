package com.misa.fresher.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.BillViewModel
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapter.ListBillAdapter
import com.misa.fresher.model.BillInfor

class ListBillsFragment : Fragment() {
    var viewModel: BillViewModel? = null
    var mListBill= arrayListOf<BillInfor>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list_bills, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BillViewModel::class.java)
        setUpView(view)
        mListBill = getListBill()
        setUpRecycleView(view)
        openDrawerLayoutMenu(view)
        showSaleFragment()
    }
    /**
     *Thiết lập dữ liệu cho Spinner
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun setUpView(view: View) {
        val spnDay = view.findViewById<Spinner>(R.id.spnDay)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spnDay,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnDay?.adapter = adapter
        }
        val spnPayment = view.findViewById<Spinner>(R.id.spnPayment)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spnPayment,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnPayment?.adapter = adapter
        }
        val tvListBillSize = view.findViewById<TextView>(R.id.tvListBillSize)
        tvListBillSize.text = viewModel?.getSize().toString()
        val tvListBillAmount = view.findViewById<TextView>(R.id.tvListBillAmount)
        tvListBillAmount.text = viewModel?.calculateTotalAmount().toString()
    }
    /**
     *Thiết lập nạp dữ liệu vào RecycleView
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun setUpRecycleView(view: View) {
        val rcvBill = view.findViewById<RecyclerView>(R.id.rcvBill)
        val adapter = ListBillAdapter(mListBill)
        rcvBill.adapter = adapter
        rcvBill.layoutManager = LinearLayoutManager(requireContext())
    }
    /**
     *Mở Drawer
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun openDrawerLayoutMenu(view: View) {
        val ibMenu = view.findViewById<ImageButton>(R.id.ibMenuLB)
        ibMenu?.setOnClickListener {
            (activity as MainActivity).openDrawerLayout()
        }
    }
    /**
     *Hiển thị SaleFragment
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun showSaleFragment()
    {
        val navigationView = (activity as MainActivity).findViewById<NavigationView>(R.id.nvMenu)
        val drawerLayout = (activity as MainActivity).findViewById<DrawerLayout>(R.id.dlLeft)
        navigationView?.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mnSale -> {
                    activity?.onBackPressed()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
    }

    /**
     *Lấy danh sách hóa đơn
     *@author:NCPhuc
     *@date:3/18/2022
     **/
    private fun getListBill(): ArrayList<BillInfor> {
        return arguments?.get(BILL) as ArrayList<BillInfor>
    }
    companion object{
        const val BILL="bill"
    }
}