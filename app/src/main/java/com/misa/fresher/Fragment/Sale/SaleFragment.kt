package com.misa.fresher.Fragment.Sale

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.CustomView.ToastCustom
import com.misa.fresher.MainActivity
import com.misa.fresher.Models.Product
import com.misa.fresher.PublicViewModel
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentSaleBinding
import kotlinx.android.synthetic.main.search_view.view.*

class SaleFragment : Fragment() {

    lateinit var binding: FragmentSaleBinding
    lateinit var sharedViewModel: PublicViewModel
    lateinit var saleViewModel: SaleViewModel
    lateinit var bottomSheetDialog: BottomSheetDialog

//    var createProductData = Product.createProductList(20)
//    var rcvProduct: RecyclerView? = null
//    lateinit var productViewModel: ProductViewModel
//    var billList = mutableListOf<Product>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
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

        transitionFragment(view)
        configureFilterDrawer()
        configureToolbar()
        configureOtherView()
        configListView()

//        configureDrawerFilter()
//        configureRecyclerView()
//        updateItemSeleted()
//        onClickEvent()
//        searchEvent()
//        billListEvent()
    }

    private fun transitionFragment(view: View) {
        binding.linearQuantity.setOnClickListener {
            if (sharedViewModel.listItemSelected.value!!.size > 0) {
//        Navigation.findNavController(view).navigate(R.id.action_fragment_sale_to_fragment_payment)
                findNavController().navigate(R.id.action_fragment_sale_to_fragment_payment)
            }
        }
    }

    private fun initViewModel() {
        saleViewModel = SaleViewModel()
        saleViewModel.initData()

        sharedViewModel = ViewModelProvider(requireActivity()).get(PublicViewModel::class.java)
    }

    private fun configureToolbar(){

        val edtSearch = binding.root.edtSearch

        edtSearch.doAfterTextChanged {
            saleViewModel.updateListItemShow(it.toString())
        }
    }

    private fun configureFilterDrawer() {
        binding.root.setScrimColor(Color.TRANSPARENT)
        binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.navigationView)

        binding.filterDrawer.btnDone.setOnClickListener {
            toggleDrawer(binding.navigationView)
        }

        binding.toolbarSale.root.inflateMenu(R.menu.menu_main)

        binding.toolbarSale.btnNav.setOnClickListener {
            (activity as MainActivity).toggleDrawer()
        }

        binding.toolbarSale.root.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.btnFilter -> {
                    toggleDrawer(binding.navigationView)
                }
            }
            true
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    private fun configListView() {
        binding.rcvProduct.layoutManager = LinearLayoutManager(requireContext())

        saleViewModel.listItemShow.observe(viewLifecycleOwner, Observer {
            binding.rcvProduct.adapter = it?.let {
                it1 -> ProductAdapter(it1) {it -> saleItemClick(it)}
            }
        })

        sharedViewModel.listItemSelected.observe(viewLifecycleOwner, Observer {
            binding.tvCountProduct.text = it.size.toString()

            if (it.size >= 1) {
                binding.linearQuantity.background = this.context?.getDrawable(R.drawable.custom_background)
                binding.btnRefresh.background = this.context?.getDrawable(R.drawable.custom_button)
                binding.tvCountProduct.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvBillProduct.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvBillProduct.text = "Total " + sharedViewModel.getTotalPrice().toString()
            } else {
                binding.linearQuantity.background = this.context?.getDrawable(R.drawable.custom_background_none)
                binding.btnRefresh.background = this.context?.getDrawable(R.drawable.custom_button_none)
                binding.tvBillProduct.text = "Not yet selected item"
                binding.tvCountProduct.setTextColor(Color.GRAY)
                binding.tvBillProduct.setTextColor(Color.GRAY)
            }
        })
    }

    private fun configureOtherView(){
        binding.btnRefresh.setOnClickListener {
            sharedViewModel.clearListItemSelected()
        }
    }

//    private fun configureToolbar() {
//        binding.toolbarSale.root.inflateMenu(R.menu.menu_main)
//
//        binding.toolbarSale.btnNav.setOnClickListener {
//            (activity as MainActivity).toggleDrawer()
//        }
//
//        binding.toolbarSale.root.setOnMenuItemClickListener { item ->
//            when (item.itemId) {
//                R.id.btnFilter -> {
//                    toggleDrawer(binding.navigationView)
//                }
//            }
//            true
//        }
//    }

    private fun toggleDrawer(view: View) {
        if (binding.root.isDrawerOpen(view)) {
            binding.root.closeDrawer(view)
        } else {
            binding.root.openDrawer(view)
        }
    }

    private fun  saleItemClick(itemProduct: Product) {
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetView: View = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottom_sheet_product,
            this.view as DrawerLayout, false
        )

        val tvItemName = bottomSheetView.findViewById<TextView>(R.id.tvNameProduct)
        val tvItemId = bottomSheetView.findViewById<TextView>(R.id.tvCodeProduct)
        val tvItemQuantity = bottomSheetView.findViewById<TextView>(R.id.tvCountProduct)

//        val recyclerView = bottomSheetView.findViewById<CustomRecyclerView>(R.id.cv_rcv)
//            .findViewById<RecyclerView>(R.id.cv_rcv_recyclerview)

        val btAdd = bottomSheetView.findViewById<ImageView>(R.id.imgAdd)
        val btRemove = bottomSheetView.findViewById<ImageView>(R.id.imgRemove)

        sharedViewModel.updateItemSelected(itemProduct)


        btAdd.setOnClickListener {
            sharedViewModel.updateItemSelectedQuantity(1)
        }

        btRemove.setOnClickListener {
            if (sharedViewModel.itemSelected.value?.countPackage == 1) {
                var customToast = ToastCustom(requireContext())
                customToast.makeToast(
                    requireContext(),
                    "Quantity must be more than 0. Please check again",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                sharedViewModel.updateItemSelectedQuantity(-1)
            }
        }

        sharedViewModel.itemSelected.observe(viewLifecycleOwner, Observer {
            tvItemQuantity.text = it.countPackage.toString()
            tvItemName.text = it.namePackage
            tvItemId.text = it.codePackage
        })

        sharedViewModel.updateListItemSelected()
        bottomSheetDialog.dismiss()

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    fun clickColor(color: com.misa.fresher.Models.Enum.Color) {
        Log.e(
            this.javaClass.simpleName,
            sharedViewModel.itemSelected.value?.product?.nameProduct.toString()
        )
        sharedViewModel.updateListItemSelected()
        Log.e(this.javaClass.simpleName, sharedViewModel.listItemSelected.value?.size.toString())
        bottomSheetDialog.dismiss()
    }

//    private fun configureDrawerFilter() {
//        binding.root.setScrimColor(Color.TRANSPARENT)
//        binding.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.navigationView)
//    }
//
//    @SuppressLint("ResourceAsColor")
//    private fun billListEvent() {
//        binding.btnRefresh.setOnClickListener {
//            billList.clear()
//
//            it.background =
//                AppCompatResources.getDrawable(requireContext(), R.drawable.custom_button_none)
//
//            binding.tvCountProduct.text = "0"
//            binding.tvCountProduct.setTextColor(Color.GRAY)
//            binding.tvBillProduct.text = "Not yet selected item"
//            binding.tvBillProduct.setTextColor(Color.GRAY)
//
//            binding.linearQuantity.background =
//                AppCompatResources.getDrawable(requireContext(), R.drawable.custom_background_none)
//        }
//    }
//
//    private fun searchEvent() {
//        val edtSearch = binding.root.edtSearch
//        binding.root.edtSearch.doAfterTextChanged {
//            updateItemList(edtSearch.text.toString())
//        }
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    private fun updateItemList(string: String) {
//        var listProduct = mutableListOf<Product>()
//
//        for (i in createProductData) {
//            if (i.nameProduct.contains(string) || i.codeProduct.contains(string)) {
//                listProduct.add(i)
//            }
//        }
//
//        rcvProduct?.adapter = ProductAdapter(listProduct, { clickItemProduct(it) })
//        (rcvProduct?.adapter)?.notifyDataSetChanged()
//    }
//
//    private fun onClickEvent() {
//        binding.linearQuantity.setOnClickListener {
//            if (billList.size != 0) {
//                findNavController().navigate(
//                    R.id.action_fragment_sale_to_fragment_payment
//                )
//            }
////            if (sharedViewModel.listItemSelected.value!!.size > 0) {
////                findNavController().navigate(R.id.action_fragment_sale_to_fragment_payment)
////            }
//        }
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    private fun configureRecyclerView() {
//        val setAdapter = ProductAdapter(createProductData, { clickItemProduct(it) })
//        setAdapter.notifyDataSetChanged()
//        rcvProduct = binding.rcvProduct
//        rcvProduct?.adapter = setAdapter
//        rcvProduct?.layoutManager = LinearLayoutManager(requireContext())
//    }
//
//    private fun configOtherView() {
//        binding.btnRefresh.setOnClickListener {
//            sharedViewModel.clearListItemSelected()
//        }
//    }
//
//    private fun configSearchView() {
//
//        var editText = binding.toolbarSale.root.edtSearch
//
//        binding.root.edtSearch.doAfterTextChanged {
//            saleViewModel
//            Log.e(this.javaClass.simpleName, it.toString())
//        }
//    }
//
//
//    fun clickColor(color: Color) {
//        sharedViewModel.updateListItemSelected()
//        bottomSheetDialog.dismiss()
//    }
//
//
//    private fun clickItemProduct(product: Product) {
//        billList.add(product)
//
//        configureBottomSheetDialog(product)
//
//        updateItemSeleted()
//    }
//
//    private fun configureBottomSheetDialog(product: Product) {
//        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
//        val bottomSheetView: View = LayoutInflater.from(requireContext()).inflate(
//            R.layout.bottom_sheet_product,
//            binding.root, false
//        )
//        bottomSheetView.findViewById<TextView>(R.id.tvNameProduct).text = product.nameProduct
//        bottomSheetView.findViewById<TextView>(R.id.tvCodeProduct).text = product.codeProduct
//
//        val tvCountProductView = bottomSheetView.findViewById<TextView>(R.id.tvCountProduct)
//        bottomSheetView.findViewById<ImageView>(R.id.imgRemove).setOnClickListener {
//            val count = tvCountProductView.text.toString().toInt() - 1
//            if (count == 0) {
//                val toastCustom = ToastCustom(requireContext())
//                toastCustom.makeToast(
//                    requireContext(),
//                    "Quantity must be more than 0. Please check again.",
//                    Toast.LENGTH_LONG
//                ).show()
//            } else {
//                tvCountProductView.text = count.toString()
//            }
//        }
//
//        bottomSheetView.findViewById<ImageView>(R.id.imgAdd).setOnClickListener {
//            val count = tvCountProductView.text.toString().toInt() + 1
//            tvCountProductView.text = count.toString()
//        }
//        bottomSheetDialog.setContentView(bottomSheetView)
//        bottomSheetDialog.show()
//    }
//
//    private fun updateItemSeleted() {
//        var totalPrice = 0.0
//
//        for (i in billList) {
//            totalPrice += i.priceProduct
//        }
//
//        if (billList.size > 0) {
//            binding.tvCountProduct.text = "${billList.size}"
//            binding.tvCountProduct.setTextColor(Color.WHITE)
//            binding.tvBillProduct.text = "Total $totalPrice"
//            binding.tvBillProduct.setTextColor(Color.WHITE)
//            binding.linearQuantity.background =
//                AppCompatResources.getDrawable(requireContext(), R.drawable.custom_background)
//            binding.btnRefresh.background =
//                AppCompatResources.getDrawable(requireContext(), R.drawable.custom_button)
//        }
//
//    }

}

