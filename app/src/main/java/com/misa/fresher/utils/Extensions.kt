package com.misa.fresher

import android.content.Context
import android.view.Gravity
import android.widget.Toast

fun Context.showToast(string: String) {
    val toast = Toast.makeText(
        this, string,
        Toast.LENGTH_SHORT
    )
    toast.setGravity(Gravity.TOP, 0, 0)
    toast.show()
}