package com.misa.fresher.Input

import android.graphics.drawable.Drawable

class InputReceiver(
    var title: String,
    var isRequired: Boolean = false,
    var type: InputType,
    var icon: Drawable? = null,


    var cols: Array<InputReceiver> = arrayOf(),
    var arr: Array<String> = arrayOf(),
    var onClickListener: () -> Unit = {}
)