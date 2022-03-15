package com.misa.fresher.utils

object Utils {
    fun <T> listToArrayList(list: List<T>): ArrayList<T> {
        val res = arrayListOf<T>()
        res.addAll(list)
        return res
    }
}