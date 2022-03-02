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
import com.misa.fresher.databinding.FragmentReceiverBinding

class ReceiverFragment : Fragment() {
    private var binding: FragmentReceiverBinding? = null
    private var title: String? = null
    private var page = 0
    private var adapter: ReceiverAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt("receiverPage", 0) ?: 0
        title = arguments?.getString("receiverTitle")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = context?.let { ReceiverAdapter(createData(), it) }
        binding!!.rcvReceive.layoutManager = LinearLayoutManager(context)
        binding!!.rcvReceive.adapter = adapter
        binding!!.rcvReceive.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun createData(): Array<InputReceiver> {
        return arrayOf(
            InputReceiver(
                "Receiver",
                true,
                InputType.TAP_ACTION,
                resources.getDrawable(R.drawable.plus, null)
            ) {},
            InputReceiver("Tel", true, InputType.TAP_INSERT),
            InputReceiver("Address", true, InputType.TAP_INSERT),
            InputReceiver(
                "Area",
                false,
                InputType.TAP_ACTION,
                resources.getDrawable(R.drawable.angleright, null)
            ) {},
            InputReceiver(
                "Ward, Commune",
                false,
                InputType.TAP_ACTION,
                resources.getDrawable(R.drawable.angleright, null)
            ) {},
            InputReceiver(
                "Ship paid by cust",
                false,
                InputType.TAP_INSERT,
                resources.getDrawable(R.drawable.ic_baseline_calculate_24, null)
            ),
            InputReceiver(
                "", false, InputType.TWO_COL, null, arrayOf(
                    InputReceiver(
                        "Deposit method", false, InputType.SPINNER, null, arrayOf(), arrayOf(
                            "Transfer",
                            "Cash",
                            "ATM",
                            "VISA",
                            "MasterCard",
                            "Visa Debit",
                            "Master Debit",
                            "American Express",
                            "American Express Debit",
                            "JCB",
                            "UnionPay",
                            "Discover Network"
                        )
                    ),
                    InputReceiver("Deposit", false, InputType.TAP_INSERT),
                )
            ),
            InputReceiver(
                "Sale channel", false, InputType.SPINNER, null, arrayOf(), arrayOf(
                    "Facebook",
                    "Zalo",
                    "Instagram",
                    "Tiki",
                    "Lazada",
                    "Facebook",
                    "Zalo",
                    "Instagram",
                    "Tiki",
                    "Lazada"
                )
            ),
            InputReceiver("Collect COD", false, InputType.CHECK_BOX),
        )
    }


    companion object {
        fun newInstance(page: Int, title: String): ReceiverFragment {
            val fragmentReceiver = ReceiverFragment()
            val args = Bundle()
            args.putInt("receiverPage", page)
            args.putString("receiverTitle", title)
            fragmentReceiver.arguments = args
            return fragmentReceiver
        }
    }
}