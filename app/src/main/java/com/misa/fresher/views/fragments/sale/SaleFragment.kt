package com.misa.fresher.views.fragments.sale


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.models.enums.Category
import com.misa.fresher.models.enums.Color
import com.misa.fresher.models.enums.SortBy
import com.misa.fresher.models.ItemProduct
import com.misa.fresher.R
import com.misa.fresher.data.dao.itemproduct.ItemProductDao
import com.misa.fresher.data.database.AppDatabase
import com.misa.fresher.data.repositories.ProductRepository
import com.misa.fresher.databinding.BottomSheetItemsaleBinding
import com.misa.fresher.views.activities.MainActivity
import com.misa.fresher.views.fragments.SharedViewModel
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.views.customViews.CustomToast


class SaleFragment : Fragment() {

    private val bottomSheetDialog by lazy {   BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)}
    private val bottomSheetItemsaleBinding: BottomSheetItemsaleBinding by lazy {
        BottomSheetItemsaleBinding.inflate(layoutInflater)
    }
    private val sharedViewModel: SharedViewModel by activityViewModels()
//    private val saleViewModel: SaleViewModel by viewModels()
    private lateinit var saleViewModel:SaleViewModel

    var backAndOut = false

    var timer = object : CountDownTimer(3000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            backAndOut = true
        }

        override fun onFinish() {
            backAndOut = false
        }
    }

    private val binding:FragmentSaleBinding by lazy {
        getInflater(layoutInflater)
    }

    val getInflater: (LayoutInflater) -> FragmentSaleBinding
        get() = FragmentSaleBinding::inflate

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        Log.e(this.javaClass.simpleName, "ATTACH")
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transitionFragment(view)
        configFilterDrawer()
        configToolbar()
        configOtherView()
        configListView()
    }

    private fun transitionFragment(view: View) {
        binding.llGetBillDetail.setOnClickListener {
            if (sharedViewModel.listItemBillDetail.value?.size!! > 0)
                Navigation.findNavController(view)
                    .navigate(R.id.action_saleFragment_to_billDetailFragment)
        }

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(binding.root.isDrawerOpen(binding.nvFilter))
                {
                    toggleDrawer(binding.nvFilter)
                }
                else
                {
                    if(backAndOut)
                    {
                        activity?.finish()
                    }
                    else
                    {
                        timer.start()
                        Toast.makeText(context,"Click again to exit!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

    private fun initViewModel() {
        val productDao = ItemProductDao(AppDatabase.getInstance(requireContext()))
        val productRepository = ProductRepository(productDao)
        val factory = SaleViewModelFactory(productRepository)

        saleViewModel = ViewModelProvider(this,factory).get(SaleViewModel::class.java)
        saleViewModel.fakeData()

    }

    private fun configToolbar() {
        binding.searchViewSale.binding.imageviewSearchIcon3
            .setOnClickListener {
                toggleDrawer(binding.nvFilter)
            }

        binding.searchViewSale.binding.imageviewSearchIcon1
            .setOnClickListener {
                (activity as MainActivity).toggleDrawer((activity as MainActivity).binding.nvMenu)
            }

        val editText = binding.searchViewSale.binding.edittextSearchHint

        editText.doAfterTextChanged {
            clearFilter()
            saleViewModel.updateListItemShow(it.toString())
            Log.e(this.javaClass.simpleName, it.toString())
        }
    }

    private fun configFilterDrawer() {
        binding.root.setScrimColor(android.graphics.Color.TRANSPARENT)
        binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.nvFilter)
        binding.layoutFilter.radioBtNameSort.isChecked = true

        binding.layoutFilter.swQuantity.setOnCheckedChangeListener { _, b ->
            saleViewModel.filter.available = b
        }

        binding.layoutFilter.spinItemCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    p3: Long
                ) {
                    if (position == 0) {
                        saleViewModel.filter.category = null
                    } else {
                        val categoryList = listOf(
                            Category.SHIRT,
                            Category.TROUSER,
                            Category.ELECTRONIC
                        )
                        saleViewModel.filter.category = categoryList[position - 1]
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

        binding.layoutFilter.radioBtNameSort.setOnClickListener {
            saleViewModel.filter.sortBy = SortBy.NAME
        }

        binding.layoutFilter.radioBtNewSort.setOnClickListener {
            saleViewModel.filter.sortBy = SortBy.NEW_ARRIVAL
        }

        binding.layoutFilter.radioBtQuantitySort.setOnClickListener {
            saleViewModel.filter.sortBy = SortBy.QUANTITY
        }

        binding.layoutFilter.spinItemColor.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    p3: Long
                ) {
                    if (position == 0) {
                        saleViewModel.filter.color = null
                    } else {
                        val colorList = listOf(
                            Color.RED,
                            Color.GREEN,
                            Color.BLUE,
                            Color.YELLOW
                        )
                        saleViewModel.filter.color = colorList[position - 1]
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

        binding.layoutFilter.tvFilterDone.setOnClickListener {
            saleViewModel.filterListItemShow()
            Log.e(this.javaClass.simpleName, "click")
            toggleDrawer(binding.nvFilter)
        }

        binding.layoutFilter.tvFilterClear.setOnClickListener {
            clearFilter()
            saleViewModel.filterListItemShow()
            toggleDrawer(binding.nvFilter)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    private fun configListView() {
        binding.recyclerviewSaleFragment.layoutManager = LinearLayoutManager(requireContext())
        saleViewModel.listItemShow.observe(viewLifecycleOwner, Observer {
            binding.recyclerviewSaleFragment.adapter=SaleAdapter(it) { saleItemClick(it) }
        })

        sharedViewModel.listItemBillDetail.observe(viewLifecycleOwner, Observer {
            binding.tvQuantityItemSelected.text = it.size.toString()

            if (it.size >= 1) {
                binding.tvQuantityItemSelected.background =
                    this.context?.getDrawable(R.drawable.bg_rounded_left_dark)
                binding.tvTotalPrice.background =
                    this.context?.getDrawable(R.drawable.bg_rounded_right_dark)
                binding.ivRefresh.background = this.context?.getDrawable(R.drawable.bg_circle_dark)
                binding.tvTotalPrice.setTextColor(android.graphics.Color.parseColor("#FFFFFF"))
                binding.tvQuantityItemSelected.setTextColor(android.graphics.Color.parseColor("#FFFFFF"))
                binding.tvTotalPrice.text = "Total "+sharedViewModel.getTotalPrice().toString()
            } else {
                binding.tvQuantityItemSelected.background =
                    this.context?.getDrawable(R.drawable.bg_rounded_left)
                binding.tvTotalPrice.background =
                    this.context?.getDrawable(R.drawable.bg_rounded_right)
                binding.ivRefresh.background = this.context?.getDrawable(R.drawable.bg_circle_light)
                binding.tvTotalPrice.text = this.context?.getString(R.string.not_yet_selected_item)
                binding.tvTotalPrice.setTextColor(android.graphics.Color.parseColor("#99000000"))
                binding.tvQuantityItemSelected.setTextColor(android.graphics.Color.parseColor("#99000000"))
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun configOtherView() {
        binding.ivRefresh.setOnClickListener {
            sharedViewModel.clearListItemSelected()
        }

        sharedViewModel.infoShip.observe(viewLifecycleOwner, Observer {
            if(it.receiver!=null && it.tel!=null)
            {
                binding.tvInforCustomer.isSelected = true
                binding.tvInforCustomer.text = it.receiver.toString()+" - "+it.tel.toString()
            }
            else
            {
                binding.tvInforCustomer.text ="Customer name, phone number"
            }
        })
    }

    fun clearFilter() {
        saleViewModel.clearFilter()

        binding.layoutFilter.radioBtNameSort.isChecked = true
        binding.layoutFilter.radioBtNewSort.isChecked = false
        binding.layoutFilter.radioBtQuantitySort.isChecked = false
        binding.layoutFilter.spinItemCategory.setSelection(0)
        binding.layoutFilter.spinItemColor.setSelection(0)
    }

    private fun toggleDrawer(view: View) {
        if (binding.root.isDrawerOpen(view)) {
            binding.root.closeDrawer(view)
        } else {
            binding.root.openDrawer(view)
        }
    }

    private fun saleItemClick(itemProduct: ItemProduct) {
        val tvItemName = bottomSheetItemsaleBinding.tvItemName
        val tvItemId = bottomSheetItemsaleBinding.tvItemId
        val tvItemQuantity = bottomSheetItemsaleBinding.tvQuantity
        val recyclerView = bottomSheetItemsaleBinding.cvRcv.bindingCustomRecyclerView.cvRcvRecyclerview

        val btAdd = bottomSheetItemsaleBinding.ivAdd
        val btRemove = bottomSheetItemsaleBinding.ivRemove

        sharedViewModel.updateItemSelected(itemProduct)


        btAdd.setOnClickListener {
            sharedViewModel.updateItemSelectedQuantity(1)
        }

        btRemove.setOnClickListener {
            if (sharedViewModel.itemBillDetail.value?.quantity == 1) {
                CustomToast.makeText(this.context!!,"Quantity must be more than 0. Please check again")
            } else {
                sharedViewModel.updateItemSelectedQuantity(-1)
            }
        }

        sharedViewModel.itemBillDetail.observe(viewLifecycleOwner, Observer {
            tvItemQuantity.text = it.quantity.toString()
            tvItemName.text = it.name
            tvItemId.text = it.code
        })

        recyclerView.adapter =
            CustomRecyclerViewAdapter(saleViewModel.getColorOf(itemProduct)) {
                sharedViewModel.updateListItemSelected()
                bottomSheetDialog.dismiss()
            }

        bottomSheetDialog.setContentView(bottomSheetItemsaleBinding.root)
        bottomSheetDialog.show()

    }

//    override fun onResume() {
//        super.onResume()
//        Log.e(this.javaClass.simpleName,"RESUME")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.e(this.javaClass.simpleName,"PAUSE")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.e(this.javaClass.simpleName,"START")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.e(this.javaClass.simpleName,"STOP")
//    }

}

