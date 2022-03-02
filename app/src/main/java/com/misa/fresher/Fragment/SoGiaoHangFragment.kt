package com.misa.fresher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SoGiaoHangFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_so_giao_hang, container, false)
    }

    companion object {

        fun newInstance(): SoGiaoHangFragment {
            val fragmentSoGiaoHang = SoGiaoHangFragment()
            val args = Bundle()
            fragmentSoGiaoHang.arguments = args
            return fragmentSoGiaoHang
        }

    }
}