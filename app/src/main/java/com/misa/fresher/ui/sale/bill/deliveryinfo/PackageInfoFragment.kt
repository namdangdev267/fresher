package com.misa.fresher.ui.sale.bill.deliveryinfo

import android.text.InputType
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.data.model.*
import com.misa.fresher.databinding.FragmentPackageInfoBinding
import com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.DeliveryInputAdapter

/**
 * Màn nhập thông tin liên quan đến gói hàng (trọng lượng, kích thước các thứ,...)
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
class PackageInfoFragment: BaseFragment<FragmentPackageInfoBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentPackageInfoBinding
        get() = FragmentPackageInfoBinding::inflate
    private var adapter: DeliveryInputAdapter? = null

    override fun initUI() {
        configRcv()
    }

    /**
     * Cài đặt Recycler view nhập liệu
     *
     * @author Nguyễn Công Chính
     * @since 3/15/2022
     *
     * @version 1
     * @updated 3/15/2022: Tạo function
     */
    private fun configRcv() {
        adapter = DeliveryInputAdapter(listOf(
            TapInsertInputModel("Trọng lượng (g)", false,
                null, InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL),
            PackageSizeInputModel("Kích thước (cm)", false)
        ), requireContext())
        binding.rcvPackageInput.adapter = adapter
        binding.rcvPackageInput.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}