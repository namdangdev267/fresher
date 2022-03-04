package com.example.freshermobile.fragment.calculatorfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.freshermobile.R

class CalculateFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn0 = view.findViewById<Button>(R.id.btn_0)
        val btn1 = view.findViewById<Button>(R.id.btn_1)
        val btn2 = view.findViewById<Button>(R.id.btn_2)
        val btn3 = view.findViewById<Button>(R.id.btn_3)
        val btn4 = view.findViewById<Button>(R.id.btn_4)
        val btn5 = view.findViewById<Button>(R.id.btn_5)
        val btn6 = view.findViewById<Button>(R.id.btn_6)
        val btn7 = view.findViewById<Button>(R.id.btn_7)
        val btn8 = view.findViewById<Button>(R.id.btn_8)
        val btn9 = view.findViewById<Button>(R.id.btn_9)
        val btnDivide = view.findViewById<Button>(R.id.btn_divide)
        val btnPositiveNegative = view.findViewById<Button>(R.id.btn_positive_negative)
        val btnPlus = view.findViewById<Button>(R.id.btn_plus)
        val btnComma = view.findViewById<Button>(R.id.btn_comma)
        val btnPercent = view.findViewById<Button>(R.id.btn_percent)
        val btnSubtract = view.findViewById<Button>(R.id.btn_subtract)
        val btnAC = view.findViewById<Button>(R.id.btn_AC)
        val btnX = view.findViewById<Button>(R.id.btn_x)
        val btnEqual = view.findViewById<Button>(R.id.btn_equal)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculate, container, false)
    }

}