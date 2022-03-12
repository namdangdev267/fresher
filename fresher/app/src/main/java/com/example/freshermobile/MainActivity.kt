package com.example.freshermobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freshermobile.adapter.ProductAdapter
import com.example.freshermobile.fragment.calculatorfragment.CalculatorActivity
import com.example.freshermobile.model.ProductModel

class MainActivity : AppCompatActivity() {
    private var listProduct = mutableListOf<ProductModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        for (i in 0 until 50) {
            val productModel = ProductModel()
            productModel.name = ("MaSP ".plus(i))
            productModel.amount = i
            productModel.price = 0.0F
            listProduct.add(productModel)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = ProductAdapter(listProduct)
        val btnCart = findViewById<ImageButton>(R.id.button_cart)
        btnCart.setOnClickListener {
            startActivity(Intent(this,DeliveryActivity::class.java))
        }
        val btnFilter = findViewById<ImageButton>(R.id.button_filter)
        btnFilter.setOnClickListener {
            startActivity(Intent(this,CalculatorActivity::class.java))
        }
    }
}