package com.misa.fresher

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

fun Context.showToast(string: String) {
    val toast = Toast.makeText(
        this, string,
        Toast.LENGTH_SHORT
    )
    toast.setGravity(Gravity.TOP, 0, 0)
    toast.show()
}
//fun test{
//    CoroutineScope(IO).launch {
//
//    }
//}