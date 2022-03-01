package com.misa.fresher.ui.sale.deliveryinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentDeliveryInfoBinding
import com.misa.fresher.ui.sale.adapter.DeliveryInfoAdapter
import com.misa.fresher.util.ViewPagerWithTab
import com.misa.fresher.util.getColorById

class DeliveryInfoFragment : Fragment() {

    private var binding: FragmentDeliveryInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeliveryInfoBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.vpDeliveryInfo.adapter = DeliveryInfoAdapter(this)

        ViewPagerWithTab.setup(
            binding!!.vpDeliveryInfo,
            arrayOf(binding!!.rbReceiver, binding!!.rbShip, binding!!.rbPackage),
            resources.getColorById(R.color.white),
            resources.getColorById(R.color.primary)
        )

        /*
        binding!!.tlDeliveryInfo.setSelectedTabIndicator(resources.getDrawable(R.drawable.box_round))
        TabLayoutMediator(binding!!.tlDeliveryInfo, binding!!.vpDeliveryInfo) { tab, position ->
            when (position) {
                0 -> tab.text = "Người nhận"
                1 -> tab.text = "Giao hàng"
                else -> tab.text = "Gói hàng"
            }
        }.attach()
        binding!!.vpDeliveryInfo.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding!!.rbReceiver.setTextColor(resources.getColorById(R.color.white))
                binding!!.rbShip.setTextColor(resources.getColorById(R.color.white))
                binding!!.rbPackage.setTextColor(resources.getColorById(R.color.white))
                when (position) {
                    0 -> {
                        binding!!.rbReceiver.isChecked = true
                        binding!!.rbReceiver.setTextColor(resources.getColorById(R.color.primary))
                    }
                    1 -> {
                        binding!!.rbShip.isChecked = true
                        binding!!.rbShip.setTextColor(resources.getColorById(R.color.primary))
                    }
                    else -> {
                        binding!!.rbPackage.isChecked = true
                        binding!!.rbPackage.setTextColor(resources.getColorById(R.color.primary))
                    }
                }
            }
        })
        binding!!.rbReceiver.setOnCheckedChangeListener { _, b ->
            if (b) {
                binding!!.vpDeliveryInfo.currentItem = 0
            }
        }
        binding!!.rbShip.setOnCheckedChangeListener { _, b ->
            if (b) {
                binding!!.vpDeliveryInfo.currentItem = 1
            }
        }
        binding!!.rbPackage.setOnCheckedChangeListener { _, b ->
            if (b) {
                binding!!.vpDeliveryInfo.currentItem = 2
            }
        }
         */
    }
}