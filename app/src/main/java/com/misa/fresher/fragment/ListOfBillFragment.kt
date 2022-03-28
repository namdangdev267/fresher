package com.misa.fresher.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapter.ListOfBillApdater
import com.misa.fresher.models.BillViewModel
import com.misa.fresher.models.Bill

class ListOfBillFragment : Fragment() {
    var viewModel : BillViewModel? = null
    var mListBill = arrayListOf<Bill>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listofbill,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BillViewModel ::class.java)
        setUpView(view)
        mListBill = getListBill()
        setUpRecycleView(view)
        onChageToHomeFragment(view)

    }

    fun setUpView(view : View){
        val tvNumberTotal = view.findViewById<TextView>(R.id.tv_number_total)
        tvNumberTotal.text = viewModel?.getSize().toString()

        val tvSumMoney = view.findViewById<TextView>(R.id.tv_sum_money)
        tvSumMoney.text =  viewModel?.calculateTotalAmount().toString()

    }

    fun getListBill(): ArrayList<Bill>{
        return viewModel?.listItemBill?.value as ArrayList<Bill>
    }

    fun setUpRecycleView(view : View){
        val rcvListOfBill = view.findViewById<RecyclerView>(R.id.rv_listofBill)
        val adapter =ListOfBillApdater(mListBill)
        rcvListOfBill.adapter = adapter
        rcvListOfBill.layoutManager =LinearLayoutManager(requireContext())
    }
    fun onChageToHomeFragment(view : View){
        val btnCart = view.findViewById<ImageButton>(R.id.img_btn_cart)
        btnCart.setOnClickListener(){
            findNavController().navigate(R.id.action_listOfBillFragment_to_homeFragment)
        }
    }
}