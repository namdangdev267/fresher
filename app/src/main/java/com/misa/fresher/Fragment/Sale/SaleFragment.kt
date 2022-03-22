package com.misa.fresher.fragment.sale

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
import com.misa.fresher.models.Product
import com.misa.fresher.PublicViewModel
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentSaleBinding
import com.misa.fresher.showToast
import kotlinx.android.synthetic.main.custom_search_view.view.*
import kotlinx.android.synthetic.main.filter_drawer.view.*
import kotlinx.android.synthetic.main.fragment_sale.view.*
import kotlinx.android.synthetic.main.sale_context.view.linearQuantity

class SaleFragment : Fragment() {

    private val binding: FragmentSaleBinding by lazy { getInflater(layoutInflater) }
    private val sharedViewModel: PublicViewModel by activityViewModels()
    private val saleViewModel: SaleViewModel by viewModels()

    val getInflater: (LayoutInflater) -> FragmentSaleBinding get() = FragmentSaleBinding::inflate

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
        saleViewModel.initData()
    }

    private fun configureToolbar() {

        binding.root.filterDrawer.btn_done.setOnClickListener {
            toggleDrawer(binding.navigationView)
        }

        binding.root.filterDrawer.btn_reset.setOnClickListener {
            toggleDrawer(binding.navigationView)
        }

        binding.searchviewSale.imageview_search_icon1.setOnClickListener {
            (activity as MainActivity).toggleDrawer((activity as MainActivity).binding.navSaleFragment)
        }

        binding.searchviewSale.imageview_search_icon3.setOnClickListener {
            toggleDrawer(binding.navigationView)
        }

        binding.searchviewSale.edittext_search_hint.doAfterTextChanged {
            saleViewModel.updateListItemShow(it.toString())
        }
    }

    private fun configureFilterDrawer() {
        binding.root.setScrimColor(Color.TRANSPARENT)
        binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.navigationView)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n", "NotifyDataSetChanged")
    private fun configListView() {
        binding.rcvProduct.layoutManager = LinearLayoutManager(requireContext())

        saleViewModel.listItemShow.observe(viewLifecycleOwner, Observer {
            binding.rcvProduct.adapter = it.let { it1 ->
                ProductAdapter(it1) { saleItemClick(it) }
            }
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
        binding.btnRefresh.setOnClickListener {
            sharedViewModel.clearListItemSelected()
        }

        sharedViewModel.inforShip.observe(viewLifecycleOwner, Observer {
            val tvCustomer = binding.tvCustomer
            tvCustomer.isSelected = true
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
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetView: View = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottom_sheet_product,
            this.view as DrawerLayout, false
        )

        val tvItemName = bottomSheetView.findViewById<TextView>(R.id.tvNameProduct)
        val tvItemId = bottomSheetView.findViewById<TextView>(R.id.tvCodeProduct)
        val tvItemQuantity = bottomSheetView.findViewById<TextView>(R.id.tvCountProduct)
        val btAdd = bottomSheetView.findViewById<ImageView>(R.id.imgAdd)
        val btRemove = bottomSheetView.findViewById<ImageView>(R.id.imgRemove)

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
            tvItemName.text = it.nameProduct
            tvItemId.text = it.idProduct
        })

        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetDialog.setOnDismissListener {
            sharedViewModel.updateListItemSelected()
        }

        bottomSheetDialog.show()

    }

}

