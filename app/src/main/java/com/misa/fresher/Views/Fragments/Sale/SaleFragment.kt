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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.Models.Enum.Category
import com.misa.fresher.Models.Enum.Color
import com.misa.fresher.Models.Enum.SortBy
import com.misa.fresher.Models.ItemProduct
import com.misa.fresher.R
import com.misa.fresher.Views.Activities.MainActivity
import com.misa.fresher.Views.CustomViews.CustomRecyclerView
import com.misa.fresher.Views.CustomViews.CustomToast
import com.misa.fresher.Views.Fragments.SharedViewModel
import com.misa.fresher.databinding.FragmentSaleBinding


class SaleFragment: Fragment() {

    lateinit var binding: FragmentSaleBinding

    lateinit var sharedViewModel: SharedViewModel
    var saleViewModel: SaleViewModel = SaleViewModel()
    lateinit var bottomSheetDialog:BottomSheetDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(this.javaClass.simpleName,"attach")
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        binding.tvTotalPrice.setOnClickListener {
            if(sharedViewModel.listItemSelected.value?.size!! >0)
            Navigation.findNavController(view)
                .navigate(R.id.action_saleFragment_to_billDetailFragment)
        }
    }

    private fun initViewModel() {
        saleViewModel = SaleViewModel()
        saleViewModel.initData()

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

    }

    private fun initUI() {
        configFilterDrawer()
        configSearchView()
        configOtherView()
        configListView()
    }

    private fun configListView() {

        binding.recyclerviewSaleFragment.layoutManager = LinearLayoutManager(requireContext())
        saleViewModel.listItemShow.observe(viewLifecycleOwner, Observer {
            binding.recyclerviewSaleFragment.adapter = it?.let { it1 -> SaleAdapter(it1) { it -> saleItemClick(it) } }
        })

        sharedViewModel.listItemSelected.observe(viewLifecycleOwner, Observer {
            binding.tvQuantityItemSelected.text = it.size.toString()

            if(it.size>=1)
            {
                binding.tvQuantityItemSelected.background = this.context?.getDrawable(R.drawable.bg_rounded_left_dark)
                binding.tvTotalPrice.background = this.context?.getDrawable(R.drawable.bg_rounded_right_dark)
                binding.ivRefresh.background = this.context?.getDrawable(R.drawable.bg_circle_dark)
                binding.tvTotalPrice.setTextColor(android.graphics.Color.parseColor("#FFFFFF"))
                binding.tvQuantityItemSelected.setTextColor(android.graphics.Color.parseColor("#FFFFFF"))
                binding.tvTotalPrice.text = sharedViewModel.getTotalPrice().toString()
            }
            else
            {
                binding.tvQuantityItemSelected.background = this.context?.getDrawable(R.drawable.bg_rounded_left)
                binding.tvTotalPrice.background = this.context?.getDrawable(R.drawable.bg_rounded_right)
                binding.ivRefresh.background = this.context?.getDrawable(R.drawable.bg_circle_light)
                binding.tvTotalPrice.text = this.context?.getString(R.string.not_yet_selected_item)
                binding.tvTotalPrice.setTextColor(android.graphics.Color.parseColor("#99000000"))
                binding.tvQuantityItemSelected.setTextColor(android.graphics.Color.parseColor("#99000000"))
            }
        })
    }

    private fun configOtherView() {
        binding.ivRefresh.setOnClickListener {
            sharedViewModel.clearListItemSelected()
        }
    }

    private fun configSearchView() {
        binding.searchViewSale.findViewById<ImageView>(R.id.imageview_search_icon3).setOnClickListener {
            toggleDrawer(binding.nvFilter)
        }

        binding.searchViewSale.findViewById<ImageView>(R.id.imageview_search_icon1).setOnClickListener {
            (activity as MainActivity).toggleDrawer((activity as MainActivity).binding.nvMenu)
        }

        var editText = binding.searchViewSale.findViewById<EditText>(R.id.edittext_search_hint)

        editText.doAfterTextChanged {
            saleViewModel.updateListItemShow(it.toString())
            Log.e(this.javaClass.simpleName,it.toString())
        }
    }

    private fun configFilterDrawer() {
        binding.root.setScrimColor(android.graphics.Color.TRANSPARENT)
        binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.nvFilter)

        binding.layoutFilter.swQuantity.setOnCheckedChangeListener { it, b -> saleViewModel.filter.available = b }

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

    private fun toggleDrawer(view:View) {
        if (binding.root.isDrawerOpen(view)) {
            binding.root.closeDrawer(view)
        } else {
            binding.root.openDrawer(view)
        }
    }

    private fun saleItemClick(itemProduct: ItemProduct) {
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetView: View = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottom_sheet_itemsale,
            this.view as DrawerLayout, false
        )

        val tvItemName = bottomSheetView.findViewById<TextView>(R.id.tv_item_name)
        val tvItemId = bottomSheetView.findViewById<TextView>(R.id.tv_item_id)
        val tvItemQuantity = bottomSheetView.findViewById<TextView>(R.id.tv_quantity)
        val recyclerView = bottomSheetView.findViewById<CustomRecyclerView>(R.id.cv_rcv).findViewById<RecyclerView>(R.id.cv_rcv_recyclerview)
        val btAdd = bottomSheetView.findViewById<ImageView>(R.id.iv_add)
        val btRemove = bottomSheetView.findViewById<ImageView>(R.id.iv_remove)

        sharedViewModel.updateItemSelected(itemProduct)


        btAdd.setOnClickListener {
            sharedViewModel.updateItemSelectedQuantity(1)
        }

        btRemove.setOnClickListener {
            if(sharedViewModel.itemSelected.value?.quantity ==1)
            {
                var customToast = CustomToast(requireContext())
                customToast.makeText(requireContext(),"Quantity must be more than 0. Please check again",Toast.LENGTH_SHORT).show()
            }
            else {
                sharedViewModel.updateItemSelectedQuantity(-1)
            }
        }

        sharedViewModel.itemSelected.observe(viewLifecycleOwner, Observer {
            tvItemQuantity.text = it.quantity.toString()
            tvItemName.text = it.name
            tvItemId.text = it.id
        })
        recyclerView.adapter = CustomRecyclerViewAdapter(saleViewModel.getColorOf(itemProduct)) { clickColor(it) }

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    fun clickColor(color: Color)
    {
        sharedViewModel.updateListItemSelected()
        bottomSheetDialog.dismiss()
    }


}



