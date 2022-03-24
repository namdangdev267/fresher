package com.misa.fresher.ui.billdetail

import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.data.model.Customer
import com.misa.fresher.data.model.SelectedProducts
import com.misa.fresher.databinding.FragmentBillDetailBinding
import com.misa.fresher.ui.billdetail.adapter.ProductSelectedAdapter

/**
 * Tạo BillDetail
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class BillDetailFragment :
    BaseFragment<FragmentBillDetailBinding, BillDetailContract.View, BillDetailContract.Presenter>(),
    BillDetailContract.View {
    private var mPresenter: BillDetailPresenter? = null
    private var rcv: RecyclerView? = null
    private var rcvAdapter: ProductSelectedAdapter? = null

    override fun initUI() {
        initPresenter()
        mPresenter?.getBillId(requireContext())
        configRecyclerView()
        savingBill()
    }

    private fun savingBill() {
        binding.btnGetPayment.setOnClickListener {
            val id = binding.tvBillId.text.toString()
            mPresenter?.saveBill(
                requireContext(),
                id.toInt()
            )
        }
    }

    override fun updateRecyclerViewSelectedProducts(list: MutableList<SelectedProducts>) {
        rcvAdapter?.mSelectedProducts = list
        rcvAdapter?.notifyDataSetChanged()
    }

    override fun updateSelectedProduct(amount: Int, price: Double) {
        binding.btnAmountBills.text = amount.toString()
        binding.tvTotalPriceBill.text = price.toString()
    }

    override fun updateSelectedClicked(list: MutableList<SelectedProducts>) {
        rcvAdapter?.mSelectedProducts = list
        rcvAdapter?.notifyDataSetChanged()
    }

    override fun initPresenter() {
        mPresenter = BillDetailPresenter().also {
            it.attach(this)
        }
    }

    private fun configRecyclerView() {
        rcv = binding.rcvListItem
        rcvAdapter = ProductSelectedAdapter(arrayListOf()) {
            mPresenter?.getUpdateSelectedClicked(it)
        }
        rcv?.adapter = rcvAdapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
        arguments?.let { mPresenter?.getSelectedProducts(it) }
    }


    override fun showErrorMessage(msg: String) {
        TODO("Not yet implemented")
    }

    override fun updateReceiver(customer: Customer) {
        TODO("Not yet implemented")
    }

    override fun navigate() {
        findNavController().navigate(R.id.action_nav_billDetail_to_nav_sale)
    }

    override fun updateBillId(BillId: Int) {
        binding.tvBillId.text = BillId.toString()
    }

    override val getInflater: (LayoutInflater) -> FragmentBillDetailBinding =
        FragmentBillDetailBinding::inflate
}
