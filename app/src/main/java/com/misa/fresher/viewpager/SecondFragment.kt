package com.misa.fresher.viewpager

import android.widget.TextView

import android.os.Bundle

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.misa.fresher.R


class SecondFragment : Fragment() {
    // Store instance variables
    private var title: String? = null
    private var page = 0

    // Store instance variables based on arguments passed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt("someInt", 0) ?: 0
        title = arguments?.getString("someTitle")
    }

    // Inflate the view for the fragment based on layout XML
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_first, container, false)
        val tvLabel = view.findViewById(R.id.tvLabel) as TextView
        tvLabel.text = "$page -- $title"
        return view
    }

    companion object {
        // newInstance constructor for creating fragment with arguments
        fun newInstance(page: Int, title: String?): SecondFragment {
            val fragmentFirst = SecondFragment()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", title)
            fragmentFirst.setArguments(args)
            return fragmentFirst
        }
    }
}