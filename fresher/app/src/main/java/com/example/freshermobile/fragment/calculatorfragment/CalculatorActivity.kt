package com.example.freshermobile.fragment.calculatorfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.freshermobile.R

class CalculatorActivity : AppCompatActivity(), CalculateFragment.CalculateSend {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_display, DisplayFragment())
        fragmentTransaction.replace(R.id.layout_calculate, CalculateFragment())
        fragmentTransaction.commit()
    }

    override fun onCalculateSent(content: String) {
        val fragmentDisplay =
            supportFragmentManager.findFragmentById(R.id.layout_display) as DisplayFragment
        fragmentDisplay.receiveDataFromFragment(content)
    }
}