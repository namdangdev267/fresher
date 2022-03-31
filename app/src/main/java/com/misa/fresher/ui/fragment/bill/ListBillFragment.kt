package com.misa.fresher.ui.fragment.bill

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.PublicViewModel
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentListBillBinding
import com.misa.fresher.fragment.bill.ListBillAdapter
import com.misa.fresher.ui.activity.MainActivity

class ListBillFragment : Fragment() {
    private val binding: FragmentListBillBinding by lazy {
        getInflater(layoutInflater)
    }

    private val sharedViewModel: PublicViewModel by activityViewModels()
    private var adapter: ListBillAdapter? = null
    val getInflater: (LayoutInflater) -> FragmentListBillBinding
        get() = FragmentListBillBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transitionFragment()
        configureListView()
        configureToolbar()
        configureOtherView()
    }

    private fun configureOtherView() {
        binding.tvBillTotalQuantity.text = sharedViewModel.listBill.value?.size.toString()
        binding.tvBillTotalPrice.text = sharedViewModel.getTotalPriceListBill().toString()
    }

    private fun configureToolbar() {
        binding.imgBackMain.setOnClickListener {
            (activity as MainActivity).toggleDrawer((activity as MainActivity).binding.navSaleFragment)
        }
    }

    private fun transitionFragment() {
        binding.rlBillBuyMore.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_list_bill_to_fragment_sale)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun configureListView() {
        binding.recyclerviewBillList.layoutManager = LinearLayoutManager(requireContext())
        adapter = ListBillAdapter()
        binding.recyclerviewBillList.adapter = adapter
        sharedViewModel.listBill.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }
    }

}