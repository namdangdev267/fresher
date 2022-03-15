package com.misa.fresher.data.model

import com.misa.fresher.util.enum.InputInfoType

/**
 * Lớp model chứa thông tin cho trường dùng spinner
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
data class SpinnerInputModel(
    override val title: String,
    override val isRequired: Boolean,
    val items: List<String> = listOf()
) : InputInfoModel(InputInfoType.SPINNER, title, isRequired, null)