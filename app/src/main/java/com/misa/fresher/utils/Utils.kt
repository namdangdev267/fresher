package com.misa.fresher.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import java.lang.Exception

fun <T> List<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}

fun Context.showToast(message: String) {
    val toast = Toast.makeText(
        this, message, Toast.LENGTH_LONG
    )
    toast.setGravity(Gravity.TOP, 0, 120)
    toast.show()
}

fun getColor(context: Context?, color: Int): Int = ContextCompat.getColor(context!!, color)
fun getDrawable(context: Context?, drawable: Int): Drawable? = AppCompatResources.getDrawable(context!!, drawable)
fun getDimension(context: Context?, dimension: Int) = context!!.resources.getDimensionPixelSize(dimension)

fun handleException(exception: Exception) {
    Log.e("--FRESHER--","---ERROR--- ${exception.localizedMessage}")
}