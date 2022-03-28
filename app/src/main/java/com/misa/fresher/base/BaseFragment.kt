package com.misa.fresher.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, V : BaseContract.View, P : BaseContract.Presenter<V>> :
    Fragment(), BaseContract.View {
    abstract fun initUI()
    protected val binding by lazy { getInflater(layoutInflater) }
    protected abstract val getInflater: (LayoutInflater) -> VB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }
}