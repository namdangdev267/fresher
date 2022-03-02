package com.misa.fresher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.adapters.ProductsAdapter
import com.misa.fresher.model.Product.Companion.createProductsList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rcv = findViewById<RecyclerView>(R.id.rcvListProduct)
        val adapter = ProductsAdapter(createProductsList(20))
        rcv.adapter=adapter
        rcv.layoutManager=LinearLayoutManager(this)
    }
}