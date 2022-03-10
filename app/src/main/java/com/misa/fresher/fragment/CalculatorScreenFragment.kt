package com.misa.fresher.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.misa.fresher.base.BaseFragment
import com.misa.fresher.databinding.FragmentCalculatorScreenBinding

class CalculatorScreenFragment: BaseFragment<FragmentCalculatorScreenBinding>(
    FragmentCalculatorScreenBinding::inflate
) {
    var value: Double = 0.0
    var text: String = "0"
        set(value) {
            field = value
            binding.result.text = field
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.setOnClickListener {
            findNavController().navigateUp()
        }
        setFragmentResultListener("calculator_key") { _, bundle ->
            keyPress(bundle.getString("input"))
        }
    }

    private fun keyPress (key: String?) {
        when(key) {
            "C" -> { // clear
                value = 0.0
                text = "0"
            }
            "Down" -> { // -1
                calculate()
                value -= 1
                text = value.toString()
            }
            "Up" -> { // +1
                calculate()
                value += 1
                text = value.toString()
            }
            "" -> { // backspace
                text = text.dropLast(1)
                if(text == "") {
                    value = 0.0
                    text = "0"
                }
            }
            "+-" -> {
                calculate()
                value *= -1
                text = value.toString()
            }
            "+" -> {
                val lastChar = text[text.length - 1]
                if(lastChar == '+' || lastChar == '-' || lastChar == '.') { text = text.dropLast(1) }
                text += "+"
            }
            "-" -> {
                val lastChar = text[text.length - 1]
                if(lastChar == '+' || lastChar == '-' || lastChar == '.') { text =  text.dropLast(1) }
                text += "-"
            }
            "." -> {
                val lastChar = text[text.length - 1]
                if(lastChar == '+' || lastChar == '-' || lastChar == '.') { text =  text.dropLast(1) }
                val nums = text.split("+", "-")
                val lastNum = nums[nums.size - 1]
                if(!lastNum.contains('.')) text += "."
            }
            "000" -> {
                val lastChar = text[text.length - 1]
                if(lastChar == '+' || lastChar == '-') { text += "0" }
                else if(lastChar == '.') text =  text.dropLast(1)
                else {
                    val nums = text.split("+", "-")
                    val lastNum = nums[nums.size - 1]
                    if(!lastNum.contains('.')) text += "000"
                }
            }
            "DONE" -> {
                calculate()
            }
            else -> {
                if(text == "0") text = key ?: "0"
                else text += key
            }
        }
    }

    private fun calculate () {
        val lastChar = text[text.length - 1]
        if(lastChar == '+' || lastChar == '-' || lastChar == '.') { text =  text.dropLast(1) }

        var result = 0.0
        text.split("+").forEach { expression ->
            val numbers = expression.split("-")
            var exResult: Double = numbers[0].toDoubleOrNull() ?: 0.0
            for (i in 1 until numbers.size) exResult -= numbers[i].toDouble()
            result += exResult
        }

        value = result
        text = if(value.toLong().toDouble() == value) value.toLong().toString() else value.toString()
    }
}