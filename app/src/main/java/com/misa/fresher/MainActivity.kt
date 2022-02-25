package com.misa.fresher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ui.Product
import com.example.ui.RcvAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adpter: RcvAdapter? = null

    val data = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adpter = RcvAdapter()
        setData()
        adpter?.list = data
        rcvListProduct.adapter = adpter
    }

    fun setData() {
        val pro1 = Product("BAC1", "tui xach 1", 1234.0, R.drawable.ic_shopping_bag)
        val pro2 = Product("BAC2", "tui xach 2", 2312.0, R.drawable.ic_shopping_bag)
        val pro3 = Product("BAC3", "tui xach 3", 1239.0, R.drawable.ic_shopping_bag)
        val pro4 = Product("BAC4", "tui xach 4", 5667.0, R.drawable.ic_shopping_bag)
        val pro5 = Product("BAC5", "tui xach 5", 9999.0, R.drawable.ic_shopping_bag)
        val pro6 = Product("BAC6", "tui xach 6", 9999.0, R.drawable.ic_shopping_bag)
        val pro7 = Product("BAC7", "tui xach 7", 9999.0, R.drawable.ic_shopping_bag)
        val pro8 = Product("BAC8", "tui xach 8", 9999.0, R.drawable.ic_shopping_bag)
        data.add(pro1)
        data.add(pro2)
        data.add(pro3)
        data.add(pro4)
        data.add(pro5)
        data.add(pro6)
        data.add(pro7)
        data.add(pro8)

    }
}