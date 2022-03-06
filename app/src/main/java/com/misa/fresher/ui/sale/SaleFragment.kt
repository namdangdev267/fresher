package com.misa.fresher.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.model.Product
import com.misa.fresher.ui.sale.adapter.ProductListAdapter
import com.misa.fresher.util.toCurrency

class SaleFragment : Fragment() {

    private val binding by lazy { FragmentSaleBinding.inflate(layoutInflater) }

    private var adapter: ProductListAdapter? = null

    private var bill = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding = FragmentSaleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateSelectedItem()
        setupToolbar()

        adapter = ProductListAdapter {
            bill.add(it)
            updateSelectedItem()
        }
        binding.btnRefresh.setOnClickListener {
            bill.clear()
            updateSelectedItem()
        }
        binding.btnBill.setOnClickListener {
            val bundle = bundleOf("bill" to bill)
            findNavController().navigate(R.id.action_sale_fragment_to_bill_fragment, bundle)
        }

        binding.rcvProduct.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        binding.rcvProduct.setHasFixedSize(true)
        binding.rcvProduct.adapter = adapter
        binding.rcvProduct.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        adapter?.updateProduct(fakeData())
    }

    private fun setupToolbar() {
        binding.tbSale.root.inflateMenu(R.menu.menu_sale)
        binding.tbSale.btnNav.setImageDrawable(resources.getDrawable(R.drawable.ic_menu, null))
        binding.tbSale.btnNav.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
    }

    private fun fakeData(): MutableList<Product> =
        mutableListOf(
            Product("ao", "a01", 1200000.0),
            Product("ao quanhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh", "a01", 120000.0, 500.0),
            Product("ao", "a01", 120000.0),
            Product("ao", "a01", 1020.0, 7000.0),
            Product("ao vay", "a01", 120.0),
            Product("ao", "a01", 000120.0),
            Product("ao", "a01", 120.0),
            Product("ao", "a01", 120.0),
            Product("ao", "a01", 120.0),
            Product("ao", "a01", 120.0),
            Product("ao", "a01", 0.0),
            Product("ao", "a01", 120.0),
            Product("ao", "a01", 120.0),
            Product("ao", "a01", 120.0),
            Product("ao", "a01", 827230.0),
            Product("ao", "a01", 189283.0, 8283623782.0),
        )

    private fun updateSelectedItem() {
        binding.tvCount.text = "${bill.size}"
        if (bill.size > 0) {
            binding.btnRefresh.isEnabled = true
            binding.btnBill.isEnabled = true

//            val total = bill.sumOf {it.price.toDouble() }
//            var total = 0f
//            bill.map {
//                total += it.price
//            }
            binding.tvInfo.text = "Tổng ${bill.sumOf { it.price }.toCurrency()}"
        } else {
            binding.btnRefresh.isEnabled = false
            binding.btnBill.isEnabled = false
            binding.tvInfo.text = "Chưa chọn hàng"
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_sale, menu)
//        super.onCreateOptionsMenu(menu,inflater);
//    }
}