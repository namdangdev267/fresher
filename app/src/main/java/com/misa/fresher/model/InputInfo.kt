package com.misa.fresher.model

import android.graphics.drawable.Drawable
import com.misa.fresher.util.enum.InputType

data class InputInfo(
    var title: String = "",
    var isRequired: Boolean = false,
    var type: InputType,
    var icon: Drawable? = null,

    // for multi col
    var cols: Array<InputInfo> = arrayOf(),

    // for spinner
    var arr: Array<String> = arrayOf(),

    // for type action
    var onClickListener: () -> Unit = { },
)
