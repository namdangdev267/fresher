package com.misa.fresher.Views.CustomViews

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.misa.fresher.R
import android.view.Gravity


object CustomToast {
    fun makeText(context: Context, message: String, duration: Int): Toast {
        val toast = Toast(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.custom_toast, null, false)
        val tvToastMessage = layout.findViewById<TextView>(R.id.tv_toast_message)
        tvToastMessage.text = message

        toast.duration = duration
        toast.view = layout
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 50)
        return toast
    }
}