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

    private var binding: FragmentBillBinding? = null

    private val bill: MutableList<Product> by lazy { arguments?.get("bill") as MutableList<Product> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBillBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.tvCount.text = "${bill.size}"
        binding!!.btnShipping.setOnClickListener {
            findNavController().navigate(R.id.action_bill_fragment_to_delivery_info_fragment)
        }
    }
}