package com.misa.fresher.Views.Fragments.Sale

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.Models.ItemSale
import com.misa.fresher.R
import com.misa.fresher.Views.Fragments.SearchView


class SaleFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var saleViewModel: SaleViewModel
    lateinit var textViewTotal: TextView
    lateinit var searchView: SearchView

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
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        var sale: ItemSale = ItemSale("a", 1.2f, "a")
        saleViewModel.listItemShow.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = SaleAdapter(it) { it -> saleItemClick(it) }
        })

        textViewTotal = view.findViewById(R.id.textview_total)
        textViewTotal.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_saleFragment_to_shippingInformationFragment)
        }

        searchView = view.findViewById(R.id.search_view_sale)

        searchView.findViewById<EditText>(R.id.edittext_search_hint).doAfterTextChanged {
            saleViewModel.updateListItemShow(it.toString())
        }
    }

    private fun saleItemClick(itemSale: ItemSale) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetView: View = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottom_sheet_itemsale,
            this.view as LinearLayout, false
        )
        bottomSheetView.findViewById<TextView>(R.id.tv_item_name).text = itemSale.name
        bottomSheetView.findViewById<TextView>(R.id.tv_item_id).text = itemSale.id

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }


}



