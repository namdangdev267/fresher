package com.misa.fresher.Fragment.Payment

import android.annotation.SuppressLint
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
import com.misa.fresher.CustomView.ToastCustom
import com.misa.fresher.Models.PackageProduct
import com.misa.fresher.PublicViewModel
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentPaymentBinding
import kotlinx.android.synthetic.main.payment_context.view.*

class PaymentFragment: Fragment() {
    private var binding: FragmentPaymentBinding? = null
    private var sharedViewModel: PublicViewModel? = null
    private var paymentViewModel: PaymentViewModel? = null

    private val chcek: FragmentPaymentBinding by lazy { getInflater(layoutInflater) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    val getInflater: (LayoutInflater) -> FragmentPaymentBinding get() = FragmentPaymentBinding::inflate

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
        configureOtherView(view)
    }

    @SuppressLint("SetTextI18n")
    private fun configureOtherView(view: View) {
        sharedViewModel?.inforShip?.observe(viewLifecycleOwner, Observer {
            if (it.receiver != null && it.tel != null) {
                binding!!.root.etCustomer.text = it.receiver.toString() + " _ " + it.tel.toString()
            }
        })
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

            ToastCustom.makeToast(requireContext(), "Paid Successfully", Toast.LENGTH_LONG).show()

//            findNavController().navigate(R.id.action_fragment_payment_to_fragment_list_bill)

            activity?.onBackPressed()
        }
    }

    private fun configureListView() {
        binding?.root?.rcvPackage?.layoutManager = LinearLayoutManager(this.context)

        sharedViewModel?.listItemSelected?.observe(viewLifecycleOwner, Observer { it ->
            binding?.root?.rcvPackage?.adapter =
                PaymentAdapter(it) { clickItemBillDetail(it) }
            binding!!.root.tvCountPackage.text =
                sharedViewModel!!.getCount().toString()
            binding!!.root.tvMoneyReceivable.text = sharedViewModel!!.getTotalPrice().toString()
        })
    }

    private fun clickItemBillDetail(itemBillDetail: PackageProduct) {
        sharedViewModel?.updateQuantityOfItemBillDetail(itemBillDetail)
    }

}
