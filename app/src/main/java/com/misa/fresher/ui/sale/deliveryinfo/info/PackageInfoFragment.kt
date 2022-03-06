package com.misa.fresher.ui.sale.deliveryinfo.info

import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.databinding.FragmentPackageInfoBinding
import com.misa.fresher.model.InputInfo
import com.misa.fresher.ui.sale.adapter.ReceiverInputAdapter
import com.misa.fresher.util.enum.InputType

class PackageInfoFragment : BaseFragment<FragmentPackageInfoBinding>() {

    override val getInflater: (inflater: LayoutInflater) -> FragmentPackageInfoBinding =
        FragmentPackageInfoBinding::inflate
    private var adapter: ReceiverInputAdapter? = null

    override fun initUI() {
        adapter = context?.let { ReceiverInputAdapter(fakeData(), it) }
        binding.rcvPackageInput.layoutManager = LinearLayoutManager(context)
        binding.rcvPackageInput.adapter = adapter
        binding.rcvPackageInput.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun fakeData(): Array<InputInfo> =
        arrayOf(
            InputInfo("Trọng lượng (g)", false, InputType.TAP_INSERT),
            InputInfo("Kích thước (cm)", false, InputType.PACKAGE_SIZE),
        )
}