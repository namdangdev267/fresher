package com.misa.fresher.ui.billdetails

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.R
import com.misa.fresher.ui.sale.adapter.SaleProductAdapter
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.FragmentBillBinding
import com.misa.fresher.global.FakeData
import com.misa.fresher.models.product.Product
import com.misa.fresher.models.product.ProductBill
import com.misa.fresher.ui.sale.SaleFragment
import com.misa.fresher.utils.showToast

class BillFragment : BaseFragment<FragmentBillBinding>(FragmentBillBinding::inflate) {
    private var selectedItems = arrayListOf<Product>()

    override fun initUI() {
        initToolbarUI()
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
        binding.btnPayment.setOnClickListener {
            val products = ArrayList<Product>(selectedItems)
            selectedItems.clear()
            FakeData.productBills.add(
                ProductBill(
                    date = System.currentTimeMillis(),
                    customer = "test customer",
                    products =  products
                )
            )
            activity?.onBackPressed()
            context?.showToast(products.size.toString())
        }
    }

    override fun updateUI() {
        super.updateUI()
        updateTotalPriceUI()
        updateSelectedItemsCountUI()
    }

    private fun initToolbarUI() {
        binding.txtBillId.text = ProductBill._id.toString()
    }
    private fun initToolbarListener() {
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
    }

    private fun initProductItems() {
        selectedItems =arguments?.get(SaleFragment.BUNDLE_SELECTED_ITEMS) as? ArrayList<Product> ?: arrayListOf()
    }

    private fun initListProductRecViewUI() {
        binding.listProductRecView.adapter = SaleProductAdapter(
            selectedItems,
            onAmountChanged = {
                updateTotalPriceUI()
                updateSelectedItemsCountUI()
            },
            clickItemListener = {_, _ ->}
        )
        binding.listProductRecView.layoutManager = LinearLayoutManager(context)
    }
    private fun updateTotalPriceUI() {
        binding.txtTotalPrice.text = selectedItems.sumOf { it.price }.toString()
    }
    private fun updateSelectedItemsCountUI() {
        binding.btnProductItemCount.text = selectedItems.sumOf { it.amount }.toString()
    }
}