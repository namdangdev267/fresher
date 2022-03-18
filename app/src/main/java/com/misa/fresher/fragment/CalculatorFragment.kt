package com.misa.fresher.fragment

import com.misa.fresher.R
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.FragmentCalculatorBinding

class CalculatorFragment : BaseFragment<FragmentCalculatorBinding>(FragmentCalculatorBinding::inflate) {
    override fun initUI() {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.add(R.id.calculator_screen, CalculatorScreenFragment())
        transaction.add(R.id.calculator_keyboard, CalculatorKeyFragment())
        transaction.commit()
    }

    companion object {
        const val FRAGMENT_CALCULATOR = "calculator"
    }
}