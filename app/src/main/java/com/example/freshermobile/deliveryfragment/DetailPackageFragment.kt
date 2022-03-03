package com.example.freshermobile.deliveryfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freshermobile.R
import com.example.freshermobile.adapter.DeliveryAdapter
import com.example.freshermobile.model.DeliveryModel


class DetailPackageFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView_package)
        val list = mutableListOf<DeliveryModel>(
            DeliveryModel.EnterTextView(getString(R.string.weight), null, "300", null ),
            DeliveryModel.PackageSize(
                getString(R.string.size),
                0,
                0,
                0,
            ),
        )
        val adapter = DeliveryAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_package, container, false)
    }


}