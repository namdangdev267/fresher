package com.misa.fresher.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.IllegalArgumentException

abstract class BaseFragment<VB : ViewBinding>(private val bindingInflater: (inflater: LayoutInflater) -> VB) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    private var isNotYetInit: Boolean = true

    abstract fun initUI()
    open fun initListener() {}
    open fun onBackPressed(): Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (_binding == null) _binding = bindingInflater(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isNotYetInit) {
            initUI()
            initListener()
            isNotYetInit = false
        }
        updateUI()
    }

    open fun updateUI() {}
}