package com.misa.fresher.data.model

import com.misa.fresher.util.enum.InputInfoType

/**
 * Lớp model chứa thông tin cho item nhập kích cỡ cho gói hàng (width, height, depth)
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
data class PackageSizeInputModel(
    override val title: String,
    override val isRequired: Boolean
) : InputInfoModel(InputInfoType.PACKAGE_SIZE, title, isRequired)