package com.misa.fresher.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.misa.fresher.R
import com.misa.fresher.ViewPagerActivity
import com.misa.fresher.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {
    private var binding: FragmentCalculatorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.beginTransaction()
            .replace(R.id.calculator_screen, CalculatorScreenFragment())
            .replace(R.id.calculator_keyboard, CalculatorKeyFragment())
            .commit()
    }

}