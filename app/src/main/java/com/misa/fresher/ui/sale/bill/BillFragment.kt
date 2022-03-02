package com.misa.fresher.ui.sale.bill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentBillBinding
import com.misa.fresher.model.Product

class BillFragment : Fragment() {

    private var _binding: FragmentBillBinding? = null
    // Chỉ sử dụng ở giữa onCreateView và onDestroyView
    private val binding get() = _binding!!

    private val bill: MutableList<Product> by lazy { arguments?.get("bill") as MutableList<Product> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}