package com.misa.fresher.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.misa.fresher.Adapter.CalculatorKeyAdapter
import com.misa.fresher.Model.CalculatorKey
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentCalculatorKeyboardBinding

class CalculatorKeyFragment : Fragment() {
    private var binding: FragmentCalculatorKeyboardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorKeyboardBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fakeData()
    }

    private fun fakeData() {
        val calculatorKeyboardRecView = binding?.calculatorKeyboardRecView

        val keys = arrayListOf(
            "C", "Down", "Up", "",
            "7", "8", "9", "-",
            "4", "5", "6", "+",
            "1", "2", "3", "+-",
            "0", "000", ".", "DONE",
        ).map { text ->
            CalculatorKey(text, if (text == "") R.drawable.ic_backspace_red else 0, text == "DONE")
        } as ArrayList<CalculatorKey>

        if (calculatorKeyboardRecView != null) {
            calculatorKeyboardRecView.adapter = CalculatorKeyAdapter(keys) { onKeyClick(it) }
            calculatorKeyboardRecView.layoutManager = GridLayoutManager(activity, 4)
        }
    }

    private fun onKeyClick(key: CalculatorKey) {
        setFragmentResult("calculator_key", bundleOf("input" to key.value))
    }
}