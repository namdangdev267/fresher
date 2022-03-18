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
import kotlinx.android.synthetic.main.payment_context.view.*

class PaymentFragment: Fragment() {
    private var binding: FragmentPaymentBinding? = null
    private var sharedViewModel: PublicViewModel? = null
    private var paymentViewModel: PaymentViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding!!.root
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
    }

    private fun configToolbar() {
        binding!!.tvCodePayment.text = sharedViewModel!!.billHandling.value?.id
    }

    private fun transitionFragment(view: View) {
        binding?.imgBackMain?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding?.root?.btnBuyMore?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding?.root?.btnGoToShipFragment?.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_payment_to_fragment_ship)
        }

        binding?.root?.linearQuantity?.setOnClickListener {
            sharedViewModel?.addBillToListBill()
        }
    }

    private fun configureListView() {
        binding?.root?.rcvPackage?.layoutManager = LinearLayoutManager(this.context)

        sharedViewModel?.listItemSelected?.observe(viewLifecycleOwner, Observer {
            binding?.root?.rcvPackage?.adapter =
                PaymentAdapter(it) { it -> clickItemBillDetail(it) }
            binding!!.root.tvCountPackage.text =
                sharedViewModel!!.getCount().toString()
            binding!!.root.tvMoneyReceivable.text = sharedViewModel!!.getTotalPrice().toString()
        })
    }

    private fun clickItemBillDetail(itemBillDetail: PackageProduct) {
        if (itemBillDetail.countPackage < 1) {
            Toast.makeText(requireContext(), "Quantity must be more than 0. Please check again",
                Toast.LENGTH_SHORT
            ).show()
        }
        sharedViewModel?.updateQuantityOfItemBillDetail(itemBillDetail)
    }

}
