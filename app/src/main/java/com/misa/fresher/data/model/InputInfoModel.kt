package com.misa.fresher.data.model

import androidx.annotation.DrawableRes
import com.misa.fresher.util.enum.InputInfoType

/**
 * Lớp model chứa thông tin cơ bản của 1 trường nhập liệu
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 2
 * @updated 3/15/2022: Tạo class
 * @updated 3/18/2022: Đổi trường [icon] từ Drawable -> Int, thêm trường [input] để nhận giá trị trả về từ item
 */
open class InputInfoModel(
    open val type: InputInfoType = InputInfoType.TAP_ACTION,
    open val title: String = "",
    open val isRequired: Boolean = false,
    @DrawableRes
    open val icon: Int? = null,
    open var input: Any? = null,
)
