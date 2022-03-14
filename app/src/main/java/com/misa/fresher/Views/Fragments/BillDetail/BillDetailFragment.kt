package com.misa.fresher.Views.Fragments.BillDetail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.Models.ItemBillDetail
import com.misa.fresher.R
import com.misa.fresher.Views.CustomViews.CustomToast
import com.misa.fresher.Views.Fragments.Sale.SaleAdapter
import com.misa.fresher.Views.Fragments.Sale.SaleViewModel
import com.misa.fresher.Views.Fragments.SharedViewModel
import com.misa.fresher.databinding.FragmentBillDetailBinding
import com.misa.fresher.databinding.FragmentSaleBinding

class BillDetailFragment : Fragment() {

    lateinit var binding: FragmentBillDetailBinding
    lateinit var sharedViewModel: SharedViewModel
    lateinit var billDetailViewModel: BillDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBillDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    private fun initViewModel() {
        billDetailViewModel = BillDetailViewModel()
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.imBillDetailBack.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_billDetailFragment_to_saleFragment)
        }

        binding.ivBillDetailShip.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_billDetailFragment_to_shippingInformationFragment)
        }

        binding.tvBuyMore.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_billDetailFragment_to_saleFragment)
        }

        binding.recyclerviewBillDetail.layoutManager = LinearLayoutManager(this.context)

        sharedViewModel.listItemSelected.observe(viewLifecycleOwner, Observer {
            binding.recyclerviewBillDetail.adapter = BillDetailAdapter(it,{it->clickItemBillDetail(it)} )
            binding.tvBillDetailTotalQuantity.text = sharedViewModel.listItemSelected.value?.size.toString()
            binding.tvBillDetailTotalPrice.text = sharedViewModel.getTotalPrice().toString()
        })


    }

    fun clickItemBillDetail(itemBillDetail: ItemBillDetail)
    {
        if(itemBillDetail.quantity==1)
        {
            var customToast = CustomToast(requireContext())
            customToast.makeText(requireContext(),"Quantity must be more than 0. Please check again",
                Toast.LENGTH_SHORT).show()
        }
        sharedViewModel.updateQuantityOfItemBillDetail(itemBillDetail)

    }

}