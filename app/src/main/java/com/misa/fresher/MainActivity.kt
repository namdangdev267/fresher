package com.misa.fresher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.misa.fresher.adapter.ProductApdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.adapter.Product


class MainActivity : AppCompatActivity() {
    lateinit var products: ArrayList<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rlv=findViewById<View>(R.id.ryvProduct) as RecyclerView
        products=Product.createContactsList(20)
        val adapter=ProductApdapter(products)
        rlv.adapter=adapter
        rlv.layoutManager=LinearLayoutManager(this)
    }

    fun action_ViewPager(view: View) {
        val intent = Intent(this,ViewPagerActivity::class.java)
        startActivity(intent)
    }
}