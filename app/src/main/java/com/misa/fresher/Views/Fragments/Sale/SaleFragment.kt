package com.misa.fresher.Views.Fragments.Sale

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.Models.Enum.Category
import com.misa.fresher.Models.Enum.Color
import com.misa.fresher.Models.Enum.SortBy
import com.misa.fresher.Models.ItemSale
import com.misa.fresher.R
import com.misa.fresher.Views.CustomViews.CustomRecyclerView
import com.misa.fresher.Views.CustomViews.CustomSearchView
import com.misa.fresher.Views.CustomViews.CustomToast
import com.misa.fresher.databinding.FragmentSaleBinding


class SaleFragment: Fragment() {

    lateinit var binding: FragmentSaleBinding
    lateinit var recyclerView: RecyclerView
    lateinit var saleViewModel: SaleViewModel
    lateinit var textViewTotal: TextView
    lateinit var textViewAmount: TextView
    lateinit var imageViewRefresh:ImageView
    lateinit var customSearchView: CustomSearchView
    lateinit var bottomSheetDialog:BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(this.javaClass.simpleName,"attach")
        saleViewModel = SaleViewModel()
        saleViewModel.initData()
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()

        recyclerView = view.findViewById(R.id.recyclerview_sale_fragment)
        textViewTotal = view.findViewById(R.id.tv_total_price)
        textViewAmount = view.findViewById(R.id.tv_amount_item_selected)
        customSearchView = view.findViewById(R.id.search_view_sale)
        imageViewRefresh = view.findViewById(R.id.iv_refresh)
        var editText = customSearchView.findViewById<EditText>(R.id.edittext_search_hint)


        recyclerView.layoutManager = LinearLayoutManager(view.context)
        saleViewModel.listItemShow.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = it?.let { it1 -> SaleAdapter(it1) { it -> saleItemClick(it) } }
        })


        textViewTotal.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_saleFragment_to_shippingInformationFragment)
        }

        imageViewRefresh.setOnClickListener {
            saleViewModel.clearListItemSelected()
        }

        editText.doAfterTextChanged {
            saleViewModel.updateListItemShow(it.toString())
            Log.e(this.javaClass.simpleName,it.toString())
        }

        saleViewModel.listItemSelected.observe(viewLifecycleOwner, Observer {
            textViewAmount.text = it.size.toString()

            if(it.size>=1)
            {
                textViewAmount.background = this.context?.getDrawable(R.drawable.bg_rounded_left_dark)
                textViewTotal.background = this.context?.getDrawable(R.drawable.bg_rounded_right_dark)
                imageViewRefresh.background = this.context?.getDrawable(R.drawable.bg_circle_dark)
                textViewTotal.setTextColor(android.graphics.Color.parseColor("#FFFFFF"))
                textViewAmount.setTextColor(android.graphics.Color.parseColor("#FFFFFF"))
                textViewTotal.text = saleViewModel.getTotalPrice().toString()
            }
            else
            {
                textViewAmount.background = this.context?.getDrawable(R.drawable.bg_rounded_left)
                textViewTotal.background = this.context?.getDrawable(R.drawable.bg_rounded_right)
                imageViewRefresh.background = this.context?.getDrawable(R.drawable.bg_circle_light)
                textViewTotal.text = this.context?.getString(R.string.not_yet_selected_item)
                textViewTotal.setTextColor(android.graphics.Color.parseColor("#99000000"))
                textViewAmount.setTextColor(android.graphics.Color.parseColor("#99000000"))
            }
        })

        binding.searchViewSale.findViewById<ImageView>(R.id.imageview_search_icon3).setOnClickListener {
            toggleDrawer(binding.nvFilter)
        }

        binding.layoutFilter.spinItemCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                if (position == 0) {
                    saleViewModel.filter.category = null
                } else {
                    val categoryList = listOf(
                        Category.Shirt,
                        Category.Trouser,
                        Category.Electronic
                    )
                    saleViewModel.filter.category = categoryList[position-1]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.layoutFilter.radioBtNameSort.setOnClickListener {
            saleViewModel.filter.sortBy = SortBy.name_item
        }

        binding.layoutFilter.radioBtNewSort.setOnClickListener {
            saleViewModel.filter.sortBy = SortBy.new_arrival
        }

        binding.layoutFilter.radioBtQuantitySort.setOnClickListener {
            saleViewModel.filter.sortBy = SortBy.quantity
        }

        binding.layoutFilter.spinItemColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                if (position == 0) {
                    saleViewModel.filter.color = null
                } else {
                    val colorList = listOf(
                        Color.red,
                        Color.green,
                        Color.blue,
                        Color.yellow
                    )
                    saleViewModel.filter.color = colorList[position-1]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.layoutFilter.tvFilterDone.setOnClickListener {
            saleViewModel.filterListItemShow()
            Log.e(this.javaClass.simpleName,"click")
            toggleDrawer(binding.nvFilter)
        }

        binding.layoutFilter.tvFilterClear.setOnClickListener {
            saleViewModel.filter.sortBy = null
            saleViewModel.filter.category  = null
            saleViewModel.filter.color = null
            saleViewModel.filter.available = false
            saleViewModel.filterListItemShow()

            binding.nvFilter.findViewById<RadioButton>(R.id.radio_bt_name_sort).isChecked = false
            binding.nvFilter.findViewById<RadioButton>(R.id.radio_bt_new_sort).isChecked = false
            binding.nvFilter.findViewById<RadioButton>(R.id.radio_bt_quantity_sort).isChecked = false
            binding.nvFilter.findViewById<Spinner>(R.id.spin_item_category).setSelection(0)
            binding.nvFilter.findViewById<Spinner>(R.id.spin_item_color).setSelection(0)

            toggleDrawer(binding.nvFilter)
        }
    }

    private fun initUI() {
        configFilterDrawer()
    }

    private fun configFilterDrawer() {
        binding.root.setScrimColor(android.graphics.Color.TRANSPARENT)
        binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.nvFilter)
    }

    private fun toggleDrawer(view:View) {
        if (binding.root.isDrawerOpen(view)) {
            binding.root.closeDrawer(view)
        } else {
            binding.root.openDrawer(view)
        }
    }

    private fun saleItemClick(itemSale: ItemSale) {
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetView: View = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottom_sheet_itemsale,
            this.view as DrawerLayout, false
        )

        val tvItemName = bottomSheetView.findViewById<TextView>(R.id.tv_item_name)
        val tvItemId = bottomSheetView.findViewById<TextView>(R.id.tv_item_id)
        val tvItemAmount = bottomSheetView.findViewById<TextView>(R.id.tv_amount)
        val recyclerView = bottomSheetView.findViewById<CustomRecyclerView>(R.id.cv_rcv).findViewById<RecyclerView>(R.id.cv_rcv_recyclerview)
        val btAdd = bottomSheetView.findViewById<ImageView>(R.id.iv_add)
        val btRemove = bottomSheetView.findViewById<ImageView>(R.id.iv_remove)

        saleViewModel.updateItemSelected(itemSale)


        btAdd.setOnClickListener {
            saleViewModel.updateItemSelectedAmount(1)
        }

        btRemove.setOnClickListener {
            if(saleViewModel.itemSelected.value?.amount ==1)
            {
                var customToast = CustomToast(requireContext())
                customToast.makeText(requireContext(),"Quantity must be more than 0. Please check again",Toast.LENGTH_SHORT).show()
            }
            else {
                saleViewModel.updateItemSelectedAmount(-1)
            }
        }

        saleViewModel.itemSelected.observe(viewLifecycleOwner, Observer {
            tvItemAmount.text = it.amount.toString()
            tvItemName.text = it.name
            tvItemId.text = it.id
        })
        recyclerView.adapter = CustomRecyclerViewAdapter(saleViewModel.getColorOf(itemSale)) { clickColor(it) }

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()

    }

    fun clickColor(color: Color)
    {
        saleViewModel.updateListItemSelected()
        bottomSheetDialog.dismiss()
//        Toast.makeText(this.context,saleViewModel.listItemSelected.value?.size.toString()+"--"+saleViewModel.getTotalPrice().toString(),Toast.LENGTH_LONG).show()
    }


}



