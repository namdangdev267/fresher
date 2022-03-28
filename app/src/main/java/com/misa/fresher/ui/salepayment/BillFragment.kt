package com.misa.fresher.ui.salepayment

import androidx.navigation.fragment.findNavController
import com.misa.fresher.R
import com.misa.fresher.ui.sale.adapter.SaleProductAdapter
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.FragmentBillBinding
import com.misa.fresher.data.model.product.ProductModel
import com.misa.fresher.data.model.product.ProductBill
import com.misa.fresher.utils.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BillFragment : BaseFragment<FragmentBillBinding>(FragmentBillBinding::inflate), BillContract.View {
    private var presenter: BillPresenter? = null

    override fun initPresenter() {
        presenter = BillPresenter().also { it.attach(this) }
        presenter?.getSelectedProducts(arguments)
    }

    override fun initUI() {
        initPresenter()
        initToolbarUI()

        binding.btnBuyMore.setOnClickListener { activity?.onBackPressed() }
        binding.btnNavToShipInfo.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_bill_to_fragment_ship_info)
        }
        binding.btnPayment.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                presenter?.payProducts(context)
            }
        }
    }


    override fun updateUI() {
        super.updateUI()
        presenter?.getSelectedProductsStatistic()
    }

    private fun initToolbarUI() {
        binding.txtBillId.text = "Bill details"
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
    }


    override fun updateListProductRecViewUI(productModels: ArrayList<ProductModel>) {
        binding.listProductRecView.adapter = SaleProductAdapter(
            productModels,
            onAmountChanged = { presenter?.getSelectedProductsStatistic() },
            clickItemListener = {_, _ ->}
        )
    }
    override fun updateSelectedProductsStatisticUI(totalPrice: Double, totalAmount: Int) {
        binding.txtTotalPrice.text = totalPrice.toString()
        binding.btnProductItemCount.text = totalAmount.toString()
    }


    override fun productPaid() {
        activity?.onBackPressed()
        context?.showToast("paid successful")
    }

    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }
}