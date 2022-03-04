package com.misa.fresher.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.misa.fresher.R

class ShipmentDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shipment_detail, container, false)
    }

    companion object {

        val TAG: String = ShipmentDetailFragment::class.java.getName()

        fun getNewInstance(): ShipmentDetailFragment? {
            val fragment = ShipmentDetailFragment()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }

        @JvmStatic
        fun newInstance() =
            ShipmentDetailFragment().apply {

            }
    }
}