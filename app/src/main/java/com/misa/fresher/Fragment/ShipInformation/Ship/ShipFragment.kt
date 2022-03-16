package com.misa.fresher.Fragment.ShipInformation.Ship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Input.InputReceiver
import com.misa.fresher.Input.InputType
import com.misa.fresher.Models.ItemShipInfor
import com.misa.fresher.R

class ShipFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var listItemShip: MutableList<ItemShipInfor>
    lateinit var shipViewModel: ShipViewModel

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

    companion object{
        fun newInstance(page: Int, title: String?): ShipFragment {
            val fragmentShip = ShipFragment()
            val args = Bundle()
            args.putInt("shipPage", page)
            args.putString("shipTitle", title)
            fragmentShip.setArguments(args)
            return fragmentShip
        }
    }
}