package com.misa.fresher.data.model

import android.text.InputType
import androidx.annotation.DrawableRes
import com.misa.fresher.util.enum.InputInfoType

/**
 * Lớp model chứa thông tin cho trường chạm để nhập thông tin
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 2
 * @updated 3/15/2022: Tạo class
 * @updated 3/18/2022: Cập nhật override trường [icon], [input] tương ứng với lớp cha
 */
data class TapInsertInputModel(
    override val title: String,
    override val isRequired: Boolean,
    @DrawableRes
    override val icon: Int?,
    val inputType: Int = InputType.TYPE_CLASS_TEXT,
    override var input: Any? = null
) : InputInfoModel(InputInfoType.TAP_INSERT, title, isRequired, icon, input)