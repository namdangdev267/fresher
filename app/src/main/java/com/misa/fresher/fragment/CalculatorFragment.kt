package com.misa.fresher.fragment

import android.os.Bundle
import android.view.View
import com.misa.fresher.R
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.FragmentCalculatorBinding

class CalculatorFragment : BaseFragment<FragmentCalculatorBinding>(
    FragmentCalculatorBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.beginTransaction()
            .replace(R.id.calculator_screen, CalculatorScreenFragment())
            .replace(R.id.calculator_keyboard, CalculatorKeyFragment())
            .commit()
    }
}