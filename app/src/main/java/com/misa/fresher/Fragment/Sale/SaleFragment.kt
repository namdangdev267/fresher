package com.misa.fresher.Fragment.Sale

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.CustomView.ToastCustom
import com.misa.fresher.MainActivity
import com.misa.fresher.Models.Product
import com.misa.fresher.PublicViewModel
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentSaleBinding
import kotlinx.android.synthetic.main.custom_search_view.view.*
import kotlinx.android.synthetic.main.sale_context.view.*

class SaleFragment : Fragment() {

    private val binding: FragmentSaleBinding by lazy { getInflater(layoutInflater) }
    private var sharedViewModel: PublicViewModel? = null
    private var saleViewModel: SaleViewModel? = null

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

        transitionFragment(view)
        configureFilterDrawer()
        configureToolbar()
        configureOtherView()
        configListView()
    }

    private fun transitionFragment(view: View) {

        binding.root.linearQuantity?.setOnClickListener {
            if (sharedViewModel?.listItemSelected?.value!!.size > 0) {
                Navigation.findNavController(view)
                    .navigate(R.id.action_fragment_sale_to_fragment_payment)
            }
        }
    }

    private fun initViewModel() {
        saleViewModel = SaleViewModel()
        saleViewModel!!.initData()
        sharedViewModel = ViewModelProvider(requireActivity()).get(PublicViewModel::class.java)
    }

    private fun configureToolbar() {
        binding.filterDrawer.btnDone.setOnClickListener {
            toggleDrawer(binding.navigationView)
        }

        binding.filterDrawer.btnReset.setOnClickListener {
            toggleDrawer(binding.navigationView)
        }

        binding.searchviewSale.imageview_search_icon1.setOnClickListener {
            (activity as MainActivity).toggleDrawer((activity as MainActivity).binding.navSaleFragment)
        }

        binding.searchviewSale.imageview_search_icon3.setOnClickListener {
            toggleDrawer(binding.navigationView)
        }

        binding.searchviewSale.edittext_search_hint.doAfterTextChanged {
            saleViewModel?.updateListItemShow(it.toString())
        }
    }

    private fun configureFilterDrawer() {
        binding.root.setScrimColor(Color.TRANSPARENT)
        binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.navigationView)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    private fun configListView() {
        binding.rcvProduct.layoutManager = LinearLayoutManager(requireContext())

        saleViewModel?.listItemShow?.observe(viewLifecycleOwner, Observer {
            binding.rcvProduct.adapter = it?.let { it1 ->
                ProductAdapter(it1) { it -> saleItemClick(it) }
            }
        })

        sharedViewModel?.listItemSelected?.observe(viewLifecycleOwner, Observer {
            binding.tvCountProduct.text = sharedViewModel!!.getCount().toString()

            if (it.size >= 1) {
                binding.linearQuantity.background =
                    this.context?.getDrawable(R.drawable.custom_background)
                binding.btnRefresh.background =
                    this.context?.getDrawable(R.drawable.custom_button)
                binding.tvCountProduct.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvBillProduct.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvBillProduct.text =
                    "Total " + sharedViewModel!!.getTotalPrice().toString()
            } else {
                binding.linearQuantity.background =
                    this.context?.getDrawable(R.drawable.custom_background_none)
                binding.btnRefresh.background =
                    this.context?.getDrawable(R.drawable.custom_button_none)
                binding.tvBillProduct.text = "Not yet selected item"
                binding.tvCountProduct.setTextColor(Color.GRAY)
                binding.tvBillProduct.setTextColor(Color.GRAY)
            }
        })
    }

    private fun configureOtherView() {
        binding.btnRefresh.setOnClickListener {
            sharedViewModel?.clearListItemSelected()
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

        sharedViewModel?.updateItemSelected(itemProduct)

        btAdd.setOnClickListener {
            sharedViewModel?.updateItemSelectedQuantity(1)
        }

        btRemove.setOnClickListener {
            if (sharedViewModel?.itemSelected?.value?.countPackage == 1) {
                val customToast = ToastCustom
                customToast.makeToast(
                    requireContext(),
                    "Quantity must be more than 0. Please check again",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                sharedViewModel?.updateItemSelectedQuantity(-1)
            }
        }

        sharedViewModel?.itemSelected?.observe(viewLifecycleOwner, Observer {
            tvItemQuantity.text = it.countPackage.toString()
            tvItemName.text = it.nameProduct
            tvItemId.text = it.idProduct
        })

        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetDialog.setOnDismissListener {
            sharedViewModel?.updateListItemSelected()
        }

        bottomSheetDialog.show()

    }

}

