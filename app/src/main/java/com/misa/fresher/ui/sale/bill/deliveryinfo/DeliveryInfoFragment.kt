package com.misa.fresher.ui.sale.bill.deliveryinfo

import android.view.LayoutInflater
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.databinding.FragmentDeliveryInfoBinding
import com.misa.fresher.ui.MainActivity
import com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.DeliveryInfoAdapter

/**
 * Màn hình thông tin giao hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
class DeliveryInfoFragment: BaseFragment<FragmentDeliveryInfoBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentDeliveryInfoBinding
        get() = FragmentDeliveryInfoBinding::inflate

    override fun initUI() {
        configToolbar()
        configTabLayout()
        configOtherView()
    }

    /**
     * Cài đặt các view khác
     *
     * @author Nguyễn Công Chính
     * @since 3/15/2022
     *
     * @version 1
     * @updated 3/15/2022: Tạo function
     */
    private fun configOtherView() {
        binding.btnSave.setOnClickListener {
            (activity as MainActivity).tempCustomer?.let {
                activity?.onBackPressed()
            } ?: run {
                Toast.makeText(requireContext(), getString(R.string.please_select_receiver), Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Caì đặt 3 tab trong màn hình
     *
     * @author Nguyễn Công Chính
     * @since 3/15/2022
     *
     * @version 1
     * @updated 3/15/2022: Tạo function
     */
    private fun configTabLayout() {
        binding.vpDeliveryInfo.adapter = DeliveryInfoAdapter(this)

        TabLayoutMediator(binding.tlDeliveryInfo, binding.vpDeliveryInfo) {tab, position ->
            when (position) {
                0 -> {
                    tab.view.setBackgroundResource(R.drawable.selector_button_tab_left)
                    tab.text = getString(R.string.receiver)
                }
                1 -> {
                    tab.view.setBackgroundResource(R.drawable.selector_button_tab_center)
                    tab.text = getString(R.string.ship)
                }
                else -> {
                    tab.view.setBackgroundResource(R.drawable.selector_button_tab_right)
                    tab.text = getString(R.string._package)
                }
            }
        }.attach()
    }

    /**
     * Cài đặt toolbar
     *
     * @author Nguyễn Công Chính
     * @since 3/15/2022
     *
     * @version 1
     * @updated 3/15/2022: Tạo function
     */
    private fun configToolbar() {
        binding.tbDeliveryInfo.root.inflateMenu(R.menu.menu_delivery_info)
        binding.tbDeliveryInfo.tvTitle.text = getString(R.string.delivery_info)
        binding.tbDeliveryInfo.btnNav.setImageResource(R.drawable.ic_arrow_back)
        binding.tbDeliveryInfo.btnNav.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}