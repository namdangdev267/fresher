package com.misa.fresher.ui.bills

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.MainActivity
import com.misa.fresher.R
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.data.model.Bill
import com.misa.fresher.databinding.FragmentBillsBinding
import com.misa.fresher.ui.bills.adapter.BillsAdapter

/**
 * Màn ListBill
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class BillsFragment :
    BaseFragment<FragmentBillsBinding, BillsContract.View, BillsContract.Presenter>(),
    BillsContract.View {
    private var mPresenter: BillsPresenter? = null
    private var rcv: RecyclerView? = null
    private var rcvAdapter: BillsAdapter? = null
    override fun upDateReclerView(list: MutableList<Bill>) {
        binding.tvTotal.text = list.size.toString()
        binding.tvTotalPriceBills.text = "${list.sumOf { it.totalPrice }}"
        rcvAdapter?.mBills = list
        rcvAdapter?.notifyDataSetChanged()
    }

    override fun initPresenter() {
        if (mPresenter == null) {
            mPresenter = BillsPresenter().also {
                it.attach(this)
            }
        }
    }

    override fun showErrorMessage(msg: String) {
        TODO("Not yet implemented")
    }

    override fun initUI() {
        initPresenter()
        configToggle()
        configSpinnerView()
        configRecyclerView()
    }

    private fun getValuesForFilter() {
        val spnDate = binding.spnDay
        spnDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val dateSpinnerValue = spnDate.selectedItem.toString()
                mPresenter?.getFilterBills(dateSpinnerValue)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    private fun configSpinnerView() {
        val spnDay = binding.spnDay
        val spnPayStatus = binding.spnPayStatus
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.DayArray,
            android.R.layout.simple_spinner_item

        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnDay.adapter = arrayAdapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.payStatusArray,
            android.R.layout.simple_spinner_item

        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnPayStatus.adapter = arrayAdapter
        }
    }

    private fun configRecyclerView() {
        rcv = binding.rcvListBills
        rcvAdapter = BillsAdapter(mutableListOf())
        rcv?.adapter = rcvAdapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
        mPresenter?.getListBillsForAdapter(requireContext())
        getValuesForFilter()
    }

    private fun configToggle() {
        binding.btnMenuBills.setOnClickListener {
            (activity as MainActivity).toggle()
        }
    }

    override val getInflater: (LayoutInflater) -> FragmentBillsBinding =
        FragmentBillsBinding::inflate

    override fun onDestroy() {
        super.onDestroy()
        mPresenter = null
    }
}