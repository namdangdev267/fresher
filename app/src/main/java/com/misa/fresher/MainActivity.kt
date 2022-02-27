package com.misa.fresher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.Product

class MainActivity : AppCompatActivity() {

    private lateinit var listProductView: RecyclerView;
    private lateinit var drawer: DrawerLayout;
    private lateinit var toolbar: Toolbar;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.listProductView = findViewById(R.id.list_product_view)

        val products: ArrayList<Product> = arrayListOf();
        for (i in 1..15) products.add(Product("name $i", "code$i", 1000.0 * i));

        val adapter = ProductViewAdapter();
        adapter.products = products

        this.listProductView.adapter = adapter;
        this.listProductView.layoutManager = LinearLayoutManager(this)

        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);


        this.drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            this.drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.syncState()
        drawer.addDrawerListener(toggle)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}