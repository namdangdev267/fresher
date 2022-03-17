package com.misa.fresher.Fragment.Payment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.Models.PackageProduct
import com.misa.fresher.PublicViewModel
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentPaymentBinding

class PaymentFragment: Fragment() {
    lateinit var binding: FragmentPaymentBinding
    lateinit var sharedViewModel: PublicViewModel
    lateinit var paymentViewModel: PaymentViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initViewModel() {
        paymentViewModel = PaymentViewModel()
        sharedViewModel = ViewModelProvider(requireActivity()).get(PublicViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transitionFragment(view)
        configToolbar()
        configureListView()
        configureGetPayment()
    }

    private fun configureGetPayment() {
        binding.linearQuantity.setOnClickListener {

        }
    }

    private fun configToolbar() {
        binding.tvCodePayment.text = sharedViewModel.billHandling.value?.id
    }

    private fun transitionFragment(view: View) {
        binding.imgBackMain.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.btnBuyMore.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.btnGoToShipFragment.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_payment_to_fragment_ship)
        }

        binding.linearQuantity.setOnClickListener {
            sharedViewModel.addBillToListBill()
        }
    }

    private fun configureListView() {
        binding.rcvPackage.layoutManager = LinearLayoutManager(this.context)

        sharedViewModel.listItemSelected.observe(viewLifecycleOwner, Observer {
            binding.rcvPackage.adapter =
                PaymentAdapter(it, { it -> clickItemBillDetail(it) })
            binding.tvCountPackage.text =
                sharedViewModel.listItemSelected.value?.size.toString()
            binding.tvMoneyReceivable.text = sharedViewModel.getTotalPrice().toString()
        })
    }

    fun clickItemBillDetail(itemBillDetail: PackageProduct) {
        if (itemBillDetail.countPackage == 1) {
            Toast.makeText(requireContext(), "Quantity must be more than 0. Please check again",
                Toast.LENGTH_SHORT
            ).show()
        }
        sharedViewModel.updateQuantityOfItemBillDetail(itemBillDetail)

    }

}