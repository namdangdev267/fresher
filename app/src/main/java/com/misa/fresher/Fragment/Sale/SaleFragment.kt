package com.misa.fresher.fragment.sale

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.MainActivity
import com.misa.fresher.PublicViewModel
import com.misa.fresher.R
import com.misa.fresher.data.models.Product
import com.misa.fresher.data.models.enum.Category
import com.misa.fresher.data.models.enum.SortBy
import com.misa.fresher.databinding.BottomSheetProductBinding
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.showToast
import kotlinx.android.synthetic.main.custom_search_view.view.*
import kotlinx.android.synthetic.main.sale_context.view.*

class SaleFragment : Fragment() {

    private val binding: FragmentSaleBinding by lazy { getInflater(layoutInflater) }
    private val sharedViewModel: PublicViewModel by activityViewModels()
    private val saleViewModel: SaleViewModel by viewModels()
    private val bottomSheetDialog by lazy {
        BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetDialogTheme
        )
    }
    private val bottomSheetView by lazy { BottomSheetProductBinding.inflate(layoutInflater) }

    val getInflater: (LayoutInflater) -> FragmentSaleBinding get() = FragmentSaleBinding::inflate

    var goBackAndExit = false

    var runTime = object : CountDownTimer(3000, 1000) {
        override fun onTick(p0: Long) {
            goBackAndExit = true
        }

        override fun onFinish() {
            goBackAndExit = false
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
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

        sharedViewModel.fakeData(requireContext())

        transitionFragment()
        configureFilterDrawer()
        configureToolbar()
        configureOtherView()
        configListView()
    }

    private fun transitionFragment() {

        binding.root.linearQuantity.setOnClickListener {
            if (sharedViewModel.listItemSelected.value!!.size > 0) {
                findNavController().navigate(R.id.action_fragment_sale_to_fragment_payment)
            }
        }
    }

    private fun initViewModel() {
        saleViewModel.createData(requireContext())
    }

    private fun configureToolbar() {

        binding.searchviewSale.imageview_search_icon1.setOnClickListener {
            (activity as MainActivity).toggleDrawer((activity as MainActivity).binding.navSaleFragment)
        }

        binding.searchviewSale.imageview_search_icon3.setOnClickListener {
            toggleDrawer(binding.navigationView)
        }

        binding.searchviewSale.edittext_search_hint.doAfterTextChanged {
            clearFilter()
            saleViewModel.updateListItemShow(it.toString())
            toggleDrawer(binding.navigationView)
        }
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

            rbName.setOnClickListener { saleViewModel.filter.sortBy = SortBy.NAME }
            rbNew.setOnClickListener { saleViewModel.filter.sortBy = SortBy.NEW_ARRIVAL }
            rbQuantity.setOnClickListener { saleViewModel.filter.sortBy = SortBy.QUANTITY }

            spinItemColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 == 0)
                        saleViewModel.filter.color = null
                    else {
                        val listColor = listOf(
                            com.misa.fresher.data.models.enum.Color.RED,
                            com.misa.fresher.data.models.enum.Color.YELLOW,
                            com.misa.fresher.data.models.enum.Color.BLUE
                        )
                        saleViewModel.filter.color = listColor[p2 - 1]
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

            btnDone.setOnClickListener {
                saleViewModel.filterShow()
                toggleDrawer(binding.navigationView)
            }

            btnReset.setOnClickListener {
                clearFilter()
                saleViewModel.filterShow()
                toggleDrawer(binding.navigationView)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n", "NotifyDataSetChanged")
    private fun configListView() {
        binding.rcvProduct.layoutManager = LinearLayoutManager(requireContext())

        saleViewModel.listProductShow.observe(viewLifecycleOwner, Observer {
            binding.rcvProduct.adapter = ProductAdapter(it) { saleItemClick(it) }
        })

        sharedViewModel.listItemSelected.observe(viewLifecycleOwner, Observer {
            binding.tvCountProduct.text = sharedViewModel.getCount().toString()

            if (it.size >= 1) {
                binding.linearQuantity.background =
                    requireContext().getDrawable(R.drawable.custom_background)
                binding.btnRefresh.background =
                    requireContext().getDrawable(R.drawable.custom_button)
                binding.tvCountProduct.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvBillProduct.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvBillProduct.text =
                    "Total " + sharedViewModel.getTotalPrice().toString()
            } else {
                binding.linearQuantity.background =
                    requireContext().getDrawable(R.drawable.custom_background_none)
                binding.btnRefresh.background =
                    requireContext().getDrawable(R.drawable.custom_button_none)
                binding.tvBillProduct.text = "Not yet selected item"
                binding.tvCountProduct.setTextColor(Color.GRAY)
                binding.tvBillProduct.setTextColor(Color.GRAY)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun configureOtherView() {
        binding.tvCustomer.isSelected = true

        binding.btnRefresh.setOnClickListener {
            sharedViewModel.clearListItemSelected()
        }

        sharedViewModel.inforShip.observe(viewLifecycleOwner, Observer {
            val tvCustomer = binding.tvCustomer
            tvCustomer.isSingleLine = true
            if (it.receiver != null && it.tel != null) {
                tvCustomer.text = it.receiver.toString() + "_" + it.tel.toString()
            } else {
                tvCustomer.text = "Customer name, phone number"
            }

        })
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
        val rcvColorClick = bottomSheetView.rcvColor.bindingCustomRecyclerView.cvRcvRecyclerview

        sharedViewModel.updateItemSelected(itemProduct)

        btAdd.setOnClickListener {
            sharedViewModel.updateItemSelectedQuantity(1)
        }

        btRemove.setOnClickListener {
            if (sharedViewModel.itemSelected.value!!.countPackage == 1) {
                requireContext().showToast("Quantity must be more than 0. Please check again")
            } else {
                sharedViewModel.updateItemSelectedQuantity(-1)
            }
        }

        sharedViewModel.itemSelected.observe(viewLifecycleOwner, Observer {
            tvItemQuantity.text = it.countPackage.toString()
            tvItemName.text = it.namePackage
            tvItemId.text = it.codePackage
        })

        rcvColorClick.adapter = CustomRCVAdapter(saleViewModel.getColor(itemProduct)) {
            sharedViewModel.updateListItemSelected()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(bottomSheetView.root)
//
//        bottomSheetDialog.setOnDismissListener {
//            sharedViewModel.updateListItemSelected()
//        }

        bottomSheetDialog.show()

    }

}

