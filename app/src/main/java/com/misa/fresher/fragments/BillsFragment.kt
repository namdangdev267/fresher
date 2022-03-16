package com.misa.fresher.fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.adapters.BillsAdapter
import com.misa.fresher.viewModel.BillsViewModel

class BillsFragment : Fragment() {
    var viewModel: BillsViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity()).get(BillsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_bills, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configToolbar(view)
        configRecyclerView(view)
        configSpinner(view)
    }

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

    private fun configRecyclerView(view: View) {
        val rcv = view.findViewById<RecyclerView>(R.id.rcvListBills)
        val total = view.findViewById<TextView>(R.id.tvTotal)
        val totalPrice = view.findViewById<TextView>(R.id.tvTotalPriceBills)
        rcv.layoutManager = LinearLayoutManager(view.context)
        viewModel?.listBill?.observe(viewLifecycleOwner, Observer {
            total.text = it.size.toString()
            val adapter = BillsAdapter(it)
            rcv.adapter = adapter
        })
        totalPrice.text = viewModel?.getTotalPrice().toString()
    }

    private fun configToolbar(view: View) {
        val dlBills = (activity as MainActivity).findViewById<DrawerLayout>(R.id.drawerLayout)
        view.findViewById<ImageView>(R.id.btnMenuBills).setOnClickListener {
            dlBills.openDrawer(Gravity.LEFT)
        }
    }
}