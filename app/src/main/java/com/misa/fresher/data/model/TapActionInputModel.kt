package com.misa.fresher.data.model

import androidx.annotation.DrawableRes
import com.misa.fresher.util.enum.InputInfoType

/**
 * Lớp model chứa thông tin cho trường chạm để thực hiện hành động gì đó
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 2
 * @updated 3/15/2022: Tạo class
 * @updated 3/18/2022: Sửa đối override trường [icon], [input] tương ứng với lớp cha
 */
data class TapActionInputModel(
    override val title: String,
    override val isRequired: Boolean,
    @DrawableRes
    override val icon: Int?,
    override var input: Any? = null,
    val onClickListener: () -> Unit = {}
) : InputInfoModel(InputInfoType.TAP_ACTION, title, isRequired, icon, input)