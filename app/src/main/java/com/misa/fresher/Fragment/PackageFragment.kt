package com.misa.fresher.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.misa.fresher.Input.InputReceiver
import com.misa.fresher.Input.InputType
import com.misa.fresher.R

class PackageFragment: Fragment() {
    private var mView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_package, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mView = view

        onClickEvent()
    }

    private fun onClickEvent() {
        mView?.findViewById<ImageView>(R.id.imgBackMain)?.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_package_to_fragment_sale)
        }
    }

}