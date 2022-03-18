package com.misa.fresher.ui.listbills


import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.FragmentListBillsBinding
import com.misa.fresher.global.FakeData

class ListBillFragment : BaseFragment<FragmentListBillsBinding>(FragmentListBillsBinding::inflate) {
    private var productBills = FakeData.productBills

    override fun initUI() {
    }

    override fun initListener() {
        super.initListener()
    }

    override fun updateUI() {
        super.updateUI()
    }
}