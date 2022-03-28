package com.misa.fresher.ui.fragment.shipinfor.ship

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.input.InputReceiver
import com.misa.fresher.input.InputType

class ShipFragment(
    private var recyclerView: RecyclerView,
    private var shipViewModel: ShipViewModel
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ship, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shipViewModel = ShipViewModel(view.context)
        recyclerView = view.findViewById(R.id.recyclerview_shipping_ship)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = ShipAdapter(shipViewModel.listItemShip)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun createData(): Array<InputReceiver> {
        return arrayOf(
            InputReceiver("", false, InputType.DELIVERY_TYPE),
            InputReceiver(
                "Shipping partner", false, InputType.SPINNER, null, arrayOf(), arrayOf(
                    "Viettel Post",
                    "VNPost",
                    "J&T Express",
                    "Giao hàng tiết kiệm",
                    "GHN",
                )
            ),
            InputReceiver("Service type", false, InputType.TAP_ACTION) {},
            InputReceiver(
                "Shipping cost paid to partner",
                false,
                InputType.TAP_INSERT,
                resources.getDrawable(R.drawable.calculator, null)
            ),
            InputReceiver(
                "Tracking No.",
                false,
                InputType.TAP_INSERT
            ),
            InputReceiver(
                "Notes",
                false,
                InputType.TAP_INSERT
            ),
            InputReceiver("Shipping date", false, InputType.TAP_ACTION) {},
        )
    }
}