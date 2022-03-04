package com.misa.fresher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.misa.fresher.adapter.ProductApdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.misa.fresher.model.Product


class MainActivity : AppCompatActivity() {
    lateinit var products: ArrayList<Product>
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_view)
        setUpViews()
    }

    private fun setUpViews() {
        val rlv = findViewById<RecyclerView>(R.id.ryvProduct)
        products = Product.createContactsList(20)
        val adapter = ProductApdapter(products)
        rlv.adapter = adapter
        rlv.layoutManager = LinearLayoutManager(this)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView= findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    fun actionOpen(view: View) {
        val intent=Intent(this,ViewPagerActivity::class.java)
        startActivity(intent)
    }
}