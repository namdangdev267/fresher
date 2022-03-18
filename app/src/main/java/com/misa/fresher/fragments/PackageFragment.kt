package com.misa.fresher.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.adapters.ShipInforViewAdapter
import com.misa.fresher.model.ShippingView

/**
 * tạo class
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class PackageFragment : Fragment() {
    private var rcv: RecyclerView? = null
    private var list = mutableListOf<ShippingView>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_package, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        configRecyclerView(view)
    }

    /**
     * config recycler view cho package
     * chưa xử lý sự kiện
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun configRecyclerView(view: View) {
        rcv = view.findViewById(R.id.rcvShippingView_package)
        val adapter = ShipInforViewAdapter(list, {
        })
        rcv?.adapter = adapter
        rcv?.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * Tạo data cho adapter
     * @Auther : NTBao
     * @date : 3/18/2022
     **/
    private fun initData() {
        list = mutableListOf(
            ShippingView.TouchEditText("Trọng lượng (g)", "300", null),
            ShippingView.ThreeCol("Kích thước (cm)", "10", "10", "10")
        )
    }
}