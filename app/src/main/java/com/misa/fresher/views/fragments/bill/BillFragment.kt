package com.misa.fresher.views.fragments.bill

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.views.activities.MainActivity
import com.misa.fresher.views.fragments.SharedViewModel
import com.misa.fresher.databinding.FragmentBillBinding


class BillFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    val binding:FragmentBillBinding by lazy {
        getInflater(layoutInflater)
    }

    val getInflater: (LayoutInflater) -> FragmentBillBinding
        get() = FragmentBillBinding::inflate


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transitionFragment(view)
        configListView()
        configToolbar()
        configOtherView()
    }

    private fun configOtherView() {
        binding.tvBillTotalQuantity.text = sharedViewModel.listBill.value?.size.toString()

        binding.tvBillTotalPrice.text  = sharedViewModel.getTotalPriceListBill().toString()
    }

    private fun configToolbar() {
        binding.imBillMenu.setOnClickListener {
            (activity as MainActivity).toggleDrawer((activity as MainActivity).binding.nvMenu)
        }
    }

    private fun transitionFragment(view: View) {
        binding.ivBillBuyMore.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_billFragment_to_saleFragment)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun configListView() {
        binding.recyclerviewBillList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewBillList.adapter = sharedViewModel.listBill.value?.let { BillAdapter(it) }

        sharedViewModel.listBill.observe(viewLifecycleOwner, Observer {
            binding.recyclerviewBillList.adapter?.notifyDataSetChanged()
        })
    }


}