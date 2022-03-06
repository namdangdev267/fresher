package com.misa.fresher.ui.sale.deliveryinfo

import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.databinding.FragmentDeliveryInfoBinding
import com.misa.fresher.ui.sale.adapter.DeliveryInfoAdapter

class DeliveryInfoFragment : BaseFragment<FragmentDeliveryInfoBinding>() {

    override val getInflater: (inflater: LayoutInflater) -> FragmentDeliveryInfoBinding =
        FragmentDeliveryInfoBinding::inflate

    override fun initUI() {
        setupToolbar()

        binding.vpDeliveryInfo.adapter = DeliveryInfoAdapter(this)

        /*ViewPagerWithTab.setup(
            binding.vpDeliveryInfo,
            arrayOf(binding.rbReceiver, binding.rbShip, binding.rbPackage),
            resources.getColorById(R.color.white),
            resources.getColorById(R.color.primary)
        )*/

        TabLayoutMediator(binding.tlDeliveryInfo, binding.vpDeliveryInfo) {tab, position ->
            when (position) {
                0 -> {
                    tab.view.setBackgroundResource(R.drawable.selector_button_tab_left)
                    tab.text = "Người nhận"
                }
                1 -> {
                    tab.view.setBackgroundResource(R.drawable.selector_button_tab_center)
                    tab.text = "Giao hàng"
                }
                else -> {
                    tab.view.setBackgroundResource(R.drawable.selector_button_tab_right)
                    tab.text = "Gói hàng"
                }
            }
        }.attach()
    }

    private fun setupToolbar() {
        binding.tbDeliveryInfo.root.inflateMenu(R.menu.menu_delivery_info)
        binding.tbDeliveryInfo.tvTitle.text = "Thông tin giao hàng"
        binding.tbDeliveryInfo.btnNav.setImageDrawable(resources.getDrawable(R.drawable.ic_arrow_back, null))
        binding.tbDeliveryInfo.btnNav.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}