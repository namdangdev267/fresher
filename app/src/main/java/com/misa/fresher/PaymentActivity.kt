package com.misa.fresher

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        var imgShip = findViewById<ImageView>(R.id.imgShip)
        var imgBackMain = findViewById<ImageView>(R.id.imgBackMain)

        imgShip.setOnClickListener(View.OnClickListener {

        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        })

        imgBackMain.setOnClickListener(View.OnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}