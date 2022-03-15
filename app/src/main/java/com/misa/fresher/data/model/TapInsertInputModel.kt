package com.misa.fresher.data.model

import android.graphics.drawable.Drawable
import android.text.InputType
import com.misa.fresher.util.enum.InputInfoType

/**
 * Lớp model chứa thông tin cho trường chạm để nhập thông tin
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
data class TapInsertInputModel(
    override val title: String,
    override val isRequired: Boolean,
    override val icon: Drawable?,
    val inputType: Int = InputType.TYPE_CLASS_TEXT,
    var input: String = "",
) : InputInfoModel(InputInfoType.TAP_INSERT, title, isRequired, icon)