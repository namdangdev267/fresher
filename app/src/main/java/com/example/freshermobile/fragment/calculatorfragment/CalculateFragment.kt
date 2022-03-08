package com.example.freshermobile.fragment.calculatorfragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.freshermobile.R
import com.example.freshermobile.databinding.FragmentCalculateBinding

class CalculateFragment : Fragment() {

    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!
    private lateinit var calculateSend: CalculateSend

    interface CalculateSend {
        fun onCalculateSent(content: String)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btn0.setOnClickListener {
            sendDataToFragmentDisplay("0")
        }
        binding.btn1.setOnClickListener {
            sendDataToFragmentDisplay("1")
        }
        binding.btn2.setOnClickListener {
            sendDataToFragmentDisplay("2")
        }
        binding.btn3.setOnClickListener {
            sendDataToFragmentDisplay("3")
        }
        binding.btn4.setOnClickListener {
            sendDataToFragmentDisplay("4")
        }
        binding.btn5.setOnClickListener {
            sendDataToFragmentDisplay("5")
        }
        binding.btn6.setOnClickListener {
            sendDataToFragmentDisplay("6")
        }
        binding.btn7.setOnClickListener {
            sendDataToFragmentDisplay("7")
        }
        binding.btn7.setOnClickListener {
            sendDataToFragmentDisplay("7")
        }
        binding.btn8.setOnClickListener {
            sendDataToFragmentDisplay("8")
        }
        binding.btnPlus.setOnClickListener {
            sendDataToFragmentDisplay("+")
        }
        binding.btnSubtract.setOnClickListener {
            sendDataToFragmentDisplay("-")
        }
        binding.btnX.setOnClickListener {
            sendDataToFragmentDisplay("*")
        }
        binding.btnDivide.setOnClickListener {
            sendDataToFragmentDisplay("/")
        }
        binding.btnEqual.setOnClickListener {
            sendDataToFragmentDisplay("=")
        }
        binding.btn9.setOnClickListener {
            sendDataToFragmentDisplay("9")
        }
        binding.btnAC.setOnClickListener {
            sendDataToFragmentDisplay("AC")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        calculateSend = activity as CalculateSend
    }

    fun sendDataToFragmentDisplay(content: String) {
        calculateSend.onCalculateSent(content)
    }
}