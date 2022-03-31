package com.misa.fresher.ui.login.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.misa.fresher.databinding.DialogShopSelectBinding

/**
 * Lớp chứa dialog chọn cửa hàng làm việc
 *
 * @author Nguyễn Công Chính
 * @since 3/31/2022
 *
 * @version 1
 * @updated 3/31/2022: Tạo class
 */
class ShopSelectorDialog(
    context: Context,
    private val binding: DialogShopSelectBinding,
    private val onAcceptListener: (String) -> Unit
) {

    private val alertDialog by lazy { AlertDialog.Builder(context).setView(binding.root).create() }

    init {
        configDialog()
    }

    /**
     * Hàm cài đặt ban đầu cho dialog
     *
     * @author Nguyễn Công Chính
     * @since 3/31/2022
     *
     * @version 1
     * @updated 3/31/2022: Tạo function
     */
    private fun configDialog() {
        alertDialog.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)
    }

    /**
     * Hàm cài đặt các sự kiện, thay đổi dữ liệu hiển thị lên dialog
     *
     * @author Nguyễn Công Chính
     * @since 3/31/2022
     *
     * @version 1
     * @updated 3/31/2022: Tạo function
     */
    private fun bindData() {
        binding.btnDone.setOnClickListener {
            dismiss()
            val result = if (binding.rbShop1.isChecked)
                binding.rbShop1.text.toString()
            else binding.rbShop2.text.toString()
            onAcceptListener(result)
        }
    }

    /**
     * Hàm hiển thị dialog
     *
     * @author Nguyễn Công Chính
     * @since 3/31/2022
     *
     * @version 1
     * @updated 3/31/2022: Tạo function
     */
    fun show() {
        bindData()
        alertDialog.show()
    }

    /**
     * Hàm ẩn đi dialog
     *
     * @author Nguyễn Công Chính
     * @since 3/31/2022
     *
     * @version 1
     * @updated 3/31/2022: Tạo function
     */
    fun dismiss() {
        alertDialog.dismiss()
    }
}