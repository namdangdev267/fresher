package com.misa.fresher.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.model.Product
import com.misa.fresher.ui.sale.adapter.ProductListAdapter
import com.misa.fresher.util.toCurrency

class SaleFragment: Fragment() {

    private var binding: FragmentSaleBinding? = null
    private var adapter: ProductListAdapter? = null

    private var itemSelectedCount = 0
    private var total = 0f

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
            itemSelectedCount++
            total += it.price
            updateSelectedItem()
        }
        binding!!.btnRefresh.setOnClickListener {
            itemSelectedCount = 0
            total = 0f
            updateSelectedItem()
        }

        binding!!.rvProduct.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        rv.setHasFixedSize(true)
        binding!!.rvProduct.adapter = adapter
        binding!!.rvProduct.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

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
        binding!!.tvCount.text = "$itemSelectedCount"
        if (itemSelectedCount > 0) {
            binding!!.btnRefresh.isEnabled = true
            binding!!.btnInfo.isEnabled = true
            binding!!.tvInfo.text = "Tổng ${total.toCurrency()}"
        } else {
            binding!!.btnRefresh.isEnabled = false
            binding!!.btnInfo.isEnabled = false
            binding!!.tvInfo.text = "Chưa chọn hàng"
        }
    }
}