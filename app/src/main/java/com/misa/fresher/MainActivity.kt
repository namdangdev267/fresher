package com.misa.fresher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ui.Product
import com.example.ui.RcvAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val data = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configView()
        setData()
    }

    fun configView() {
        var adpter = RcvAdapter().also { it.list = data }
        rcvListProduct.adapter = adpter
    }

    fun setData() {
        data.addAll(
            listOf(
                Product("BAC1", "tui xach 1", 1234.0, R.drawable.ic_shopping_bag),
                Product("BAC2", "tui xach 2", 2312.0, R.drawable.ic_shopping_bag),
                Product("BAC3", "tui xach 3", 1239.0, R.drawable.ic_shopping_bag),
                Product("BAC4", "tui xach 4", 5667.0, R.drawable.ic_shopping_bag),
                Product("BAC5", "tui xach 5", 9999.0, R.drawable.ic_shopping_bag),
                Product("BAC6", "tui xach 6", 9999.0, R.drawable.ic_shopping_bag),
                Product("BAC7", "tui xach 7", 9999.0, R.drawable.ic_shopping_bag),
                Product("BAC8", "tui xach 8", 9999.0, R.drawable.ic_shopping_bag)
            )
        )
        rcvListProduct.adapter?.notifyDataSetChanged()
    }
}