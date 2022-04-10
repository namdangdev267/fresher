package com.misa.fresher.ui.fragment.sale

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.*
import com.misa.fresher.data.models.Product
import com.misa.fresher.data.models.enum.Category
import com.misa.fresher.data.models.enum.SortBy
import com.misa.fresher.databinding.BottomSheetProductBinding
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.ui.activity.MainActivity
import kotlinx.android.synthetic.main.custom_search_view.view.*
import kotlinx.android.synthetic.main.sale_context.view.*
@SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n", "NotifyDataSetChanged")

class SaleFragment : Fragment() {

    private val binding: FragmentSaleBinding by lazy { getInflater(layoutInflater) }
    private val publicViewModel: PublicViewModel by activityViewModels()
    private val saleViewModel: SaleViewModel by viewModels()
    private var adapter: ProductAdapter? = null
    private val bottomSheetDialog by lazy {
        BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetDialogTheme
        )
    }
    private val bottomSheetView by lazy { BottomSheetProductBinding.inflate(layoutInflater) }
    val getInflater: (LayoutInflater) -> FragmentSaleBinding get() = FragmentSaleBinding::inflate
    private var newFilter: com.misa.fresher.data.models.Filter? = null

    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var mLayoutManagement: RecyclerView.LayoutManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        publicViewModel.fakeData(requireContext())
        transitionFragment()
        configureFilterDrawer()
        configureToolbar()
        configureOtherView()
        configListView()
        updateListView()
    }

    private fun transitionFragment() {

        binding.root.linearQuantity.setOnClickListener {
            if (publicViewModel.listItemSelected.value!!.size > 0) {
                findNavController().navigate(R.id.action_fragment_sale_to_fragment_payment)
            }
        }
    }

    private fun configureToolbar() {

        binding.searchviewSale.img_icon1.setOnClickListener {
            (activity as MainActivity).toggleDrawer((activity as MainActivity).binding.navSaleFragment)
        }

        binding.searchviewSale.img_search3.setOnClickListener {
            toggleDrawer(binding.navigationView)
        }

        binding.searchviewSale.edt_search_hint.doAfterTextChanged {
            saleViewModel.updateListItemShow(it.toString())
            Log.d("tagSearch", it.toString())
        }
    }

    private fun initViewModel(context: Context) {
        saleViewModel.createData(context)
    }

    private fun clearFilter() {
        saleViewModel.clearFilter()

        binding.filterDrawer.run {
            rbName.isChecked = true
            rbNew.isChecked = false
            rbQuantity.isChecked = false
            spnGrouping.setSelection(0)
            spinItemColor.setSelection(0)
        }
    }

    private fun configureFilterDrawer() {
        binding.root.setScrimColor(Color.TRANSPARENT)
        binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.navigationView)

        binding.filterDrawer.run {
            rbName.isChecked = true
            swQuantity.setOnCheckedChangeListener { _, b ->
                saleViewModel.filter.available = b
            }
            spnGrouping.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        p2: Int,
                        p3: Long
                    ) {
                        if (p2 == 0) {
                            saleViewModel.filter.category = null
                        } else {
                            val listCategory = listOf(
                                Category.SHIRT,
                                Category.TROUSER,
                                Category.ELECTRONIC
                            )
                            saleViewModel.filter.category = listCategory[p2 - 1]
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }

            rbName.setOnClickListener {
                saleViewModel.filter.sortBy = SortBy.NAME
            }
            rbNew.setOnClickListener {
                saleViewModel.filter.sortBy = SortBy.NEW_ARRIVAL

            }
            rbQuantity.setOnClickListener {
                saleViewModel.filter.sortBy = SortBy.QUANTITY
            }

            spinItemColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 == 0)
                        saleViewModel.filter.color = null
                    else {
                        val listColor = listOf(
                            com.misa.fresher.data.models.enum.Color.RED,
                            com.misa.fresher.data.models.enum.Color.YELLOW,
                            com.misa.fresher.data.models.enum.Color.BLUE,
                            com.misa.fresher.data.models.enum.Color.BLACK
                        )
                        saleViewModel.filter.color = listColor[p2 - 1]
                    }

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

            btnDone.setOnClickListener {
                saleViewModel.filterShow()
                Log.e("tagFilter", newFilter.toString())
                toggleDrawer(binding.navigationView)
                updateListByFilter()
            }

            btnReset.setOnClickListener {
                clearFilter()
                saleViewModel.filterShow()
                toggleDrawer(binding.navigationView)
            }
        }
    }

    private fun updateListByFilter() {
        saleViewModel.filterList.observe(viewLifecycleOwner) {
            adapter?.listItems = it
//            adapter?.notifyDataSetChanged()
            adapter?.submitList(it)
        }
    }

    private fun setScrollListener() {
        binding.rcvProduct.layoutManager = LinearLayoutManager(requireContext())
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManagement as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                loadMoreData()
            }

        })
        binding.rcvProduct.addOnScrollListener(scrollListener)
    }

    private fun loadMoreData() {
        TODO("Not yet implemented")
    }

    private fun configListView() {
        binding.rcvProduct.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvProduct.setHasFixedSize(true)
        adapter = ProductAdapter(mutableListOf()) { saleItemClick(it) }
        binding.rcvProduct.adapter = adapter

        saleViewModel.listProductShow.observe(viewLifecycleOwner) {
            adapter?.listItems = it
//            adapter?.notifyDataSetChanged()
            adapter?.submitList(it)
        }

        publicViewModel.listItemSelected.observe(viewLifecycleOwner) {
            binding.tvCountProduct.text = publicViewModel.getCount().toString()

            if (it.size >= 1) {
                binding.linearQuantity.background =
                    requireContext().getDrawable(R.drawable.custom_background)
                binding.btnRefresh.background =
                    requireContext().getDrawable(R.drawable.custom_button)
                binding.tvCountProduct.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvBillProduct.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvBillProduct.text =
                    "Total " + publicViewModel.getTotalPrice().toString()
            } else {
                binding.linearQuantity.background =
                    requireContext().getDrawable(R.drawable.custom_background_none)
                binding.btnRefresh.background =
                    requireContext().getDrawable(R.drawable.custom_button_none)
                binding.tvBillProduct.text = "Not yet selected item"
                binding.tvCountProduct.setTextColor(Color.GRAY)
                binding.tvBillProduct.setTextColor(Color.GRAY)
            }
        }
    }

    private fun updateListView() {
        saleViewModel.searchList.observe(viewLifecycleOwner) {
            adapter?.listItems = it
//            adapter?.notifyDataSetChanged()
            adapter?.submitList(it)
        }
    }

    private fun configureOtherView() {
        binding.tvCustomer.isSelected = true

        binding.btnRefresh.setOnClickListener {
            publicViewModel.clearListItemSelected()
        }

        publicViewModel.inforShip.observe(viewLifecycleOwner) {
            val tvCustomer = binding.tvCustomer
            tvCustomer.isSingleLine = true
            if (it.receiver != null && it.tel != null) {
                tvCustomer.text = it.receiver.toString() + "_" + it.tel.toString()
            } else {
                tvCustomer.text = "Customer name, phone number"
            }
        }
    }

    private fun toggleDrawer(view: View) {
        if (binding.root.isDrawerOpen(view)) {
            binding.root.closeDrawer(view)
        } else {
            binding.root.openDrawer(view)
        }
    }

    private fun saleItemClick(itemProduct: Product) {
        val tvItemName = bottomSheetView.tvNameProduct
        val tvItemId = bottomSheetView.tvCodeProduct
        val tvItemQuantity = bottomSheetView.tvCountProduct
        val btAdd = bottomSheetView.imgAdd
        val btRemove = bottomSheetView.imgRemove

        publicViewModel.updateItemSelected(itemProduct)

        btAdd.setOnClickListener {
            publicViewModel.updateItemSelectedQuantity(1)
        }

        btRemove.setOnClickListener {
            if (publicViewModel.itemPackageProduct.value!!.countPackage == 1) {
                requireContext().showToast("Quantity must be more than 0. Please check again")
            } else {
                publicViewModel.updateItemSelectedQuantity(-1)
            }
        }

        publicViewModel.itemPackageProduct.observe(viewLifecycleOwner) {
            tvItemQuantity.text = it.countPackage.toString()
            tvItemName.text = it.namePackage
            tvItemId.text = it.codePackage
        }

        bottomSheetDialog.setContentView(bottomSheetView.root)

        bottomSheetDialog.setOnDismissListener {
            publicViewModel.updateListItemSelected()
        }

        bottomSheetDialog.show()

    }
}

