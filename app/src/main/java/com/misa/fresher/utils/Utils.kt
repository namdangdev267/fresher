package com.misa.fresher.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat

object Utils {
    fun <T> listToArrayList(list: List<T>): ArrayList<T> {
        val res = arrayListOf<T>()
        res.addAll(list)
        return res
    }

    fun getColor(context: Context?, color: Int): Int = ContextCompat.getColor(context!!, color)
    fun getDrawable(context: Context?, drawable: Int): Drawable? = AppCompatResources.getDrawable(context!!, drawable)
}