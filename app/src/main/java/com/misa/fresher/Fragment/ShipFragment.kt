package com.misa.fresher.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.Adapter.ReceiverAdapter
import com.misa.fresher.InputShip.InputReceiver
import com.misa.fresher.InputShip.InputType
import com.misa.fresher.R
import com.misa.fresher.databinding.FragmentShipBinding

class ShipFragment: Fragment() {
    private var title: String?= null
    private var page = 0
    private var binding: FragmentShipBinding?= null
    private var adapter: ReceiverAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt("shipPage", 0) ?: 0
        title = arguments?.getString("shipTitle")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShipBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = context?.let { ReceiverAdapter(createData(), it) }
        binding!!.rcvShip.layoutManager = LinearLayoutManager(context)
        binding!!.rcvShip.adapter = adapter
        binding!!.rcvShip.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
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
                resources.getDrawable(R.drawable.ic_baseline_calculate_24, null)
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