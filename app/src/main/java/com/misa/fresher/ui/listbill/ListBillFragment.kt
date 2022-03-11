package com.misa.fresher.ui.listbill

import android.view.LayoutInflater
import com.misa.fresher.core.BaseFragment
import com.misa.fresher.databinding.FragmentListBillBinding

/**
 * Màn hình Danh sách hóa đơn
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 1
 * @updated 3/9/2022: Tạo class
 */
class ListBillFragment: BaseFragment<FragmentListBillBinding>() {

    override val getInflater: (LayoutInflater) -> FragmentListBillBinding
        get() = FragmentListBillBinding::inflate
}