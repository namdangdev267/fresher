package kma.longhoang.beta.fragment.delivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.adapter.DeliveryAdapter
import kma.longhoang.beta.model.DeliveryModel

class RecipientFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_recipient)
        val list = mutableListOf<DeliveryModel>(
            DeliveryModel.EnterEditText(
                getString(R.string.recipient),
                getString(R.string.star),
                getString(R.string.tap_to_select),
                R.drawable.ic_baseline_add_24
            ),
            DeliveryModel.EnterEditText(
                getString(R.string.phone_number),
                getString(R.string.star),
                getString(R.string.tap_to_enter),
                null
            ),
            DeliveryModel.EnterEditText(
                getString(R.string.address),
                getString(R.string.star),
                getString(R.string.tap_to_enter),
                null
            ),
            DeliveryModel.EnterEditText(
                getString(R.string.area),
                null,
                getString(R.string.tap_to_select),
                R.drawable.ic_baseline_arrow_forward_ios_24
            ),
            DeliveryModel.EnterEditText(
                getString(R.string.wards),
                null,
                getString(R.string.tap_to_select),
                R.drawable.ic_baseline_arrow_forward_ios_24
            ),
            DeliveryModel.EnterTextView(
                getString(R.string.shipping_cost),
                null,
                "0,0",
                R.drawable.ic_baseline_calculate_24
            ),
            DeliveryModel.Enter2Column(
                getText(R.string.deposit_form).toString(),
                "Chuyển khoản",
                R.drawable.ic_baseline_keyboard_arrow_down_24,
                getString(R.string.deposit_money),
                "0,0"
            ),
            DeliveryModel.EnterTextView(getString(R.string.channel), null, "Facebook", null),
            DeliveryModel.Checkbox("Thu COD")
        )
        val adapter = DeliveryAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipient, container, false)
    }

}