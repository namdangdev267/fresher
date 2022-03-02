package com.misa.fresher

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.ProductViewAdapter.ItemClickListener
import com.misa.fresher.databinding.ActivityMainBinding
import com.misa.fresher.models.Product


class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // user activity binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initProductList()

        binding.priceBtn.setOnClickListener {
            val intent = Intent(this, ShippingBookActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun initToolbar() {
        toolbar = binding.toolbar
        this.setSupportActionBar(toolbar);

        this.drawer = binding.drawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.syncState()
        drawer.addDrawerListener(toggle)
    }

    private fun initProductList() {
        val listProductView = binding.listProductView

        val products: ArrayList<Product> = arrayListOf();
        for (i in 1..15) products.add(Product("name $i", "code$i", 1000.0 * i));

        val adapter = ProductViewAdapter(products, object : ItemClickListener {
            override fun onClick(item: Product) = showBottomSheet(item)
            override fun onLongClick(item: Product): Boolean {
                showBottomSheet(item)
                return true;
            }
        });

        listProductView.adapter = adapter;
        listProductView.layoutManager = LinearLayoutManager(this)
    }

    private fun showBottomSheet(item: Product) {
        val bottomSheetDialog =
            BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        val bottomSheetView: View = LayoutInflater.from(applicationContext).inflate(
            R.layout.bottom_sheet,
            findViewById(R.id.bottom_sheet_dialog)
        )
        bottomSheetView.findViewById<TextView>(R.id.bottom_sheet_text).text = item.name
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

}