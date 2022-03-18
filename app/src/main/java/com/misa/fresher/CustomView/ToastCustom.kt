package com.misa.fresher.CustomView

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.misa.fresher.R

object ToastCustom {
    fun makeToast(context: Context, message: String, duration: Int): Toast {
        var toast = Toast(context)
        var layout = LayoutInflater.from(context).inflate(R.layout.toast_custom, null, false)
        var tvToast = layout.findViewById<TextView>(R.id.tv_toast_message)
        tvToast.text = message
        toast.duration = duration
        toast.view = layout
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 50)
        return toast
    }
}