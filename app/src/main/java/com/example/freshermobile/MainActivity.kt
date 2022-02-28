package com.example.freshermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var listProduct: MutableList<ProductModel>?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        listProduct = mutableListOf()
        for (i in 0 until 50) {
            val productModel = ProductModel()
            productModel.name = ("MaSP ".plus(i))
            productModel.number = i
            productModel.price = 0.0F
            listProduct?.add(productModel)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = ProductAdapter(listProduct!!)
    }
}