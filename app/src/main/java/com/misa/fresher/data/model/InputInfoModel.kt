package com.misa.fresher.data.model

import android.graphics.drawable.Drawable
import com.misa.fresher.util.enum.InputInfoType

/**
 * Lớp model chứa thông tin cơ bản của 1 trường nhập liệu
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
open class InputInfoModel(
    open val type: InputInfoType = InputInfoType.TAP_ACTION,
    open val title: String = "",
    open val isRequired: Boolean = false,
    open val icon: Drawable? = null,
)
