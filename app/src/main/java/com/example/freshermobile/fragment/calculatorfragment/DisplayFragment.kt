package com.example.freshermobile.fragment.calculatorfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.freshermobile.R


class DisplayFragment : Fragment() {

    private lateinit var tvDisplay: TextView
    private lateinit var tvResult: TextView
    private var num1: Int = 0
    private var num2: Int = 0
    private var result: Int = 0
    private var dau: String = ""
    private var count: Int = 3
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvDisplay = view.findViewById(R.id.text_display)
        tvResult = view.findViewById(R.id.text_result)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    fun receiveDataFromFragment(content: String) {
        when (count) {
            3 -> {
                if (checkNumber(content)) {
                    num1 = Integer.parseInt(content)
                    tvDisplay.text = num1.toString()
                    count -= 1
                }
                if (checkDau(content)) {
                    tvDisplay.text = "0"
                    count = 3
                }
                checkAC(content)
            }
            2 -> {
                if (checkNumber(content)) {
                    num1 = Integer.parseInt(content)
                    tvDisplay.text = num1.toString()
                    count = 2
                }
                if (checkDau(content)) {
                    dau = content
                    tvDisplay.text = tvDisplay.text.toString().plus(dau)
                    count -= 1
                }
                checkAC(content)
            }
            1 -> {
                if (checkNumber(content)) {
                    num2 = Integer.parseInt(content)
                    tvDisplay.text = tvDisplay.text.toString().plus(num2)
                    count -= 1
                }
                checkAC(content)
            }
            0 -> {
                checkAC(content)
                if (content == "=") {
                    tvResult.text = calculate()
                    count = 3
                }
            }
        }
    }


    private fun calculate(): String {
        when (dau) {
            "*" -> {
                result = num1 * num2
            }
            "/" -> {
                result = num1 / num2
            }
            "-" -> {
                result = num1 - num2
            }
            "+" -> {
                result = num1 + num2
            }
        }
        return result.toString()
    }

    private fun checkAC(content: String) {
        if (content == "AC") {
            tvDisplay.text = "0"
            tvResult.text = "0"
            count = 3
        }
    }

    private fun checkNumber(content: String): Boolean {
        return (content == "0" || content == "1" || content == "2" || content == "3" || content == "4"
                || content == "5" || content == "6" || content == "7" || content == "8" || content == "9")
    }

    private fun checkDau(content: String): Boolean {
        return content == "+" || content == "-" || content == "/" || content == "*"
    }
}