package com.misa.fresher.data.model

import com.misa.fresher.util.enum.InputInfoType

/**
 * Lớp model chứa thông tin cho item mà có 1 check box duy nhất
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
data class SingleCheckBoxInputModel(
    override val title: String
) : InputInfoModel(InputInfoType.SINGLE_CHECK_BOX, title)