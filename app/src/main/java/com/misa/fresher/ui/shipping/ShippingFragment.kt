package com.misa.fresher.ui.shipping

import android.view.LayoutInflater
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.databinding.FragmentShippingBinding

class ShippingFragment : BaseFragment<FragmentShippingBinding>() {

    override val getInflater: (inflater: LayoutInflater) -> FragmentShippingBinding =
        FragmentShippingBinding::inflate
}