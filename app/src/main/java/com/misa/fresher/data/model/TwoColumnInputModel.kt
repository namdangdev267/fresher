package com.misa.fresher.data.model

import com.misa.fresher.util.enum.InputInfoType

/**
 * Lớp model chứa thông tin cho item mà có 2 cột
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
data class TwoColumnInputModel(
    val column0: InputInfoModel = InputInfoModel(),
    val column1: InputInfoModel = InputInfoModel()
) : InputInfoModel(InputInfoType.TWO_COLUMN)