package com.misa.fresher.data.model

import android.graphics.drawable.Drawable
import com.misa.fresher.util.enum.InputInfoType

/**
 * Lớp model chứa thông tin cho trường chạm để thực hiện hành động gì đó
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
data class TapActionInputModel(
    override val title: String,
    override val isRequired: Boolean,
    override val icon: Drawable?,
    var input: String = "",
    val onClickListener: () -> Unit = {}
) : InputInfoModel(InputInfoType.TAP_ACTION, title, isRequired, icon)