package com.misa.fresher.ui.sale.bill

import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.misa.fresher.R
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.databinding.FragmentBillBinding
import com.misa.fresher.model.Product

class BillFragment : BaseFragment<FragmentBillBinding>() {

    override val getInflater: (inflater: LayoutInflater) -> FragmentBillBinding =
        FragmentBillBinding::inflate

    private val bill: MutableList<Product> by lazy { arguments?.get("bill") as MutableList<Product> }

    override fun initUI() {
        setupToolbar()

        binding.tvCount.text = "${bill.size}"
        binding.btnShipping.setOnClickListener {
            findNavController().navigate(R.id.action_bill_fragment_to_delivery_info_fragment)
        }
    }

    private fun setupToolbar() {
        binding.tbBill.root.inflateMenu(R.menu.menu_bill)
        binding.tbBill.tvTitle.text = "220193755"
        binding.tbBill.btnNav.setImageDrawable(resources.getDrawable(R.drawable.ic_arrow_back, null))
        binding.tbBill.btnNav.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}