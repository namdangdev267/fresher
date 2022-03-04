package com.example.freshermobile.fragment.calculatorfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.freshermobile.R

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_display, DisplayFragment()).commit()
        fragmentTransaction.replace(R.id.layout_calculate, CalculateFragment()).commit()
    }
}