package com.misa.fresher.ui.sale.deliveryinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentDeliveryInfoBinding
import com.misa.fresher.ui.sale.adapter.DeliveryInfoAdapter

class DeliveryInfoFragment : Fragment() {

    private var _binding: FragmentDeliveryInfoBinding? = null
    // Chỉ sử dụng ở giữa onCreateView và onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeliveryInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        /*
        binding?.tlDeliveryInfo.setSelectedTabIndicator(resources.getDrawable(R.drawable.box_round))
        TabLayoutMediator(binding?.tlDeliveryInfo, binding?.vpDeliveryInfo) { tab, position ->
            when (position) {
                0 -> tab.text = "Người nhận"
                1 -> tab.text = "Giao hàng"
                else -> tab.text = "Gói hàng"
            }
        }.attach()
        binding?.vpDeliveryInfo.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding?.rbReceiver.setTextColor(resources.getColorById(R.color.white))
                binding?.rbShip.setTextColor(resources.getColorById(R.color.white))
                binding?.rbPackage.setTextColor(resources.getColorById(R.color.white))
                when (position) {
                    0 -> {
                        binding?.rbReceiver.isChecked = true
                        binding?.rbReceiver.setTextColor(resources.getColorById(R.color.primary))
                    }
                    1 -> {
                        binding?.rbShip.isChecked = true
                        binding?.rbShip.setTextColor(resources.getColorById(R.color.primary))
                    }
                    else -> {
                        binding?.rbPackage.isChecked = true
                        binding?.rbPackage.setTextColor(resources.getColorById(R.color.primary))
                    }
                }
            }
        })
        binding?.rbReceiver.setOnCheckedChangeListener { _, b ->
            if (b) {
                binding?.vpDeliveryInfo.currentItem = 0
            }
        }
        binding?.rbShip.setOnCheckedChangeListener { _, b ->
            if (b) {
                binding?.vpDeliveryInfo.currentItem = 1
            }
        }
        binding?.rbPackage.setOnCheckedChangeListener { _, b ->
            if (b) {
                binding?.vpDeliveryInfo.currentItem = 2
            }
        }
         */
    }

    private fun setupToolbar() {
        binding.tbDeliveryInfo.root.inflateMenu(R.menu.menu_delivery_info)
        binding.tbDeliveryInfo.tvTitle.text = "Thông tin giao hàng"
        binding.tbDeliveryInfo.btnNav.setImageDrawable(resources.getDrawable(R.drawable.ic_arrow_back, null))
        binding.tbDeliveryInfo.btnNav.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}