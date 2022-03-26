package com.misa.fresher

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.databinding.CustomToastBinding
import com.misa.fresher.views.customViews.CustomToast


fun Context.showToastUp(message: String) {

    val layout = LayoutInflater.from(this).inflate(R.layout.custom_toast, null, false)
    val binding = CustomToastBinding.inflate(LayoutInflater.from(this))
    val tvToastMessage = binding.tvToastMessage

    tvToastMessage.text = message

    Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
        this.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 50)
        this.view = layout
    }.show()
}

fun String.getNumString(): String {
    var res=""
    for(i in this)
    {
        if(i.isDigit())
        {
            res+=i.toString()
        }

    }
    return res
}






