package com.misa.fresher.ui.listbills


import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.misa.fresher.R
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.data.model.product.ProductBill
import com.misa.fresher.databinding.FragmentListBillsBinding
import com.misa.fresher.global.FakeData
import com.misa.fresher.ui.listbills.adapter.ListBillAdapter
import com.misa.fresher.utils.showToast

class ListBillFragment : BaseFragment<FragmentListBillsBinding>(FragmentListBillsBinding::inflate),
    ListBillContract.View {
    private var presenter: ListBillPresenter? = null

    override fun initPresenter() {
        presenter = ListBillPresenter().also { it.attach(this) }
    }

    override fun initUI() {
        initPresenter()
        initToolbar()
        initFilters()
        initListBillRecView()
    }

    private fun initToolbar() {
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        binding.btnSearch.setOnClickListener { toggleSearch(true) }
        binding.btnClose.setOnClickListener { toggleSearch(false) }
        binding.txtSearch.doAfterTextChanged {
            val txtSearch = binding.txtSearch.text.toString().lowercase()
            presenter?.getBills(txtSearch)
        }
    }

    private fun initFilters() { presenter?.getFilterOptions() }
    override fun updateFilters(dates: ArrayList<String>, categories: ArrayList<String>) {
        val dateAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_item, dates)
        dateAdapter.setDropDownViewResource(R.layout.item_spinner_item)

        val categoryAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(R.layout.item_spinner_item)

        binding.run {
            spinnerDate.adapter = dateAdapter
            spinnerCategory.adapter = categoryAdapter
        }
    }

    private fun initListBillRecView() {presenter?.getBills()}
    override fun updateListBillRecView(bills: ArrayList<ProductBill>) {
        binding.listBillRecView.adapter = ListBillAdapter(bills) { bill, pos ->
            context?.showToast("you click bill: ${bill.id}")
        }
    }

    override fun updateBillStat(totalAmount: Int, totalPrice: Double) {
        binding.txtTotalAmount.text = totalAmount.toString()
        binding.txtTotalPrice.text = totalPrice.toString()
    }

    private fun toggleSearch(isOpenSearch: Boolean) {
        binding.btnSearch.isVisible = !isOpenSearch
        binding.toolbarTitle.isVisible = !isOpenSearch

        binding.txtSearch.isVisible = isOpenSearch
        binding.btnClose.isVisible = isOpenSearch
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter?.detach()
    }
}