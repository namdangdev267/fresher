package com.misa.fresher.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.model.Product
import com.misa.fresher.ui.sale.adapter.ProductListAdapter
import com.misa.fresher.util.toCurrency

class SaleFragment : Fragment() {

    private var binding: FragmentSaleBinding? = null

    private var adapter: ProductListAdapter? = null

    private var bill = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaleBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateSelectedItem()

        adapter = ProductListAdapter {
            bill.add(it)
            updateSelectedItem()
        }
        binding!!.btnRefresh.setOnClickListener {
            bill.clear()
            updateSelectedItem()
        }
        binding!!.btnBill.setOnClickListener {
            val bundle = bundleOf("bill" to bill)
            findNavController().navigate(R.id.action_sale_fragment_to_bill_fragment, bundle)
        }

        binding!!.rcvProduct.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        rv.setHasFixedSize(true)
        binding!!.rcvProduct.adapter = adapter
        binding!!.rcvProduct.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        adapter?.updateProduct(fakeData())
    }

    private fun fakeData(): MutableList<Product> {
        return mutableListOf<Product>(
            Product("ao", "a01", 1200000f),
            Product("ao quan", "a01", 120000f, 500f),
            Product("ao", "a01", 120000f),
            Product("ao", "a01", 1020f, 7000f),
            Product("ao vay", "a01", 120f),
            Product("ao", "a01", 000120f),
            Product("ao", "a01", 120f),
            Product("ao", "a01", 120f),
            Product("ao", "a01", 120f),
            Product("ao", "a01", 120f),
            Product("ao", "a01", 0f),
            Product("ao", "a01", 120f),
            Product("ao", "a01", 120f),
            Product("ao", "a01", 120f),
            Product("ao", "a01", 827230f),
            Product("ao", "a01", 189283f, 8283623782f),
        )
    }

    private fun updateSelectedItem() {
        binding!!.tvCount.text = "${bill.size}"
        if (bill.size > 0) {
            binding!!.btnRefresh.isEnabled = true
            binding!!.btnBill.isEnabled = true

            var total = 0f
            bill.map {
                total += it.price
            }
            binding!!.tvInfo.text = "Tổng ${total.toCurrency()}"
        } else {
            binding!!.btnRefresh.isEnabled = false
            binding!!.btnBill.isEnabled = false
            binding!!.tvInfo.text = "Chưa chọn hàng"
        }
    }
}