package com.misa.fresher.views.customViews

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.misa.fresher.R
import android.view.Gravity
import com.misa.fresher.databinding.CustomToastBinding


object CustomToast {
    private var toast:Toast?=null
    fun makeText(context: Context, message: String){
        if(toast!=null)
        {
            toast?.cancel()
        }
        toast = Toast(context)
        val binding = CustomToastBinding.inflate(LayoutInflater.from(context))
        binding.tvToastMessage.text = message

        toast?.duration = Toast.LENGTH_SHORT
        toast?.view = binding.root
        toast?.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 50)
        toast?.show()
    }
}