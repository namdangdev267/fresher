package com.misa.fresher

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView


fun Context.showToastUp(message: String) {
    val layout = LayoutInflater.from(this).inflate(R.layout.custom_toast, null, false)
    val tvToastMessage = layout.findViewById<TextView>(R.id.tv_toast_message)

    tvToastMessage.text = message

    Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
        this.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 50)
        this.view = layout
    }.show()
}





