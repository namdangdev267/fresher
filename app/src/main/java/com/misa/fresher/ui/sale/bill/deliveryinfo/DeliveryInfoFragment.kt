package com.misa.fresher.ui.sale.bill.deliveryinfo

import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.entity.Customer
import com.misa.fresher.databinding.FragmentDeliveryInfoBinding
import com.misa.fresher.ui.MainActivity
import com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.DeliveryInfoAdapter
import com.misa.fresher.util.toast

/**
 * Màn hình thông tin giao hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 2
 * @updated 3/15/2022: Tạo class
 * @updated 3/16/2022: Nút lưu sẽ lưu lại các giá trị đã nhập thay vì làm cảnh như trước
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
     * @updated 3/16/2022: Khi nhấn lưu sẽ thu thập dữ liệu từ các fragment và lưu lại
     */
    private fun configOtherView() {
        binding.btnSave.setOnClickListener {
            (activity as MainActivity).tempCustomer?.let {
                val receiver =
                    (childFragmentManager.findFragmentByTag("f${binding.vpDeliveryInfo.currentItem}") as ReceiverInfoFragment)
                val data = receiver.collectData()
                (activity as MainActivity).tempCustomer = Customer(
                    it.id,
                    it.name,
                    data.tel,
                    data.address
                )
                activity?.onBackPressed()
            } ?: run {
                toast(requireContext(), R.string.please_select_receiver)
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