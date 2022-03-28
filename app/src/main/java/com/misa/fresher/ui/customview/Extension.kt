package com.misa.fresher

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast

@SuppressLint("InflateParams")
fun Context.showToast(message: String) {
    var toast: Toast? = null

    toast?.cancel()

    toast = Toast(this)

    val layoutInflater = LayoutInflater.from(this).inflate(R.layout.toast_custom, null, false)
    val tvMessageToast = layoutInflater.findViewById<TextView>(R.id.tv_toast_message)

    tvMessageToast.text = message

    Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
        this.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 50)
        this.view = layoutInflater
    }.show()
}