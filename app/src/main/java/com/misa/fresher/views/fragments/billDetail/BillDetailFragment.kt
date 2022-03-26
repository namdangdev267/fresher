package com.misa.fresher.views.fragments.billDetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.misa.fresher.models.ItemBillDetail
import com.misa.fresher.R
import com.misa.fresher.views.customViews.CustomToast
import com.misa.fresher.views.fragments.SharedViewModel
import com.misa.fresher.databinding.FragmentBillDetailBinding
import com.misa.fresher.getNumString

class BillDetailFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val binding:FragmentBillDetailBinding by lazy {
        getInflater(layoutInflater)
    }

    val getInflater: (LayoutInflater) -> FragmentBillDetailBinding
        get() = FragmentBillDetailBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transitionFragment(view)
        configToolbar()
        configListView()
        configOtherView(view)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Navigation.findNavController(view)
                    .navigate(R.id.action_billDetailFragment_to_saleFragment)
            }
        })
    }

    private fun configToolbar() {
        binding.tvBillDetailId.text = sharedViewModel.billHandling.value?.id?.getNumString()!!.substring(10)
    }

    private fun transitionFragment(view: View) {
        binding.llGetPayment.setOnClickListener {

            Navigation.findNavController(view)
                .navigate(R.id.action_billDetailFragment_to_saleFragment)
            sharedViewModel.addBillToListBill()

            CustomToast.makeText(this.context!!,"Paid Successfully")

        }

        binding.imBillDetailBack.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_billDetailFragment_to_saleFragment)

        }

        binding.ivBillDetailShip.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_billDetailFragment_to_shippingInformationFragment)
        }

        binding.tvBuyMore.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_billDetailFragment_to_saleFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun configOtherView(view: View) {
        sharedViewModel.infoShip.observe(viewLifecycleOwner, Observer {
            if(it.receiver!=null && it.tel!=null) {
                binding.tvBillDetailCustomerInfor.text =
                    it.receiver.toString() + " - " + it.tel.toString()
            }
            else
            {
                binding.tvBillDetailCustomerInfor.text ="Customer name, phone number"
            }
        })

    }

    private fun configListView() {

        sharedViewModel.listItemSelected.observe(viewLifecycleOwner, Observer { it ->
            binding.recyclerviewBillDetail.adapter =
                BillDetailAdapter(it) { clickItemBillDetail(it) }
            binding.tvBillDetailTotalQuantity.text =it.size.toString()
            binding.tvBillDetailTotalPrice.text = sharedViewModel.getTotalPrice().toString()
        })
    }

    private fun clickItemBillDetail(itemBillDetail: ItemBillDetail) {
        sharedViewModel.updateQuantityOfItemBillDetail(itemBillDetail)
    }

}