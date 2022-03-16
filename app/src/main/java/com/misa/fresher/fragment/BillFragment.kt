package com.misa.fresher.fragment

import android.view.Gravity
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.adapter.SaleProductAdapter
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.FragmentBillBinding
import com.misa.fresher.models.product.Product

class BillFragment : BaseFragment<FragmentBillBinding>(FragmentBillBinding::inflate) {
    private var selectedItems = arrayListOf<Product>()

    override fun initUI() {
        initProductItems()
        initListProductRecViewUI()
    }

    override fun initListener() {
        super.initListener()
        initToolbarListener()
    }

    fun initToolbarListener() {
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
    }

    fun initProductItems() {
        selectedItems = try { arguments?.get("selected_items") as ArrayList<Product> }
        catch (e: Exception) { arrayListOf() }
    }

    private fun initListProductRecViewUI() {
        binding.listProductRecView.adapter = SaleProductAdapter(selectedItems) {_, _ ->}
        binding.listProductRecView.layoutManager = LinearLayoutManager(context)
        binding.listProductRecView.post {
            selectedItems.forEachIndexed { pos, product ->
                (binding.listProductRecView.findViewHolderForAdapterPosition(pos) as SaleProductAdapter.ProductItemViewHolder).itemSaleProductView.run {
                    binding.btnAdd.setOnClickListener { amount = ++product.items[0].amount }
                    binding.btnMinus.setOnClickListener {
                        if(product.items[0].amount == 1) {
                            val toast = Toast.makeText(
                                context, "Quantity must be more than 0. Please check again.", Toast.LENGTH_LONG
                            )
                            toast.setGravity(Gravity.TOP, 0, 120)
                            toast.show()
                        } else amount = --product.items[0].amount
                    }
                }
            }
        }
    }

    private fun removeItem (pos: Int) {
        selectedItems.removeAt(pos)
        (binding.listProductRecView.adapter as SaleProductAdapter).run {
            notifyItemMoved(pos, pos)
        }
    }

}