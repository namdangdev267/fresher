package com.misa.fresher.ui.sale.deliveryinfo.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.databinding.FragmentPackageInfoBinding
import com.misa.fresher.model.InputInfo
import com.misa.fresher.ui.sale.adapter.ReceiverInputAdapter
import com.misa.fresher.util.enum.InputType

class PackageInfoFragment : Fragment() {

    private var _binding: FragmentPackageInfoBinding? = null
    // Chỉ sử dụng ở giữa onCreateView và onDestroyView
    private val binding get() = _binding!!
    private var adapter: ReceiverInputAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPackageInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}