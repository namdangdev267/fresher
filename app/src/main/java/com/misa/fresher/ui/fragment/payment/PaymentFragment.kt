package com.misa.fresher.ui.fragment.payment

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
import com.misa.fresher.data.models.PackageProduct
import com.misa.fresher.databinding.FragmentPaymentBinding
import com.misa.fresher.showToast
import kotlinx.android.synthetic.main.payment_context.view.*

class PaymentFragment: Fragment() {
    private val binding: FragmentPaymentBinding by lazy { getInflater(layoutInflater) }
    private val sharedViewModel: PublicViewModel by activityViewModels()
    private var adapter: PaymentAdapter? = null

    val getInflater: (LayoutInflater) -> FragmentPaymentBinding get() = FragmentPaymentBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transitionFragment()
        configToolbar()
        configureListView()
        configureOtherView()

//        activity?.onBackPressedDispatcher?.addCallback(
//            viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    activity?.onBackPressed()
//                }
//            })
    }

    @SuppressLint("SetTextI18n")
    private fun configureOtherView() {
        val tvCustomer = binding.root.tvCustomer
        tvCustomer.isSelected = true
        sharedViewModel.inforShip.observe(viewLifecycleOwner) {
            if (it.receiver != null && it.tel != null) {
                tvCustomer.text = it.receiver.toString() + " _ " + it.tel.toString()
            } else {
                tvCustomer.text = "Customer name, phone number"
            }
        }
    }

    private fun configToolbar() {
        binding.tvCodePayment.text = sharedViewModel.billHandling.value?.id
    }

    private fun transitionFragment() {
        binding.root.linearQuantity?.setOnClickListener {
            sharedViewModel.addBillToListBill(requireContext())
            requireContext().showToast("Paid Successfully")
            activity?.onBackPressed()
        }

        binding.imgBackMain.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.root.btnBuyMore?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.root.btnGoToShipFragment?.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_payment_to_fragment_ship_information)
        }
    }

    private fun configureListView() {
        binding.root.rcvPackage?.layoutManager = LinearLayoutManager(this.context)
        adapter = PaymentAdapter(mutableListOf()) { clickItemBillDetail(it) }
        binding.root.rcvPackage.adapter = adapter

        sharedViewModel.listItemSelected.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
            binding.root.tvCountPackage.text =
                sharedViewModel.getCount().toString()
            binding.root.tvMoneyReceivable.text = sharedViewModel.getTotalPrice().toString()

        }
    }

    private fun clickItemBillDetail(itemBillDetail: PackageProduct) {
        sharedViewModel.updateQuantityOfPackageProduct(itemBillDetail)
    }

}
