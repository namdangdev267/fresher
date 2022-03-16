package com.misa.fresher.Views.Fragments.Bill

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.Views.Activities.MainActivity
import com.misa.fresher.Views.Fragments.SharedViewModel
import com.misa.fresher.databinding.FragmentBillBinding
import com.misa.fresher.databinding.FragmentBillDetailBinding


class BillFragment : Fragment() {

    lateinit var sharedViewModel: SharedViewModel
    lateinit var binding: FragmentBillBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBillBinding.inflate(inflater, container, false)
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

    private fun configListView() {
        binding.recyclerviewBillList.layoutManager = LinearLayoutManager(requireContext())
        sharedViewModel.listBill.observe(viewLifecycleOwner, Observer {
            binding.recyclerviewBillList.adapter = BillAdapter(it)

        })
    }


}