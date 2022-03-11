package com.misa.fresher.Views.Fragments.Sale

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemSale
import com.misa.fresher.R
import com.misa.fresher.Views.Fragments.SearchView


class SaleFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var saleViewModel: SaleViewModel
    lateinit var textViewTotal: TextView
    lateinit var searchView:SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        saleViewModel = ViewModelProvider(this).get(SaleViewModel::class.java)

        saleViewModel = SaleViewModel()
        recyclerView = view.findViewById(R.id.recyclerview_sale_fragment)
        recyclerView.layoutManager= LinearLayoutManager(view.context)
        saleViewModel.listItemShow.observe(viewLifecycleOwner, Observer { recyclerView.adapter = SaleAdapter(it)})

        textViewTotal = view.findViewById(R.id.textview_total)
        textViewTotal.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_saleFragment_to_shippingInformationFragment)
        }

        searchView = view.findViewById(R.id.search_view_sale)
        searchView.setOnClickListener {
            searchView.setEditTextClick{
                saleViewModel.updateListItemShow(it)
                Log.e(this.javaClass.simpleName,it)
            }
        }




    }



}