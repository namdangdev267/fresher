package com.misa.fresher.fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapters.BillsAdapter
import com.misa.fresher.model.Bill
import com.misa.fresher.viewModel.BillsViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Màn ListBill
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class BillsFragment : Fragment() {
    private val viewModel: BillsViewModel by activityViewModels()
    private var adapter = BillsAdapter(mutableListOf())
    private var rcv : RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bills, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configToolbar(view)
        configRecyclerView(view)
        configSpinner(view)
        navigationEvent(view)
    }
    /**
    * Chuyển màn hình
    * @Auther : NTBao
    * @date : 3/18/2022
    **/
    private fun navigationEvent(view: View) {
        view.findViewById<FloatingActionButton>(R.id.flbCart).setOnClickListener {
            findNavController().navigate(R.id.action_nav_bills_to_nav_sale)
        }
    }

    /**
     * Set giá trị cho spinner
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configSpinner(view: View) {
        val spnDay = view.findViewById<Spinner>(R.id.spnDay)
        val spnPayStatus = view.findViewById<Spinner>(R.id.spnPayStatus)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.DayArray,
            android.R.layout.simple_spinner_item

        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnDay.adapter = arrayAdapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.payStatusArray,
            android.R.layout.simple_spinner_item

        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnPayStatus.adapter = arrayAdapter
        }
    }

    /**
     * Config List bills recycler view
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configRecyclerView(view: View) {
        rcv = view.findViewById<RecyclerView>(R.id.rcvListBills)
        val total = view.findViewById<TextView>(R.id.tvTotal)
        val totalPrice = view.findViewById<TextView>(R.id.tvTotalPriceBills)
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(view.context)
        viewModel.listBill.observe(viewLifecycleOwner, Observer {
            adapter.mBills = it
            total.text = it.size.toString()
        })
        adapter.notifyDataSetChanged()
        totalPrice.text = viewModel.getTotalPrice().toString()
    }

    /**
     * Set sự kiện khi click vào btn menu -> mở drawer
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configToolbar(view: View) {
        val dlBills = (activity as MainActivity).findViewById<DrawerLayout>(R.id.drawerLayout)
        view.findViewById<ImageView>(R.id.btnMenuBills).setOnClickListener {
            dlBills.openDrawer(Gravity.LEFT)
        }
    }
}