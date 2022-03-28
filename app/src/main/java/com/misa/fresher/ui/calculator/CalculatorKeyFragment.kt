package com.misa.fresher.ui.calculator

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.misa.fresher.R
import com.misa.fresher.ui.calculator.adapter.CalculatorKeyAdapter
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.FragmentCalculatorKeyboardBinding
import com.misa.fresher.data.model.calculator.CalculatorKey

class CalculatorKeyFragment : BaseFragment<FragmentCalculatorKeyboardBinding>(FragmentCalculatorKeyboardBinding::inflate) {

    override fun initUI() {
        val keys = fakeData()

        val calculatorKeyboardRecView = binding.calculatorKeyboardRecView
        calculatorKeyboardRecView.adapter = CalculatorKeyAdapter(keys) { it, _ -> onKeyClick(it) }
        calculatorKeyboardRecView.layoutManager = GridLayoutManager(activity, 4)
    }

    private fun fakeData(): ArrayList<CalculatorKey> {
        return arrayListOf(
            "C", "Down", "Up", "",
            "7", "8", "9", "-",
            "4", "5", "6", "+",
            "1", "2", "3", "+-",
            "0", "000", ".", "DONE",
        ).map { k -> CalculatorKey(k, if (k == "") R.drawable.ic_backspace_red else 0, k == "DONE") } as ArrayList<CalculatorKey>
    }

    private fun onKeyClick(key: CalculatorKey) {
        setFragmentResult("calculator", bundleOf("input" to key.value))
    }

    companion object {
        const val BUNDLE_CALCULATOR_INPUT = "input"
    }
}