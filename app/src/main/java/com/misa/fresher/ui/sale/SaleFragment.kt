package com.misa.fresher.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.Product
import com.misa.fresher.ui.sale.adapter.ProductListAdapter
import com.misa.fresher.util.toCurrency

class SaleFragment: Fragment() {

    private lateinit var rv: RecyclerView
    private lateinit var btnRefresh: AppCompatImageButton
    private lateinit var tvCount: TextView
    private lateinit var tvInfo: TextView
    private lateinit var btnInfo: LinearLayout

    private lateinit var adapter: ProductListAdapter

    private var itemSelectedCount = 0
    private var total = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById(R.id.rv)
        btnRefresh = view.findViewById(R.id.btn_refresh)
        tvCount = view.findViewById(R.id.tv_count)
        tvInfo = view.findViewById(R.id.tv_info)
        btnInfo = view.findViewById(R.id.btn_info)

        updateSelectedItem()

        adapter = object: ProductListAdapter() {
            override fun onItemClick(product: Product) {
                itemSelectedCount++
                total += product.price
                updateSelectedItem()
            }
        }

        btnRefresh.setOnClickListener {
            itemSelectedCount = 0
            total = 0f
            updateSelectedItem()
        }

        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)
        rv.adapter = adapter

        adapter.updateProduct(fakeData())
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
        tvCount.text = "$itemSelectedCount"
        if (itemSelectedCount > 0) {
            btnRefresh.isEnabled = true
            btnInfo.isEnabled = true
            tvInfo.text = "Tổng ${total.toCurrency()}"
        } else {
            btnRefresh.isEnabled = false
            btnInfo.isEnabled = false
            tvInfo.text = "Chưa chọn hàng"
        }
    }
}