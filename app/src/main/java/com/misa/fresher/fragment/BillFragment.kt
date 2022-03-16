package com.misa.fresher.fragment

import android.view.Gravity
import android.widget.Toast
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
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
        binding.btnBuyMore.setOnClickListener { activity?.onBackPressed() }
        binding.btnNavToShipInfo.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_bill_to_fragment_ship_info)
        }
    }

    override fun updateUI() {
        super.updateUI()
        updateTotalPriceUI()
        updateSelectedItemsCountUI()
    }

    private fun initToolbarListener() {
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
    }

    private fun initProductItems() {
        selectedItems = try { arguments?.get("selected_items") as ArrayList<Product> }
        catch (e: Exception) { arrayListOf() }
    }

    private fun initListProductRecViewUI() {
        binding.listProductRecView.adapter = SaleProductAdapter(selectedItems) {_, _ ->}
        binding.listProductRecView.layoutManager = LinearLayoutManager(context)
        binding.listProductRecView.post {
            selectedItems.forEachIndexed { pos, product ->
                (binding.listProductRecView.findViewHolderForAdapterPosition(pos) as SaleProductAdapter.ProductItemViewHolder).itemSaleProductView.run {
                    binding.btnAdd.setOnClickListener {
                        amount = ++product.items[0].amount
                        updateTotalPriceUI()
                        updateSelectedItemsCountUI()

                    }
                    binding.btnMinus.setOnClickListener {
                        if(product.items[0].amount == 1) {
                            val toast = Toast.makeText(
                                context, "Quantity must be more than 0. Please check again.", Toast.LENGTH_LONG
                            )
                            toast.setGravity(Gravity.TOP, 0, 120)
                            toast.show()
                        } else {
                            amount = --product.items[0].amount
                            updateTotalPriceUI()
                            updateSelectedItemsCountUI()
                        }
                    }
                }
            }
        }
    }
    private fun updateTotalPriceUI() {
        binding.txtTotalPrice.text = selectedItems.sumOf { it.getTotalPrice() }.toString()
    }
    private fun updateSelectedItemsCountUI() {
        binding.productItemCountBtn.text = selectedItems.sumOf { it.getAmount() }.toString()
    }
}