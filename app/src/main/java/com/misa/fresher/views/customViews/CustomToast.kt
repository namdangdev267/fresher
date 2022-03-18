package com.misa.fresher.views.customViews

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.misa.fresher.R
import android.view.Gravity


object CustomToast {
    private var toast:Toast?=null
    fun makeText(context: Context, message: String, duration: Int){
        if(toast!=null)
        {
            toast?.cancel()
        }
        toast = Toast(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.custom_toast, null, false)
        val tvToastMessage = layout.findViewById<TextView>(R.id.tv_toast_message)
        tvToastMessage.text = message

        toast?.duration = duration
        toast?.view = layout
        toast?.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 50)
        toast?.show()
    }
}