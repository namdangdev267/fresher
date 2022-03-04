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


}